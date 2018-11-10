package es.upm.miw.bitacora;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import es.upm.miw.bitacora.models.BestSeller;
import es.upm.miw.bitacora.models.Isbn;
import es.upm.miw.bitacora.models.Reparto;
import es.upm.miw.bitacora.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetalleRepartoActivity extends Activity {

    final static String LOG_TAG = "MiW";

    Reparto itemReparto;
    String currentUserID;

    private static final String API_BASE_URL = "https://api.nytimes.com/";

    private DatabaseReference mRepartidoresReference;

    private BookNYTResultRESTAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_reparto);

        itemReparto = getItemRepartoSelected();
        currentUserID = getCurrentUser();

        mRepartidoresReference = FirebaseDatabase.getInstance().getReference();

        builderRetrofitApiService();
    }

    private void builderRetrofitApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(BookNYTResultRESTAPIService.class);

        Call<BestSeller> call_async = apiService.getBestSellerByIsbn(itemReparto.getId());

        // Asíncrona
        call_async.enqueue(new Callback<BestSeller>() {

            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             */
            @Override
            public void onResponse(Call<BestSeller> call, Response<BestSeller> response) {
                if (response.isSuccessful()) {

                    BestSeller bestSeller = response.body();

                    Log.i(LOG_TAG, "response isSuccess " + bestSeller.toString());

                    if (null != bestSeller) {

                        TextView tvItemCopyright = findViewById(R.id.tvItemCopyright);
                        tvItemCopyright.setText(bestSeller.getCopyright());

                        Result result = new Result();
                        if (bestSeller.getNumResults() > 0) {
                            result = bestSeller.getResults().get(0);
                        }

                        TextView tvItemAuthor = findViewById(R.id.tvItemAuthor);
                        tvItemAuthor.setText(result.getAuthor());

                        TextView tvItemTitle = findViewById(R.id.tvItemTitle);
                        tvItemTitle.setText(result.getTitle());

                        if(result.getIsbns().size() > 0) {
                            Isbn isbn = result.getIsbns().get(0);
                            TextView tvItemIsbn = findViewById(R.id.tvItemIsbn);
                            tvItemIsbn.setText("" + isbn.getIsbn13());
                        }

                        TextView tvItemAgeGroup = findViewById(R.id.tvItemAgeGroup);
                        tvItemAgeGroup.setText(result.getAgeGroup());

                        TextView tvItemDescription = findViewById(R.id.tvItemDescription);
                        tvItemDescription.setText(result.getDescription());

                        TextView tvItemPrice = findViewById(R.id.tvItemPrice);
                        tvItemPrice.setText("" + result.getPrice());

                        TextView tvItemPublisher = findViewById(R.id.tvItemPublisher);
                        tvItemPublisher.setText("" + result.getPublisher());
                    }
                } else {
                    Log.i(LOG_TAG, "response notSuccess ");

                    Toast.makeText(
                            getApplicationContext(),
                            "No se ha encontrado detalle para el artículo seleccionado. ",
                            Toast.LENGTH_LONG
                    ).show();
                }

            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            @Override
            public void onFailure(Call<BestSeller> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e(LOG_TAG, t.getMessage());
            }
        });

        // Síncrona... no aquí => NetworkOnMainThreadException
//        Call<Country> call_sync = apiService.getCountryByName("spain");
//        try {
//            Country country = call_sync.execute().body();
//            Log.i(LOG_TAG, "SYNC => " + country.toString());
//        } catch (IOException e) {
//            Log.e(LOG_TAG, e.getMessage());
//        }


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

        mRepartidoresReference.child("repartidores").child(currentUserID).child("repartos").child(itemReparto.getId()).setValue(itemReparto);

        finish();
    }

    public void registrarIncidencia() {
        Intent intent = new Intent(this, IncidenciaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("REPARTO", itemReparto);
        intent.putExtras(bundle);
        intent.putExtra("FIREBASE_AUTH_CURRENT_USER", currentUserID);
        startActivity(intent);
    }
}
