package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillList {
    @SerializedName("id")
    @Expose
    private Integer idDonHang;
    @SerializedName("KhachHang")
    @Expose
    private String khachHang;
    @SerializedName("TinhTrang")
    @Expose
    private String tinhTrang;
    @SerializedName("tongGia")
    @Expose
    private Float tongGia;
    @SerializedName("soDT")
    @Expose
    private String soDT;
    @SerializedName("ghiChu")
    @Expose
    private String ghiChu;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("ngayLap")
    @Expose
    private String ngayLap;

    public Integer getId() {
        return idDonHang;
    }

    public void setId(Integer id) {
        this.idDonHang = id;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Float getTongGia() {
        return tongGia;
    }

    public void setTongGia(Float tongGia) {
        this.tongGia = tongGia;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
}
