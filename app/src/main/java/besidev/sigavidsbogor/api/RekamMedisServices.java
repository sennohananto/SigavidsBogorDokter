package besidev.sigavidsbogor.api;

import java.util.List;

import besidev.sigavidsbogor.models.RekamMedis;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Senno Hananto on 27/11/2017.
 */

public interface RekamMedisServices {
    @GET("/RekamMedisM")
    Call<List<RekamMedis>> getRekamMedisMasyarakat(@Query("emailMasyarakat") String emailM);
}
