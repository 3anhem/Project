package vn.edu.vtn.quanlybanhang.data.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AddressLists implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer idDiaChiKhachHang;
    @SerializedName("diaChi")
    @Expose
    private String tenDiaChi;
    @SerializedName("tenKhachHang")
    @Expose
    private String tenKhachHang;
    @SerializedName("soDT")
    @Expose
    private String soDt;
    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("loai")
    @Expose
    private Boolean loai;
    @SerializedName("idTinh")
    @Expose
    private String idTinh;
    @SerializedName("idQuanHuyen")
    @Expose
    private String idQuanHuyen;
    @SerializedName("idXaPhuong")
    @Expose
    private String idXaPhuong;

    @SerializedName("tenXaPhuong")
    @Expose
    private String tenXaPhuong;
    @SerializedName("tenQuanHuyen")
    @Expose
    private String tenQuanHuyen;
    @SerializedName("tenTinh")
    @Expose
    private String tenTinh;

    public AddressLists() {
    }

    public AddressLists(Integer id, String tenDiaChi, String soDt, Integer idKhachHang, String tenKhachHang, String idTinh, String idQuanHuyen, String idXaPhuong) {
        this.tenDiaChi = tenDiaChi;
        this.soDt = soDt;
        this.idKhachHang = idKhachHang;
        this.idTinh = idTinh;
        this.idQuanHuyen = idQuanHuyen;
        this.idXaPhuong = idXaPhuong;
        this.tenKhachHang = tenKhachHang;
        this.idDiaChiKhachHang = id;

    }

    public String getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(String idTinh) {
        this.idTinh = idTinh;
    }

    public String getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(String idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    public String getIdXaPhuong() {
        return idXaPhuong;
    }

    public void setIdXaPhuong(String idXaPhuong) {
        this.idXaPhuong = idXaPhuong;
    }

    public String getTenXaPhuong() {
        return tenXaPhuong;
    }

    public void setTenXaPhuong(String tenXaPhuong) {
        this.tenXaPhuong = tenXaPhuong;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public Integer getIdDiaChiKhachHang() {
        return idDiaChiKhachHang;
    }

    public void setIdDiaChiKhachHang(Integer idDiaChiKhachHang) {
        this.idDiaChiKhachHang = idDiaChiKhachHang;
    }

    public String getTenDiaChi() {
        return tenDiaChi;
    }

    public void setTenDiaChi(String tenDiaChi) {
        this.tenDiaChi = tenDiaChi;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDt() {
        return soDt;
    }

    public void setSoDt(String soDt) {
        this.soDt = soDt;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }
}
