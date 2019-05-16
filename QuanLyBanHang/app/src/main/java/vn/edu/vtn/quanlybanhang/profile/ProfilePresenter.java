package vn.edu.vtn.quanlybanhang.profile;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;

public class ProfilePresenter implements ProfileMvpPresenter {
    private Context context;
    private ProfileMvpView view;
    private SharedPrefsHelper sharedPrefsHelper;
    private APIService service;
    private Customer customer;

    public ProfilePresenter(Context context, ProfileMvpView view) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        service = APIUtils.getServer();

        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        customer = new Gson().fromJson(json, Customer.class);
    }

    @Override
    public void onGetView() {
        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        customer = new Gson().fromJson(json, Customer.class);
        view.onSetView(customer);
    }

    @Override
    public void onLogOut() {
        sharedPrefsHelper.setLoggedin(false, null);
        ProgressDialogF.showLoading(context);
        service.logout(customer.getIdKhachHang()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AAAA", response.code() + "");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("AAAA", "Failure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
        view.onSetLogOut();
    }
}
