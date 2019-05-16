package vn.edu.vtn.quanlybanhang.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public class SharedPrefsHelper {
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPrefsHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Splash", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedin(boolean loggedin, Customer customer) {
        editor.putBoolean("log", loggedin);
        String json = new Gson().toJson(customer);
        editor.putString("CUSTOMER", json);
        editor.commit();
    }

    public boolean checklogin() {
        return sharedPreferences.getBoolean("log", false);
    }

    public void setData(AddressLists addressLists) {
        String json = new Gson().toJson(addressLists);
        editor.putString("ADDRESS", json);
        editor.commit();
    }

    public void setDataCustomer(Customer customer) {
        String json = new Gson().toJson(customer);
        editor.putString("CUSTOMER", json);
        editor.commit();
    }

    public void setTokenAndId(String token, String id) {
        editor.putString("TOKEN", token);
        editor.putString("ID", id);
        editor.commit();
    }

    public void setDatabaseName(boolean result) {
        editor.putBoolean("DBName", result);
        editor.commit();
    }
}
