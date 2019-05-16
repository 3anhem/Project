package vn.edu.vtn.quanlybanhang.pay.newAddress;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.address.Address;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.address.District;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;

public class NewAddressPresenter implements NewAddressMvpPresenter {
    private NewAddressMvpView view;
    private Context context;
    private APIService service;
    private SharedPrefsHelper sharedPrefsHelper;

    public NewAddressPresenter(Context context, NewAddressMvpView view) {
        this.context = context;
        this.view = view;
        service = APIUtils.getServer();
        sharedPrefsHelper = new SharedPrefsHelper(context);
    }


    @Override
    public void getData(String tenKhachHang, String soDt, String idTinh, String idQuan, String idXaPhuong, String diaChi, Boolean loai) {
        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        Customer customer = new Gson().fromJson(json, Customer.class);

        Address address = new Address(customer.getIdKhachHang(), tenKhachHang, soDt, idTinh, idQuan, idXaPhuong, diaChi, loai);
        ProgressDialogF.showLoading(context);
        service.CreateNewAddress(address).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                Log.d("AAAA", "Success");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {
                Log.d("AAAA", "Failure");
                ProgressDialogF.hideLoading();
            }
        });
    }

    @Override
    public void openProvinceDialog() {
        ProgressDialogF.showLoading(context);
        service.getProvinceLists().enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                view.OnopenProvinceDialog(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });

    }

    @Override
    public void openDistricDialog(String provinceCode) {
        ProgressDialogF.showLoading(context);
        service.getDistrictLists(provinceCode).enqueue(new Callback<ArrayList<District>>() {
            @Override
            public void onResponse(Call<ArrayList<District>> call, Response<ArrayList<District>> response) {
                view.OnopenDistrictDialog(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<District>> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });

    }

    @Override
    public void openXaPhuongDialog(String districtCode) {
        ProgressDialogF.showLoading(context);
        service.getXaPhuongLists(districtCode).enqueue(new Callback<ArrayList<XaPhuong>>() {
            @Override
            public void onResponse(Call<ArrayList<XaPhuong>> call, Response<ArrayList<XaPhuong>> response) {
                view.OnopenXaPhuongDialog(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<XaPhuong>> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
