package vn.edu.vtn.quanlybanhang.productList;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.PagingProduct;
import vn.edu.vtn.quanlybanhang.data.model.Product;

public class ProductListPresenter implements ProductListMvpPresenter {
    ProductListMvpView view;
    Context context;
    APIService service;

    public ProductListPresenter(ProductListMvpView view, Context context) {
        this.view = view;
        this.context = context;
        service = APIUtils.getServer();
    }

    public void getData(PagingProduct pagingProduct) {
//        Toast.makeText(context, pagingProduct.getSortBy() + " ", Toast.LENGTH_SHORT).show();
        ProgressDialogF.showLoading(context);
        service.getProductLists(pagingProduct).enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                view.onCallBackDataSucess(response.body());
                Log.d("AAAA", response.code() + "");
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                view.onCallBackDataFailure("onFailure " + t.toString());
                Log.d("AAAA", t.toString());
                ProgressDialogF.hideLoading();
            }
        });
    }

}
