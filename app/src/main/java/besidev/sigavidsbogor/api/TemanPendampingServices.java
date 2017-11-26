package besidev.sigavidsbogor.api;

import java.util.List;

import besidev.sigavidsbogor.models.LayananHIV;
import besidev.sigavidsbogor.models.TemanPendamping;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Senno Hananto on 26/11/2017.
 */

public interface TemanPendampingServices {
    @GET("/TemanPendamping")
    Call<List<TemanPendamping>> getTemanPendamping();
}
