package es.upm.miw.bitacora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.upm.miw.bitacora.models.Reparto;


public class LocalizacionActivity extends Activity {

    Reparto itemReparto;
    String currentUserID;

    EditText localizacionEditText;

    private DatabaseReference mRepartidoresReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion);

        itemReparto = getItemRepartoSelected();
        currentUserID = getCurrentUser();

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference();

        localizacionEditText = findViewById(R.id.localizacionEditText);
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

    public void onClickActualizarLocalizacion(View view) {
        String nuevaLocalizacion = localizacionEditText.getText().toString();
        StringBuffer localizacionBf = new StringBuffer(itemReparto.getLocalizacion()).append(", ").append(nuevaLocalizacion);

        itemReparto.setLocalizacion(localizacionBf.toString());

        mRepartidoresReference.child("repartidores").child(currentUserID).child("repartos").child(itemReparto.getId()).setValue(itemReparto);

        Toast.makeText(this, "Localización guardada correctamente",
                Toast.LENGTH_SHORT).show();

        finish();
    }

}
