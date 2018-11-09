package es.upm.miw.bitacora;

import es.upm.miw.bitacora.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@SuppressWarnings("Unused")
interface BookNYTResultRESTAPIService {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("svc/books/v3/lists/best-sellers/history.json?api-key=2ae3e47fef9844b5a3ee16e3be52e734")
    Call<Result> getBestSellerByIsbn(@Query("isbn") String isbn);
}
