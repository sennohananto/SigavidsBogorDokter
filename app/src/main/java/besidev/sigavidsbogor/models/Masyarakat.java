package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 26/11/2017.
 */

public class Masyarakat implements Serializable{

    @SerializedName("emailMasyarakat")
    @Expose
    private String emailMasyarakat;
    @SerializedName("namaLengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("tanggalLahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("gambarURL")
    @Expose
    private String gambarURL;

    public String getEmailMasyarakat() {
        return emailMasyarakat;
    }

    public void setEmailMasyarakat(String emailMasyarakat) {
        this.emailMasyarakat = emailMasyarakat;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGambarURL() {
        return gambarURL;
    }

    public void setGambarURL(String gambarURL) {
        this.gambarURL = gambarURL;
    }
}
