package es.upm.miw.bitacora;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.upm.miw.bitacora.models.BestSeller;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepartoBestSellersActivity extends Activity {

    private static final String API_BASE_URL = "https://api.nytimes.com";

    private static final String LOG_TAG = "MiW";

    private TextView tvRespuesta;
    private EditText etBestSellerAuthor;

    private RepartoBestSellerRESTAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreateRepartoBestSellersActivity ");
        setContentView(R.layout.activity_reparto_bestsellers);

        tvRespuesta = findViewById(R.id.tvRespuesta);
        etBestSellerAuthor = findViewById(R.id.bestSellerAuthor);

        Log.i(LOG_TAG, "onCreateRepartoBestSellersActivity antes de RetrofitBuilder");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(LOG_TAG, "onCreateRepartoBestSellersActivity antes de crear apiservice");
        apiService = retrofit.create(RepartoBestSellerRESTAPIService.class);
    }

    public void obtenerInfoBestSeller(View v) {
        String bestSellerAuthor = etBestSellerAuthor.getText().toString();
        Log.i(LOG_TAG, "obtenerInfoBestSeller => BestSeller=" + bestSellerAuthor);
        tvRespuesta.setText(null);

        //Call<List<BestSeller>> call_async = apiService.getBestSellerByAuthor(bestSellerAuthor);

        Call<List<BestSeller>> call_async = apiService.getBestSellers();

        // Asíncrona
        call_async.enqueue(new Callback<List<BestSeller>>() {

            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             */
            @Override
            public void onResponse(Call<List<BestSeller>> call, Response<List<BestSeller>> response) {
                List<BestSeller> bestSellersList = response.body();
                if (null != bestSellersList) {
                    for (BestSeller bestSeller : bestSellersList) {
                        tvRespuesta.append(bestSeller.toString() + "\n\n");
                    }
                    Log.i(LOG_TAG, "obtenerInfoBestSeller => respuesta=" + bestSellersList);
                } else {
                    tvRespuesta.setText(getString(R.string.strError));
                    Log.i(LOG_TAG, getString(R.string.strError));
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            @Override
            public void onFailure(Call<List<BestSeller>> call, Throwable t) {
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
}