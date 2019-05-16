package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bill {

    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("idTinhTrang")
    @Expose
    private Integer idTinhTrang;
    @SerializedName("tongTien")
    @Expose
    private Float tongTien;
    @SerializedName("ngayLap")
    @Expose
    private String ngayLap;
    @SerializedName("soDT")
    @Expose
    private String soDT;
    @SerializedName("idNoiNhan")
    @Expose
    private Integer idNoiNhan;
    @SerializedName("ghiChu")
    @Expose
    private String ghiChu;
    @SerializedName("danhSachSanPham")
    @Expose
    private List<ProductBill> productBills = null;

    public Bill() {
    }

    public Bill(Integer idKhachHang, Integer idTinhTrang, Float tongTien, String ngayLap, String soDT, Integer idNoiNhan, String ghiChu, List<ProductBill> productBills) {
        this.idKhachHang = idKhachHang;
        this.idTinhTrang = idTinhTrang;
        this.tongTien = tongTien;
        this.ngayLap = ngayLap;
        this.soDT = soDT;
        this.idNoiNhan = idNoiNhan;
        this.ghiChu = ghiChu;
        this.productBills = productBills;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getIdTinhTrang() {
        return idTinhTrang;
    }

    public void setIdTinhTrang(Integer idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public Integer getIdNoiNhan() {
        return idNoiNhan;
    }

    public void setIdNoiNhan(Integer idNoiNhan) {
        this.idNoiNhan = idNoiNhan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public List<ProductBill> getDanhSachSanPham() {
        return productBills;
    }

    public void setDanhSachSanPham(List<ProductBill> productBills) {
        this.productBills = productBills;
    }
}
