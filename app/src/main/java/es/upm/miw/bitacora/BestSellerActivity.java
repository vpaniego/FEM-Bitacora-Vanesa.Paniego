package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;


public class BestSellerActivity extends Activity {

    final static String LOG_TAG = "MiW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reparto);

        TextView tvItemAuthor = findViewById(R.id.tvItemAuthor);
        tvItemAuthor.setText("Author");

        TextView tvItemTitle = findViewById(R.id.tvItemTitle);
        tvItemTitle.setText("Title");

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
