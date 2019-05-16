package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PagingBill {
    @SerializedName("id")
    @Expose
    private int idKhachHang;
    @SerializedName("trang")
    @Expose
    private int page;
    @SerializedName("size")
    @Expose
    private int size;

    public PagingBill() {

    }

    public PagingBill(int idKhachHang, int page, int size) {
        this.idKhachHang = idKhachHang;
        this.page = page;
        this.size = size;
    }

    public int getIdDanhMuc() {
        return idKhachHang;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idKhachHang = idDanhMuc;
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
