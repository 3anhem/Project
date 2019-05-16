package vn.edu.vtn.quanlybanhang.productfavorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.ProductFavoriteAdapter;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;
import vn.edu.vtn.quanlybanhang.main.MainActivity;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;

public class ProductFavoriteFragment extends Fragment implements ProductFavoriteMvpView {
    RecyclerView rvProductFavorite;
    ProductFavoriteMvpPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_favorite, container, false);
        rvProductFavorite = view.findViewById(R.id.rv_product_favorite);
        presenter = new ProductFavoritePresenter(getContext(), this);
        presenter.onGetData(); // get Data from API
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh mục yêu thích");
        return view;
    }

    @Override
    public void setDataSucess(final ArrayList<ProductFavorite> list) {
        ProductFavoriteAdapter adapter = new ProductFavoriteAdapter(getContext(), list);
        rvProductFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProductFavorite.setAdapter(adapter);
        adapter.setOnItemClickListener(new ProductFavoriteAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Product product = list.get(position).getProduct();
                presenter.onPassDataToDetail(product);
            }
        });
    }

    @Override
    public void setDataFailure(String mess) {
        Log.d("AAAA", mess);
    }

    @Override
    public void setDataEmpty() {
    }

    @Override
    public void setDataToDetail(Product product) {
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra("PRODUCTLIST", product);
        startActivity(intent);
    }
}
