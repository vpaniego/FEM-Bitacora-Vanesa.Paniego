package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.bitacora.models.Repartidor;
import es.upm.miw.bitacora.models.Reparto;

//import es.upm.miw.db.RepositorioSCResultado;

public class RepartosActivity extends Activity {

    final static String LOG_TAG = "MiW";

    private ListView repartosListView;

    RepartidorAdapter adapter;

    private DatabaseReference mFirebaseDatabase;

    //RepositorioSCResultado resultadoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartos);

        //resultadoRepository = new RepositorioSCResultado(getApplicationContext());

        Bundle bundle = this.getIntent().getExtras();

        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
        //    actionBar.setDisplayHomeAsUpEnabled(true);
        //}

        repartosListView = findViewById(R.id.repartosListView);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        if(mFirebaseDatabase != null){
            Log.i(LOG_TAG, "existe instancia mFirebaseDatabase ");
        }

        mFirebaseDatabase.child("repartidores");
        List<Repartidor> mFirebaseDatabaseRepartos = new ArrayList<>();
        adapter = new RepartidorAdapter(
                this,
                R.layout.listado_repartos,
                mFirebaseDatabaseRepartos);

        repartosListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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

        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarRegistrarIncidencia() {
        //DialogFragment registrarIncidenciaDialogFragment = new IncidenciaDialogFragment();
        //registrarIncidenciaDialogFragment.show(getFragmentManager(), String.valueOf(R.string.registrarIncidenciaText));
    }
}
