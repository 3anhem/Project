package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductBillDetail implements Serializable {
    @SerializedName("idDonDatHang")
    @Expose
    private Integer idDonDatHang;
    @SerializedName("trangThai")
    @Expose
    private String trangThai;
    @SerializedName("tenNguoiNhan")
    @Expose
    private String tenNguoiNhan;
    @SerializedName("soDT")
    @Expose
    private String soDT;
    @SerializedName("diaChi")
    @Expose
    private String diaChi;
    @SerializedName("ngayLap")
    @Expose
    private String ngayLap;
    @SerializedName("danhSachHang")
    @Expose
    private List<ProductBillList> list = null;

    public Integer getIdDonDatHang() {
        return idDonDatHang;
    }

    public void setIdDonDatHang(Integer idDonDatHang) {
        this.idDonDatHang = idDonDatHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
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

    public List<ProductBillList> getList() {
        return list;
    }

    public void setList(List<ProductBillList> list) {
        this.list = list;
    }
}
