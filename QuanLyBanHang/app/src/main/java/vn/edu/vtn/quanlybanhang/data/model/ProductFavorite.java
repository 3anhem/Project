package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductFavorite {

    @SerializedName("id_yeu_thich")
    @Expose
    private Integer idYeuThich;
    @SerializedName("id_san_pham")
    @Expose
    private Integer idSanPham;
    @SerializedName("id_khach_hang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("sanPham")
    @Expose
    private Product product;

    public ProductFavorite(Integer idSanPham, Integer idKhachHang) {
        this.idSanPham = idSanPham;
        this.idKhachHang = idKhachHang;
    }

    public ProductFavorite(Integer idSanPham, Integer idKhachHang, Product product) {
        this.idSanPham = idSanPham;
        this.idKhachHang = idKhachHang;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getIdYeuThich() {
        return idYeuThich;
    }

    public void setIdYeuThich(Integer idYeuThich) {
        this.idYeuThich = idYeuThich;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }
}
