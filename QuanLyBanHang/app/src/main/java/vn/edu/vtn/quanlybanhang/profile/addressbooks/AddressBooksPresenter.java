package vn.edu.vtn.quanlybanhang.profile.addressbooks;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.address.Address;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.pay.address.AddressMvpView;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;

public class AddressBooksPresenter implements AddressBooksMvpPresenter {
    private Context context;
    private SharedPrefsHelper sharedPrefsHelper;
    private AddressBooksMvpView view;
    private APIService service;

    public AddressBooksPresenter(Context context, AddressBooksMvpView view) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        service = APIUtils.getServer();
    }

    @Override
    public void callData() {
        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        Customer customer = new Gson().fromJson(json, Customer.class);
        ProgressDialogF.showLoading(context);
        service.getAddressLists(customer.getIdKhachHang()).enqueue(new Callback<ArrayList<AddressLists>>() {
            @Override
            public void onResponse(Call<ArrayList<AddressLists>> call, Response<ArrayList<AddressLists>> response) {
                if (response.body() == null) {
                    view.onOpenNewAddress();
                    ProgressDialogF.hideLoading();
                    return;
                }
                view.onLoadDataSucess(response.body());
                Log.d("AAAA", "SIze call : " + response.body().size());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<AddressLists>> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

    @Override
    public void onDeleteAddress(Integer idDiaChiKhachHang) {
        ProgressDialogF.showLoading(context);
        Log.d("AAAAID", idDiaChiKhachHang + "");
        service.deleteAdress(idDiaChiKhachHang).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AAAA", response.code() + "");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("AAAA", "onFailure + " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
