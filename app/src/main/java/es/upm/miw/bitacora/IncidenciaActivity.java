package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import es.upm.miw.bitacora.models.Incidencia;
import es.upm.miw.bitacora.models.Reparto;


public class IncidenciaActivity extends Activity {

    final static String LOG_TAG = "MiW";

    Reparto itemReparto;
    String currentUserID;

    EditText observacionesEditText;

    private DatabaseReference mRepartidoresReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        itemReparto = getItemRepartoSelected();
        currentUserID = getCurrentUser();

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference();

        observacionesEditText = findViewById(R.id.observacionesEditText);
    }

    private String getCurrentUser() {
        Intent intent = getIntent();
        return intent.getStringExtra("FIREBASE_AUTH_CURRENT_USER");
    }

    private Reparto getItemRepartoSelected() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return (Reparto) bundle.getSerializable("REPARTO");
    }

    public void onClickCrearIncidencia(View view) {
        Incidencia incidencia = new Incidencia(observacionesEditText.getText().toString(), new Date().getTime());

        mRepartidoresReference.child("repartidores").child(currentUserID).child("repartos").child(itemReparto.getId()).child("incidencias").push().setValue(incidencia);

        Toast.makeText(this, "Incidencia creada correctamente",
                Toast.LENGTH_SHORT).show();
    }

}
