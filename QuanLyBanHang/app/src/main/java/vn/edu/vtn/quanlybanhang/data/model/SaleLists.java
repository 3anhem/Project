package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleLists {
    @SerializedName("id_khuyen_mai")
    @Expose
    private Integer idKhuyenMai;
    @SerializedName("ten_km")
    @Expose
    private String tenKm;
    @SerializedName("phan_tram_km")
    @Expose
    private Float phanTramKm;
    @SerializedName("t_bat_dau")
    @Expose
    private String tBatDau;
    @SerializedName("t_ket_thuc")
    @Expose
    private String tKetThuc;
    @SerializedName("url_hinh")
    @Expose
    private String urlHinh;

    public Integer getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(Integer idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public String getTenKm() {
        return tenKm;
    }

    public void setTenKm(String tenKm) {
        this.tenKm = tenKm;
    }

    public Float getPhanTramKm() {
        return phanTramKm;
    }

    public void setPhanTramKm(Float phanTramKm) {
        this.phanTramKm = phanTramKm;
    }

    public String getTBatDau() {
        return tBatDau;
    }

    public void setTBatDau(String tBatDau) {
        this.tBatDau = tBatDau;
    }

    public String getTKetThuc() {
        return tKetThuc;
    }

    public void setTKetThuc(String tKetThuc) {
        this.tKetThuc = tKetThuc;
    }

    public String getUrlHinh() {
        return urlHinh;
    }

    public void setUrlHinh(String urlHinh) {
        this.urlHinh = urlHinh;
    }
}
