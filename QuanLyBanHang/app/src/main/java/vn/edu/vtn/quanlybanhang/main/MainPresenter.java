package vn.edu.vtn.quanlybanhang.main;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;

public class MainPresenter implements MainMvpPresenter {
    private MainMvpView view;
    private Context context;
    private SharedPrefsHelper sharedPrefsHelper;
    private APIService service;

    public MainPresenter(MainMvpView view, Context context) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        service = APIUtils.getServer();
    }

    @Override
    public void onClickHomePage() {
        view.onOpenHomePage();
    }

    @Override
    public void onProductCategory() {
        view.onOpenProductCategory();
    }

    @Override
    public void onClickOderList() {
        if (sharedPrefsHelper.checklogin()) { //check login ????
            view.onOpenOderList();
        } else {
            view.onOpenLogin();
        }
    }

    @Override
    public void onClickFavoriteProducts() {
        if (sharedPrefsHelper.checklogin()) { //check login ????
            view.onOpenFavoriteProducts();
        } else {
            view.onOpenLogin();
        }

    }

    @Override
    public void onClickProfile() {
        if (sharedPrefsHelper.checklogin()) { //check login ????
            view.onOpenProfile();
        } else {
            view.onOpenLogin();
        }
    }

    @Override
    public void onClickNotification() {
        view.onOpenNotification();
    }

    @Override
    public void onClickSuport() {
        view.onOpenSuport();
    }

    @Override
    public void onSendTokenAndId(String token, String id) {
        ProgressDialogF.showLoading(context);
        service.sendTokenAndId(token, id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Log.d("AAAA", "SUCCESS");
                }
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("AAAA", "Failure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
