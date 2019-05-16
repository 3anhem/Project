package vn.edu.vtn.quanlybanhang.pay.updateAdress;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.model.address.District;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;

public class UpdateAdressPresenter implements UpdateAdressMvpPresenter {
    private UpdateAdressMvpView view;
    private Context context;
    private APIService service;
    private SharedPrefsHelper sharedPrefsHelper;

    public UpdateAdressPresenter(Context context, UpdateAdressMvpView view) {
        this.context = context;
        this.view = view;
        service = APIUtils.getServer();
        sharedPrefsHelper = new SharedPrefsHelper(context);
    }

    @Override
    public void getData(AddressLists addressLists) {
        ProgressDialogF.showLoading(context);
        Log.d("AAAA", addressLists.getIdDiaChiKhachHang() + " " + addressLists.getIdKhachHang() + " " + addressLists.getTenKhachHang() + " " + addressLists.getIdTinh());
        service.updateAdress(addressLists).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AAAA", response.code() + "");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("AAAA", t.toString());
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
