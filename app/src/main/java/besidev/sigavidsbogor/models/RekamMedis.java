package besidev.sigavidsbogor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Senno Hananto on 27/11/2017.
 */

public class RekamMedis implements Serializable{

    @SerializedName("idRekamMedis")
    @Expose
    private String idRekamMedis;
    @SerializedName("keperluan")
    @Expose
    private String keperluan;
    @SerializedName("diagnosa")
    @Expose
    private String diagnosa;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tindakan")
    @Expose
    private String tindakan;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("emailDokter")
    @Expose
    private String emailDokter;
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
    @SerializedName("instansi")
    @Expose
    private String instansi;

    public String getIdRekamMedis() {
        return idRekamMedis;
    }

    public void setIdRekamMedis(String idRekamMedis) {
        this.idRekamMedis = idRekamMedis;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getEmailDokter() {
        return emailDokter;
    }

    public void setEmailDokter(String emailDokter) {
        this.emailDokter = emailDokter;
    }

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

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
}
