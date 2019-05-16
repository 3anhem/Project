package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListRVProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tenDanhMuc")
    @Expose
    private String headerTitle;
    @SerializedName("sanPham")
    @Expose
    private ArrayList<Product> productsList = null;


    public ListRVProduct() {
    }

    public ListRVProduct(String headerTitle, ArrayList<Product> productsList) {
        this.headerTitle = headerTitle;
        this.productsList = productsList;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }
}
