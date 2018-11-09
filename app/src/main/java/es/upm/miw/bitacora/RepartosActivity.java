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

import es.upm.miw.bitacora.models.Repartidor;
import es.upm.miw.bitacora.models.Reparto;

public class RepartosActivity extends Activity {

    final static String LOG_TAG = "MiW";

    private static final int RC_SIGN_IN = 2018;

    private ListView repartosListView;

    RepartoAdapter adapter;

    private DatabaseReference mRepartidoresReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartos);

        Intent intent = getIntent();
        String currentUserID = intent.getStringExtra("FIREBASE_AUTH_CURRENT_USER");

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
                                reparto.setTitulo((String) repartoValueMap.get("titulo"));
                                reparto.setFechaRecepcion((Long) repartoValueMap.get("fechaRecepcion"));
                                reparto.setFechaEntrega((Long) repartoValueMap.get("fechaEntrega"));
                                reparto.setDireccion((String) repartoValueMap.get("direccion"));

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

                        startActivity(new Intent(getApplicationContext(), BestSellerActivity.class));

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

        setResult(RESULT_OK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, R.string.signed_in, Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "onActivityResult " + getString(R.string.signed_in));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, R.string.signed_cancelled, Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "onActivityResult " + getString(R.string.signed_cancelled));
                finish();
            }
        }
    }

}
