package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductCategory implements Serializable {
    @SerializedName("id_danh_muc")
    @Expose
    private int idDanhMuc;

    @SerializedName("ten_danh_muc")
    @Expose
    private String name;

    @SerializedName("url_hinh")
    @Expose
    private String image;

    public ProductCategory(String name, String image) {
        this.image = image;
        this.name = name;
    }


    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
