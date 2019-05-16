package vn.edu.vtn.quanlybanhang.data.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Province implements Serializable {
    @SerializedName("ma_tinh")
    @Expose
    private String maTinh;
    @SerializedName("ten")
    @Expose
    private String ten;
    @SerializedName("loai")
    @Expose
    private String loai;

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
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
}
