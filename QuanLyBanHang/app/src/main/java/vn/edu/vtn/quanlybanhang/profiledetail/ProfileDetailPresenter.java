package vn.edu.vtn.quanlybanhang.profiledetail;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.Password;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;

public class ProfileDetailPresenter implements ProfileDetailMvpPresenter {
    private Context context;
    private ProfileDetailMvpView view;
    private APIService service;
    private SharedPrefsHelper sharedPrefsHelper;

    public ProfileDetailPresenter(Context context, ProfileDetailMvpView view) {
        this.context = context;
        this.view = view;
        service = APIUtils.getServer();
        sharedPrefsHelper = new SharedPrefsHelper(context);
    }

    @Override
    public void onUpdateProfile(Customer customer) {
        ProgressDialogF.showLoading(context);
        service.updateInfo(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                view.onUpdateSuccess(response.code() + "");
                sharedPrefsHelper.setDataCustomer(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                view.onUpdateFailure("onFailure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

    @Override
    public void onChangePass(Password password) {
        ProgressDialogF.showLoading(context);
        service.changePassword(password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AAAABB", response.code() + "");
                if (response.code() == 204) {
                    view.onCheckPassFailure("Mật khẩu cũ không đúng !!!");
                }
                if (response.code() == 200) {
                    view.onCheckPassSucess("Thành công !!!");
                }
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.onCheckPassFailure("Failure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
