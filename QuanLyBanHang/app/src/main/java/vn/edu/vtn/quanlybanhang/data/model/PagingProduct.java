package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PagingProduct {
    @SerializedName("id")
    @Expose
    private int idDanhMuc;
    @SerializedName("trang")
    @Expose
    private int page;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("locTheo")
    @Expose
    private int sortBy;

    public PagingProduct() {

    }

    public PagingProduct(int idDanhMuc, int page, int size, int sortBy) {
        this.idDanhMuc = idDanhMuc;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
