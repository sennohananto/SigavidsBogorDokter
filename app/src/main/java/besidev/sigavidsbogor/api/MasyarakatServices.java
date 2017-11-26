package besidev.sigavidsbogor.api;

import java.util.HashMap;

import besidev.sigavidsbogor.models.Masyarakat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Senno Hananto on 26/11/2017.
 */

public interface MasyarakatServices {
    @FormUrlEncoded
    @POST("/MasyarakatL")
    Call<Masyarakat> loginMasyarakat(@Field("emailMasyarakat") String emailM, @Field("namaLengkap") String namaLM, @Field("tanggalLahir") String tglLhrM, @Field("gender") String genderM, @Field("gambarURL") String gambarURLM);
}