package vn.edu.vtn.quanlybanhang.data.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class XaPhuong implements Serializable {

    @SerializedName("ma_xa_phuong")
    @Expose
    private String maXaPhuong;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("loai")
    @Expose
    private String loai;
    @SerializedName("ma_quan_huyen")
    @Expose
    private String maQuanHuyen;

    public String getMaXaPhuong() {
        return maXaPhuong;
    }

    public void setMaXaPhuong(String maXaPhuong) {
        this.maXaPhuong = maXaPhuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaQuanHuyen() {
        return maQuanHuyen;
    }

    public void setMaQuanHuyen(String maQuanHuyen) {
        this.maQuanHuyen = maQuanHuyen;
    }
}
