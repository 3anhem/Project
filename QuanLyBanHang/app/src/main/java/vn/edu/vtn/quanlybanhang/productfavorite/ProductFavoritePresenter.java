package vn.edu.vtn.quanlybanhang.productfavorite;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;

public class ProductFavoritePresenter implements ProductFavoriteMvpPresenter {
    private Context context;
    private ProductFavoriteMvpView view;
    private SharedPrefsHelper sharedPrefsHelper;
    private APIService service;

    public ProductFavoritePresenter(Context context, ProductFavoriteMvpView view) {
        this.context = context;
        this.view = view;
        sharedPrefsHelper = new SharedPrefsHelper(context);
        service = APIUtils.getServer();
    }

    @Override
    public void onGetData() {
        String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
        Customer customer = new Gson().fromJson(json, Customer.class);
        ProgressDialogF.showLoading(context);
        service.getProductFavorite(customer.getIdKhachHang()).enqueue(new Callback<ArrayList<ProductFavorite>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductFavorite>> call, Response<ArrayList<ProductFavorite>> response) {
                if (response.code() == 200) {
                    view.setDataSucess(response.body());
                } else {
                    view.setDataEmpty();
                }
                ProgressDialogF.hideLoading();

            }

            @Override
            public void onFailure(Call<ArrayList<ProductFavorite>> call, Throwable t) {
                view.setDataFailure(t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

    @Override
    public void onPassDataToDetail(Product product) {
        view.setDataToDetail(product);
    }
}
