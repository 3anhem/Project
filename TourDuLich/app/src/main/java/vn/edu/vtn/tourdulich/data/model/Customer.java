package vn.edu.vtn.tourdulich.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tuan Dau on 5/12/2019.
 */

public class Customer {
    @SerializedName("MAKH")
    @Expose
    public String customerCode;
    @SerializedName("DIACHI")
    @Expose
    public String address;
    @SerializedName("TENKH")
    @Expose
    public String customerName;

    public Customer() {
    }

    public Customer(String customerCode, String address, String customerName) {
        this.customerCode = customerCode;
        this.address = address;
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
