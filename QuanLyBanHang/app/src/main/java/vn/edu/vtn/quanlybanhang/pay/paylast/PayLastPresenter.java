package vn.edu.vtn.quanlybanhang.pay.paylast;

import android.content.Context;

import com.google.gson.Gson;

import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.data.sqlite.SqliteController;

public class PayLastPresenter implements PayLastMvpPresenter {
    private Context context;
    private PayLastMvpView view;
    private SharedPrefsHelper sharedPrefsHelper;
    private SqliteController sqliteController;

    public PayLastPresenter(Context context, PayLastMvpView view) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        sqliteController = new SqliteController(context);
    }

    @Override
    public void toProcessAdress() {
        String json = sharedPrefsHelper.sharedPreferences.getString("ADDRESS", "");
        AddressLists addressLists = new Gson().fromJson(json, AddressLists.class);
        view.setDataAdress(addressLists);
    }

    @Override
    public void toProcessCart() {
        view.setDataCart(sqliteController.getDataCart(), sqliteController.getTotalMoney());
    }
}
