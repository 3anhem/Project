package vn.edu.vtn.quanlybanhang.productCategory;

import android.content.Context;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;

public class ProductCategoryPresenter implements ProductCategoryMvpPresenter {
    ProductCategoryMvpView view;
    Context context;
    APIService service;

    public ProductCategoryPresenter(ProductCategoryMvpView view, Context context) {
        this.context = context;
        this.view = view;
        service = APIUtils.getServer();
    }

    public void getData() {
        ProgressDialogF.showLoading(context);
        service.getProductCategory().enqueue(new Callback<ArrayList<ProductCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductCategory>> call, Response<ArrayList<ProductCategory>> response) {
                if (response.isSuccessful()) {
                    view.onCallBackDataSucess(response.body());
                }
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<ProductCategory>> call, Throwable t) {
                view.onCallBackDataFailure("onFailure " + t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

    @Override
    public void toGetProductList(int id) {
        view.onOpenProductLists(id);
    }
}
