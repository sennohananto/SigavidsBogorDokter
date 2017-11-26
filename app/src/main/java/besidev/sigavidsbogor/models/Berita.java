package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 25/11/2017.
 */

public class Berita implements Serializable {

    @SerializedName("idBerita")
    @Expose
    private String idBerita;
    @SerializedName("judulBerita")
    @Expose
    private String judulBerita;
    @SerializedName("urlBerita")
    @Expose
    private String urlBerita;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    public String getIdBerita() {
        return idBerita;
    }

    public void setIdBerita(String idBerita) {
        this.idBerita = idBerita;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getUrlBerita() {
        return urlBerita;
    }

    public void setUrlBerita(String urlBerita) {
        this.urlBerita = urlBerita;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
