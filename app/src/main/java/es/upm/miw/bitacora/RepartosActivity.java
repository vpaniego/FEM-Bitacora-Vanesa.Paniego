package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.upm.miw.bitacora.models.Repartidor;
import es.upm.miw.bitacora.models.Reparto;

public class RepartosActivity extends Activity {

    final static String LOG_TAG = "MiW";

    private ListView repartosListView;

    RepartoAdapter adapter;

    private DatabaseReference mRepartidoresReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartos);

        Bundle bundle = this.getIntent().getExtras();

        repartosListView = findViewById(R.id.repartosListView);

        final Repartidor mRepartidorSet = new Repartidor();

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference()
                .child("repartidores").child("QtvKK8VIF2aNjJBSKU7jnZu4ZIh1");

        mRepartidoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                @SuppressWarnings("unchecked")
                Map<String, ?> repartidores = (Map<String, ?>)dataSnapshot.getValue();
                if (repartidores == null) {
                    /* log a warning, DataSnapshot.getValue may return null */
                    Log.i(LOG_TAG, "DataSnapshot.getValue may return null");
                    return;
                }
                ArrayList<Reparto> repartoList= new ArrayList<>();

                for (Map.Entry<String, ?> repartoSetEntry : repartidores.entrySet()) {
                    switch (repartoSetEntry.getKey()) {
                        case "username":
                            Log.i(LOG_TAG, "Username=" + (String)repartoSetEntry.getValue());
                            mRepartidorSet.setUsername((String)repartoSetEntry.getValue());
                            break;
                        case "email":
                            Log.i(LOG_TAG, "Mail=" + (String)repartoSetEntry.getValue());
                            mRepartidorSet.setEmail((String)repartoSetEntry.getValue());
                            break;
                        case "repartos":
                            @SuppressWarnings("unchecked")
                            Map<String, ?> repartoMap = (HashMap<String, ?>)repartoSetEntry.getValue();
                            for (Map.Entry<String, ?> repartoEntry : repartoMap.entrySet()) {
                                @SuppressWarnings("unchecked")
                                Map<String, ?> repartoValueMap = (Map<String, ?>)repartoEntry.getValue();
                                Reparto reparto = new Reparto();
                                // Note that the following calls are based on assumption...

                                reparto.setTitulo((String)repartoValueMap.get("titulo"));
                                reparto.setFechaRecepcion((Long) repartoValueMap.get("fechaRecepcion"));
                                reparto.setFechaEntrega((Long)repartoValueMap.get("fechaEntrega"));
                                reparto.setIncidencia((Boolean)repartoValueMap.get("incidencia"));

                                Log.i(LOG_TAG, "Reparto=" + reparto.toString());

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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(LOG_TAG, "loadRepartidor:onCancelled", databaseError.toException());
                // ...
            }
        });

        setResult(RESULT_OK);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_reparto_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.registrarIncidencia:
                mostrarRegistrarIncidencia();
                return true;
            case R.id.finalizarReparto:
                mostrarFinalizarReparto();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarRegistrarIncidencia() {
        DialogFragment registrarIncidenciaDialogFragment = new RegistrarIncidenciaDialogFragment();
        registrarIncidenciaDialogFragment.show(getFragmentManager(), String.valueOf(R.string.registrarIncidenciaText));
    }

    private void mostrarFinalizarReparto() {
        DialogFragment finalizarDialogFragment = new FinalizarRepartoDialogFragment();
        finalizarDialogFragment.show(getFragmentManager(), String.valueOf(R.string.finalizarRepartoText));
    }
}
