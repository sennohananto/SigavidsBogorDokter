package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 24/11/2017.
 */

public class LayananHIV implements Serializable{
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("namalayanan")
    @Expose
    private String namalayanan;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("notelepon")
    @Expose
    private String notelepon;
    @SerializedName("jambuka")
    @Expose
    private String jambuka;
    @SerializedName("biaya")
    @Expose
    private String biaya;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamalayanan() {
        return namalayanan;
    }

    public void setNamalayanan(String namalayanan) {
        this.namalayanan = namalayanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNotelepon() {
        return notelepon;
    }

    public void setNotelepon(String notelepon) {
        this.notelepon = notelepon;
    }

    public String getJambuka() {
        return jambuka;
    }

    public void setJambuka(String jambuka) {
        this.jambuka = jambuka;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }
}
