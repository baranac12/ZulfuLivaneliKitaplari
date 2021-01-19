package com.h5190032_baran_can_anac_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//bu classda githubdaki json dosyamızdan veri çekmek için değişkenler oluşturuldu.
//daha sonrasında çekilen verileri set ve get edilicek değişkenler belirtildi.
public class Kitap {

    @SerializedName("adi")
    @Expose
    private String adi;
    @SerializedName("yazar")
    @Expose
    private String yazar;
    @SerializedName("sayfaSayisi")
    @Expose
    private String sayfaSayisi;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("yayinlanmaTarihi")
    @Expose
    private String yayinlanmaTarihi;
    @SerializedName("dil")
    @Expose
    private String dil;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;
    @SerializedName("logoURL")
    @Expose
    private String logoURL;
    @SerializedName("kapakURL")
    @Expose
    private String kapakURL;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getSayfaSayisi() {
        return sayfaSayisi;
    }

    public void setSayfaSayisi(String sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getYayinlanmaTarihi() {
        return yayinlanmaTarihi;
    }

    public void setYayinlanmaTarihi(String yayinlanmaTarihi) {
        this.yayinlanmaTarihi = yayinlanmaTarihi;
    }

    public String getDil() {
        return dil;
    }

    public void setDil(String dil) {
        this.dil = dil;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getKapakURL() {
        return kapakURL;
    }

    public void setKapakURL(String kapakURL) {
        this.kapakURL = kapakURL;
    }

}
