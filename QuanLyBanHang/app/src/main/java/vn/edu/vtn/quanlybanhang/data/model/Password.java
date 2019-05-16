package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Password {
    @SerializedName("idKhachHang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("matKhauCu")
    @Expose
    private String oldPassword;
    @SerializedName("matKhauMoi")
    @Expose
    private String newPassword;

    public Password(Integer idKhachHang, String oldPassword, String newPassword) {
        this.idKhachHang = idKhachHang;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
