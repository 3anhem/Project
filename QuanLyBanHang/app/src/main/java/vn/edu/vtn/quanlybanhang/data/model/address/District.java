package vn.edu.vtn.quanlybanhang.data.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class District implements Serializable {

    @SerializedName("ma_quan_huyen")
    @Expose
    private String maQuanHuyen;
    @SerializedName("ten_quan_huyen")
    @Expose
    private String tenQuanHuyen;
    @SerializedName("loai")
    @Expose
    private String loai;
    @SerializedName("ma_tinh")
    @Expose
    private String maTinh;

    public District() {
    }

    public District(String tenQuanHuyen, String loai, String maTinh) {
        this.tenQuanHuyen = tenQuanHuyen;
        this.loai = loai;
        this.maTinh = maTinh;
    }

    public String getMaQuanHuyen() {
        return maQuanHuyen;
    }

    public void setMaQuanHuyen(String maQuanHuyen) {
        this.maQuanHuyen = maQuanHuyen;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

}
