package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductBill {
    @SerializedName("idSanPham")
    @Expose
    private Integer idSanPham;
    @SerializedName("soLuong")
    @Expose
    private Integer soLuong;
    @SerializedName("giaKM")
    @Expose
    private Float giaKM;
    @SerializedName("tongGia")
    @Expose
    private Float tongGia;

    public ProductBill() {
    }

    public ProductBill(Integer idSanPham, Integer soLuong, Float giaKM, Float tongGia) {
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
        this.giaKM = giaKM;
        this.tongGia = tongGia;
    }

    public Integer getIdSanPhan() {
        return idSanPham;
    }

    public void setIdSanPhan(Integer idSanPhan) {
        this.idSanPham = idSanPhan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(Float giaKM) {
        this.giaKM = giaKM;
    }

    public Float getTongGia() {
        return tongGia;
    }

    public void setTongGia(Float tongGia) {
        this.tongGia = tongGia;
    }

}
