package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("id_san_pham")
    @Expose
    private Integer idSanPham;
    @SerializedName("id_danh_muc")
    @Expose
    private Integer idDanhMuc;
    @SerializedName("ten_sp")
    @Expose
    private String name;
    @SerializedName("gia_sp")
    @Expose
    private String price;
    @SerializedName("phan_tram_km")
    @Expose
    private String percent;
    @SerializedName("gia_km")
    @Expose
    private String priceSale;
    @SerializedName("so_luong")
    @Expose
    private String amount;
    @SerializedName("mo_ta")
    @Expose
    private String describe;
    @SerializedName("url_hinh_chinh")
    @Expose
    private String image;


    public Product() {
    }

    public Product(String name, String price, String percent, String priceSale, String amount, String describe, String image) {
        this.name = name;
        this.price = price;
        this.percent = percent;
        this.priceSale = priceSale;
        this.amount = amount;
        this.describe = describe;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public Integer getIdDanhMuc() {
        return idDanhMuc;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
