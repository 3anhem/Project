package vn.edu.vtn.quanlybanhang.data.model.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginFCM {
    @SerializedName("taiKhoan")
    @Expose
    private String username;
    @SerializedName("matKhau")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("device")
    @Expose
    private String device;

    public LoginFCM(String username, String password, String token, String device) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.device = device;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
