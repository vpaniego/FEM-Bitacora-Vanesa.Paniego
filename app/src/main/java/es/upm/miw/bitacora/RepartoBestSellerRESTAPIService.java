package es.upm.miw.bitacora;

import java.util.List;

import es.upm.miw.bitacora.models.BestSeller;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

@SuppressWarnings("Unused")
interface RepartoBestSellerRESTAPIService {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/books/v3/lists/best-sellers/history.json?api-key=2ae3e47fef9844b5a3ee16e3be52e734")
    Call<List<BestSeller>> getBestSellers();

}
