package vn.edu.vtn.tourdulich.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChiTiet {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("SOPHIEU")
    @Expose
    private Integer soPhieu;
    @SerializedName("MATOUR")
    @Expose
    private Integer maTour;
    @SerializedName("SONGUOI")
    @Expose
    private Integer soNguoi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(Integer soPhieu) {
        this.soPhieu = soPhieu;
    }

    public Integer getMaTour() {
        return maTour;
    }

    public void setMaTour(Integer maTour) {
        this.maTour = maTour;
    }

    public Integer getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(Integer soNguoi) {
        this.soNguoi = soNguoi;
    }
}
