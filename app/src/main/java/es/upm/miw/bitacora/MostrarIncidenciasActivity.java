package es.upm.miw.bitacora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MostrarIncidenciasActivity extends Activity {

    final static String LOG_TAG = "MiW";

    private ListView incidenciasListView;

    IncidenciaAdapter adapter;

    private DatabaseReference mRepartidoresReference;

    String currentUserID;
    String itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencias);

        Intent intent = getIntent();
        currentUserID = intent.getStringExtra("FIREBASE_AUTH_CURRENT_USER");
        itemID = intent.getStringExtra("ITEM_ID");

        incidenciasListView = findViewById(R.id.incidenciasListView);

        View headerView = getLayoutInflater().inflate(R.layout.listado_incidencias_header, null);
        incidenciasListView.addHeaderView(headerView);

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference()
                .child("repartidores").child(currentUserID).child("repartos").child(itemID).child("incidencias");

        mRepartidoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                @SuppressWarnings("unchecked")
                Map<String, ?> incidencias = (Map<String, ?>) dataSnapshot.getValue();
                if (incidencias == null) {
                    /* log a warning, DataSnapshot.getValue may return null */
                    Log.w(LOG_TAG, "DataSnapshot-incidencias.getValue may return null");

                    Toast.makeText(
                            getApplicationContext(),
                            "El reparto seleccionado no tiene ninguna incidencia. ",
                            Toast.LENGTH_LONG
                    ).show();

                    return;
                }
                ArrayList<Incidencia> incidenciasLst = new ArrayList<>();

                for (Map.Entry<String, ?> incidenciaSetEntry : incidencias.entrySet()) {

                    Incidencia incidencia = new Incidencia();

                    Map<String, ?> incidenciaMap = (HashMap<String, ?>) incidenciaSetEntry.getValue();
                    for (Map.Entry<String, ?> incidenciaEntry : incidenciaMap.entrySet()) {
                        if (incidenciaEntry.getValue() instanceof String) {
                            incidencia.setObservaciones((String) incidenciaEntry.getValue());
                        } else {
                            incidencia.setFecha((Long) incidenciaEntry.getValue());
                        }
                    }
                    incidenciasLst.add(incidencia);

                }

                adapter = new IncidenciaAdapter(
                        getApplicationContext(),
                        R.layout.listado_incidencias,
                        incidenciasLst);

                incidenciasListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(LOG_TAG, "loadIncidencia:onCancelled", databaseError.toException());
                // ...
            }
        });

    }
}
