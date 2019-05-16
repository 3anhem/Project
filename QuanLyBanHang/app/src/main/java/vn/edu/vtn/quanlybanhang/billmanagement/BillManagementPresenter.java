package vn.edu.vtn.quanlybanhang.billmanagement;

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
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.bill.BillList;
import vn.edu.vtn.quanlybanhang.data.model.bill.PagingBill;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;

public class BillManagementPresenter implements BillManagementMvpPresenter {
    private Context context;
    private BillManagementMvpView view;
    private SharedPrefsHelper sharedPrefsHelper;
    private APIService service;

    public BillManagementPresenter(Context context, BillManagementMvpView view) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        service = APIUtils.getServer();
    }

    @Override
    public void onGetData() {
        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        Customer customer = new Gson().fromJson(json, Customer.class);
        PagingBill bill = new PagingBill(customer.getIdKhachHang(), 1, 10);
        ProgressDialogF.showLoading(context);
        service.getListBill(bill).enqueue(new Callback<ArrayList<BillList>>() {
            @Override
            public void onResponse(Call<ArrayList<BillList>> call, Response<ArrayList<BillList>> response) {
                view.onSetData(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<BillList>> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });

    }

    @Override
    public void onGetDataDetail(int id) {
        ProgressDialogF.showLoading(context);
        service.getListBillDetail(id).enqueue(new Callback<ProductBillDetail>() {
            @Override
            public void onResponse(Call<ProductBillDetail> call, Response<ProductBillDetail> response) {
                view.onsetDataDetail(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ProductBillDetail> call, Throwable t) {
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
