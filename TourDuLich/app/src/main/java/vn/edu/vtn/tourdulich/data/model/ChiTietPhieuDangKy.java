package vn.edu.vtn.tourdulich.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChiTietPhieuDangKy implements Serializable {
    @SerializedName("ID")
    @Expose
    private int ID;
    @SerializedName("SOPHIEU")
    @Expose
    private int SoPhieu;
    @SerializedName("MATOUR")
    @Expose
    private int MaTour;
    @SerializedName("SONGUOI")
    @Expose
    private int SoNguoi;

    public ChiTietPhieuDangKy() {
    }

    public ChiTietPhieuDangKy(int ID, int maTour, int soNguoi) {
        this.ID = ID;
        MaTour = maTour;
        SoNguoi = soNguoi;
    }

    public int getID() {
        return ID;
    }

    public int getSoPhieu() {
        return SoPhieu;
    }

    public int getMaTour() {
        return MaTour;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSoPhieu(int SoPhieu) {
        this.SoPhieu = SoPhieu;
    }

    public void setMaTour(int MaTour) {
        this.MaTour = MaTour;
    }

    public void setSoNguoi(int SoNguoi) {
        this.SoNguoi = SoNguoi;
    }
}
