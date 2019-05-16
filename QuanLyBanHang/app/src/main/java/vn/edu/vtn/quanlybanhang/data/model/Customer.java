package vn.edu.vtn.quanlybanhang.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {
    @SerializedName("id_khach_hang")
    @Expose
    private Integer idKhachHang;
    @SerializedName("tai_khoan")
    @Expose
    private String username;
    @SerializedName("mat_khau")
    @Expose
    private String password;
    @SerializedName("ten_nguoi_dung")
    @Expose
    private String name;
    @SerializedName("so_dt")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gioi_tinh")
    @Expose
    private String sex;
    @SerializedName("ngay_sinh")
    @Expose
    private String birthday;
    @SerializedName("t_dang_ky")
    @Expose
    private String registrationDate;
    @SerializedName("tong_don_hang")
    @Expose
    private Integer billSuccess;
    @SerializedName("tong_gia")
    @Expose
    private Float totalPrice;
    @SerializedName("so_san_pham")
    @Expose
    private Integer productSucess;


    public Customer() {
    }

    public Customer(int idKhachHang, String name, String birthday, String sex) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.idKhachHang = idKhachHang;
    }

    public Customer(String username, String password, String name, String phone, String email, String sex, String birthday, String registrationDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
    }

    public Integer getBillSuccess() {
        return billSuccess;
    }

    public void setBillSuccess(Integer billSuccess) {
        this.billSuccess = billSuccess;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getProductSucess() {
        return productSucess;
    }

    public void setProductSucess(Integer productSucess) {
        this.productSucess = productSucess;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
