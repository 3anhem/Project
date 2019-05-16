package vn.edu.vtn.quanlybanhang.productCategory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.ProductCategoryAdapter;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;
import vn.edu.vtn.quanlybanhang.productList.ProductListFragment;

public class ProductCategoryFragment extends Fragment implements ProductCategoryMvpView {
    RecyclerView rvProductCategory;
    ProductCategoryAdapter adapter;

    APIService service;
    ProductCategoryMvpPresenter presenter;
    final String TAG = "TAG";


    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_category, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {
        rvProductCategory = view.findViewById(R.id.rvProductCategory);
        service = APIUtils.getServer();
        presenter = new ProductCategoryPresenter(this, getContext());
        ((ProductCategoryPresenter) presenter).getData();

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh mục sản phẩm");

    }

    @Override
    public void onCallBackDataSucess(final ArrayList<ProductCategory> list) {
        adapter = new ProductCategoryAdapter(list, getContext());
        rvProductCategory.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProductCategory.setItemAnimator(new DefaultItemAnimator());
        rvProductCategory.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductCategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                ProductCategory category = list.get(position);
                presenter.toGetProductList(category.getIdDanhMuc());
            }
        });

    }

    @Override
    public void onCallBackDataFailure(String mess) {
        Log.d(TAG, mess);
    }

    @Override
    public void onOpenProductLists(int position) {
        ProductListFragment homepage = new ProductListFragment();
        FragmentTransaction fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager()
                .beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position); //key and value
        homepage.setArguments(bundle);
        fragmentManager.replace(R.id.fragment_container, homepage);
        fragmentManager.addToBackStack(null);
        fragmentManager.commit();
    }
}
