package es.upm.miw.bitacora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.upm.miw.bitacora.models.Incidencia;
import es.upm.miw.bitacora.models.Repartidor;
import es.upm.miw.bitacora.models.Reparto;

public class RepartosActivity extends Activity {

    final static String LOG_TAG = "MiW";

    private static final int RC_SIGN_IN = 2018;

    private ListView repartosListView;

    RepartoAdapter adapter;

    private DatabaseReference mRepartidoresReference;

    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartos);

        Intent intent = getIntent();
        currentUserID = intent.getStringExtra("FIREBASE_AUTH_CURRENT_USER");

        final Repartidor mRepartidorSet = new Repartidor();

        repartosListView = findViewById(R.id.repartosListView);

        View headerView = getLayoutInflater().inflate(R.layout.listado_repartos_header, null);
        repartosListView.addHeaderView(headerView);

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference()
                .child("repartidores").child(currentUserID);

        mRepartidoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                @SuppressWarnings("unchecked")
                Map<String, ?> repartidores = (Map<String, ?>) dataSnapshot.getValue();
                if (repartidores == null) {
                    /* log a warning, DataSnapshot.getValue may return null */
                    Log.w(LOG_TAG, "DataSnapshot.getValue may return null");
                    return;
                }
                ArrayList<Reparto> repartoList = new ArrayList<>();

                for (Map.Entry<String, ?> repartoSetEntry : repartidores.entrySet()) {
                    switch (repartoSetEntry.getKey()) {
                        case "username":
                            mRepartidorSet.setUsername((String) repartoSetEntry.getValue());
                            break;
                        case "email":
                            mRepartidorSet.setEmail((String) repartoSetEntry.getValue());
                            break;
                        case "repartos":
                            @SuppressWarnings("unchecked")
                            Map<String, ?> repartoMap = (HashMap<String, ?>) repartoSetEntry.getValue();
                            for (Map.Entry<String, ?> repartoEntry : repartoMap.entrySet()) {
                                @SuppressWarnings("unchecked")
                                Map<String, ?> repartoValueMap = (Map<String, ?>) repartoEntry.getValue();
                                Reparto reparto = new Reparto();
                                // Note that the following calls are based on assumption...
                                reparto.setId((String) repartoEntry.getKey());
                                reparto.setTitulo((String) repartoValueMap.get("titulo"));
                                reparto.setFechaRecepcion((Long) repartoValueMap.get("fechaRecepcion"));
                                reparto.setFechaEntrega((Long) repartoValueMap.get("fechaEntrega"));
                                reparto.setDireccion((String) repartoValueMap.get("direccion"));
                                reparto.setEntregado((Boolean) repartoValueMap.get("entregado"));
                                reparto.setIncidencias(getIncidenciasFromRepartoValueMap(repartoValueMap.get("incidencias")));

                                repartoList.add(reparto);
                            }
                            mRepartidorSet.setRepartos(repartoList);
                            break;
                    }
                }
                adapter = new RepartoAdapter(
                        getApplicationContext(),
                        R.layout.listado_repartos,
                        mRepartidorSet.getRepartos());

                repartosListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                repartosListView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position,
                                            long arg3) {
                        Reparto value = (Reparto) adapter.getItemAtPosition(position);
                        // assuming string and if you want to get the value on click of list item
                        // do what you intend to do on click of listview row

                        StringBuilder texto = new StringBuilder("Opción elegida (")
                                .append(String.valueOf(position))
                                .append("): ")
                                .append(value.getTitulo());

                        Intent intent = new Intent(getApplicationContext(), DetalleRepartoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("REPARTO", value);
                        intent.putExtras(bundle);
                        intent.putExtra("FIREBASE_AUTH_CURRENT_USER", currentUserID);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(LOG_TAG, "loadRepartidor:onCancelled", databaseError.toException());
                // ...
            }
        });

    }

    public ArrayList<Incidencia> getIncidenciasFromRepartoValueMap(Object resultadoMapIncidencias) {
        ArrayList<Incidencia> incidencias = new ArrayList<>();

        if (resultadoMapIncidencias instanceof ArrayList) {

            incidencias = (ArrayList) resultadoMapIncidencias;

        } else if (resultadoMapIncidencias instanceof Map) {

            Map<String, ?> mapIncidencias = (Map<String, ?>) resultadoMapIncidencias;


            if (mapIncidencias == null) {
                /* log a warning, DataSnapshot.getValue may return null */
                Log.w(LOG_TAG, "resultadoMapIncidencias may return null");
            } else {
                Incidencia incidencia = new Incidencia();


                for (Map.Entry<String, ?> incidenciaSetEntry : mapIncidencias.entrySet()) {
                    switch (incidenciaSetEntry.getKey()) {
                        case "observaciones":
                            incidencia.setObservaciones((String) incidenciaSetEntry.getValue());
                            break;
                        case "fecha":
                            incidencia.setFecha((Long) incidenciaSetEntry.getValue());
                            break;
                    }
                    incidencias.add(incidencia);
                }
            }
        }

        return incidencias;

    }

}
