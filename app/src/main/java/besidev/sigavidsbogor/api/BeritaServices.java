package besidev.sigavidsbogor.api;

import java.util.List;

import besidev.sigavidsbogor.models.Berita;
import besidev.sigavidsbogor.models.LayananHIV;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Senno Hananto on 24/11/2017.
 */

public interface BeritaServices {
    @GET("/Berita")
    Call<List<Berita>> getBerita();
}
