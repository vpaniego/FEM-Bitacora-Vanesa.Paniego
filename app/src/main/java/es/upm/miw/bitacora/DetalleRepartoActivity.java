package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import es.upm.miw.bitacora.models.Reparto;


public class DetalleRepartoActivity extends Activity {

    final static String LOG_TAG = "MiW";

    Reparto itemReparto;
    String currentUserID;

    private DatabaseReference mRepartidoresReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reparto);

        itemReparto = getItemRepartoSelected();
        currentUserID = getCurrentUser();

        //TODO: Call APIRest


        TextView tvItemAuthor = findViewById(R.id.tvItemAuthor);
        tvItemAuthor.setText("Author");

        TextView tvItemTitle = findViewById(R.id.tvItemTitle);
        tvItemTitle.setText(itemReparto.getTitulo());

        setResult(RESULT_OK);
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

    public void finalizarReparto() {
        Date today = new Date();
        itemReparto.setFechaEntrega(today.getTime());

        itemReparto.setEntregado(true);

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference();
        mRepartidoresReference.child("repartidores").child(currentUserID).child("repartos").child(itemReparto.getId()).setValue(itemReparto);
    }
}
