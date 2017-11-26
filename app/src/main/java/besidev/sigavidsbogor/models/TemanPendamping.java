package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 26/11/2017.
 */

public class TemanPendamping implements Serializable{

    @SerializedName("idTemanPendamping")
    @Expose
    private String idTemanPendamping;
    @SerializedName("namaLengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("namaPanggilan")
    @Expose
    private String namaPanggilan;
    @SerializedName("noTelp")
    @Expose
    private String noTelp;
    @SerializedName("pinBB")
    @Expose
    private String pinBB;
    @SerializedName("fb")
    @Expose
    private String fb;
    @SerializedName("wilayah")
    @Expose
    private String wilayah;
    @SerializedName("urlGambar")
    @Expose
    private String urlGambar;

    public String getIdTemanPendamping() {
        return idTemanPendamping;
    }

    public void setIdTemanPendamping(String idTemanPendamping) {
        this.idTemanPendamping = idTemanPendamping;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNamaPanggilan() {
        return namaPanggilan;
    }

    public void setNamaPanggilan(String namaPanggilan) {
        this.namaPanggilan = namaPanggilan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getPinBB() {
        return pinBB;
    }

    public void setPinBB(String pinBB) {
        this.pinBB = pinBB;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getUrlGambar() {
        return urlGambar;
    }

    public void setUrlGambar(String urlGambar) {
        this.urlGambar = urlGambar;
    }
}
