package vn.edu.vtn.quanlybanhang.data.model.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_khach_hang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("ten_khach_hang")
    @Expose
    private String tenKhachHang;
    @SerializedName("so_dt")
    @Expose
    private String soDt;
    @SerializedName("id_tinh")
    @Expose
    private String idTinh;
    @SerializedName("id_quan")
    @Expose
    private String idQuan;
    @SerializedName("id_xa_phuong")
    @Expose
    private String idXaPhuong;
    @SerializedName("dia_chi")
    @Expose
    private String diaChi;
    @SerializedName("loai")
    @Expose
    private Boolean loai;

    public Address() {
    }

    public Address(Integer idKhachHang, String tenKhachHang, String soDt, String idTinh, String idQuan, String idXaPhuong, String diaChi, Boolean loai) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDt = soDt;
        this.idTinh = idTinh;
        this.idQuan = idQuan;
        this.idXaPhuong = idXaPhuong;
        this.diaChi = diaChi;
        this.loai = loai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
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

    public String getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(String idTinh) {
        this.idTinh = idTinh;
    }

    public String getIdQuan() {
        return idQuan;
    }

    public void setIdQuan(String idQuan) {
        this.idQuan = idQuan;
    }

    public String getIdXaPhuong() {
        return idXaPhuong;
    }

    public void setIdXaPhuong(String idXaPhuong) {
        this.idXaPhuong = idXaPhuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }
}
