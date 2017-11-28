package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 28/11/2017.
 */

public class TanyaJawab implements Serializable{
    @SerializedName("idTanyaJawab")
    @Expose
    private String idTanyaJawab;
    @SerializedName("judulTanyaJawab")
    @Expose
    private String judulTanyaJawab;
    @SerializedName("isiTanyaJawab")
    @Expose
    private String isiTanyaJawab;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("terpublikasi")
    @Expose
    private String terpublikasi;
    @SerializedName("emailMasyarakat")
    @Expose
    private String emailMasyarakat;
    @SerializedName("waktuLogTanyaJawab")
    @Expose
    private String waktuLogTanyaJawab;
    @SerializedName("emailDokter")
    @Expose
    private String emailDokter;
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
    @SerializedName("instansi")
    @Expose
    private String instansi;

    public String getIdTanyaJawab() {
        return idTanyaJawab;
    }

    public void setIdTanyaJawab(String idTanyaJawab) {
        this.idTanyaJawab = idTanyaJawab;
    }

    public String getJudulTanyaJawab() {
        return judulTanyaJawab;
    }

    public void setJudulTanyaJawab(String judulTanyaJawab) {
        this.judulTanyaJawab = judulTanyaJawab;
    }

    public String getIsiTanyaJawab() {
        return isiTanyaJawab;
    }

    public void setIsiTanyaJawab(String isiTanyaJawab) {
        this.isiTanyaJawab = isiTanyaJawab;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getTerpublikasi() {
        return terpublikasi;
    }

    public void setTerpublikasi(String terpublikasi) {
        this.terpublikasi = terpublikasi;
    }

    public String getEmailMasyarakat() {
        return emailMasyarakat;
    }

    public void setEmailMasyarakat(String emailMasyarakat) {
        this.emailMasyarakat = emailMasyarakat;
    }

    public String getWaktuLogTanyaJawab() {
        return waktuLogTanyaJawab;
    }

    public void setWaktuLogTanyaJawab(String waktuLogTanyaJawab) {
        this.waktuLogTanyaJawab = waktuLogTanyaJawab;
    }

    public String getEmailDokter() {
        return emailDokter;
    }

    public void setEmailDokter(String emailDokter) {
        this.emailDokter = emailDokter;
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

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
}
