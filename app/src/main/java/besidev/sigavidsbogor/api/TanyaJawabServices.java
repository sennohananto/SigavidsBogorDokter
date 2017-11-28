package besidev.sigavidsbogor.api;

import java.util.List;

import besidev.sigavidsbogor.models.RekamMedis;
import besidev.sigavidsbogor.models.TanyaJawab;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Senno Hananto on 28/11/2017.
 */

public interface TanyaJawabServices {
    @GET("/TanyaJawabPublik")
    Call<List<TanyaJawab>> getTanyaJawabPublik();

    @GET("/TanyaJawabSaya")
    Call<List<TanyaJawab>> getTanyaJawabSaya(@Query("emailMasyarakat") String emailM);
}
