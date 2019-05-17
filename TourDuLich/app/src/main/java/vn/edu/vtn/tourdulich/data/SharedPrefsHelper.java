package vn.edu.vtn.tourdulich.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import vn.edu.vtn.tourdulich.data.model.Admin;

public class SharedPrefsHelper {
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public SharedPrefsHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("Splash", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedin(boolean loggedin, Admin admin) {
        editor.putBoolean("log", loggedin);
        String json = new Gson().toJson(admin);
        editor.putString("Admin", json);
        editor.commit();
    }

    public boolean checklogin() {
        return sharedPreferences.getBoolean("log", false);
    }
}
