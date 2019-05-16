package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import vn.edu.vtn.quanlybanhang.data.model.Product;

public class ProductBillList implements Serializable {
    @SerializedName("sanPham")
    @Expose
    private Product product;
    @SerializedName("soLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("giaKM")
    @Expose
    private Integer giaKM;
    @SerializedName("tongGia")
    @Expose
    private Float tongGia;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(Integer giaKM) {
        this.giaKM = giaKM;
    }

    public Float getTongGia() {
        return tongGia;
    }

    public void setTongGia(Float tongGia) {
        this.tongGia = tongGia;
    }
}
