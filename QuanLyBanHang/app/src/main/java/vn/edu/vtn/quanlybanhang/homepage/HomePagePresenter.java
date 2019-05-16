package vn.edu.vtn.quanlybanhang.homepage;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.ListRVProduct;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;
import vn.edu.vtn.quanlybanhang.data.model.SaleLists;

public class HomePagePresenter implements HomePageMvpPresenter {
    private HomePageMvpView view;
    private Context context;
    private APIService service;

    public HomePagePresenter(HomePageMvpView view, Context context) {
        this.view = view;
        this.context = context;
        service = APIUtils.getServer();
    }

    public void getDataProductCategory() {
        ProgressDialogF.showLoading(context);
        service.getProductCategory().enqueue(new Callback<ArrayList<ProductCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductCategory>> call, Response<ArrayList<ProductCategory>> response) {
                if (response.isSuccessful()) {
                    view.onCallBackCategorySucess(response.body());
                    ProgressDialogF.hideLoading();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductCategory>> call, Throwable t) {
                view.onCallBackCategoryFailure("onFailure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

    public void getDataNestedProduct() {
        ProgressDialogF.showLoading(context);
        service.getProduct().enqueue(new Callback<ArrayList<ListRVProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<ListRVProduct>> call, Response<ArrayList<ListRVProduct>> response) {
                view.onCallBackProductSucess(response.body());
                Log.d("TAG ERROR", response.body().size() + "");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<ListRVProduct>> call, Throwable t) {
                view.onCallBackProductFailure("onFailure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });

    }

    @Override
    public void toGetProductList(int id) {
        view.onOpenProductLists(id);
    }

    @Override
    public void toOpenProductCategory() {
        view.onOpenProductCategory();
    }

    @Override
    public void getDataSaleList() {
        ProgressDialogF.showLoading(context);
        service.getSaleLists().enqueue(new Callback<ArrayList<SaleLists>>() {
            @Override
            public void onResponse(Call<ArrayList<SaleLists>> call, Response<ArrayList<SaleLists>> response) {
                view.onCallBackSaleListsSucess(response.body());
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<SaleLists>> call, Throwable t) {
                view.onCallBackSaleListsFailure(t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }
}
