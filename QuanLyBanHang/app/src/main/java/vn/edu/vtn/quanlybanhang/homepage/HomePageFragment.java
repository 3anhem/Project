package vn.edu.vtn.quanlybanhang.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;
import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.SaleListAdapter;
import vn.edu.vtn.quanlybanhang.apdater.ProductCategoryAdapter;
import vn.edu.vtn.quanlybanhang.apdater.ProductNestedRVAdapter;
import vn.edu.vtn.quanlybanhang.data.model.ListRVProduct;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;
import vn.edu.vtn.quanlybanhang.data.model.SaleLists;
import vn.edu.vtn.quanlybanhang.productCategory.ProductCategoryFragment;
import vn.edu.vtn.quanlybanhang.productList.ProductListFragment;

public class HomePageFragment extends Fragment implements HomePageMvpView {

    AutoScrollViewPager viewPager;
    SaleListAdapter saleListAdapter;
    CircleIndicator circleIndicator;

    HomePageMvpPresenter presenter;

    RecyclerView rvProductCategory;
    ProductCategoryAdapter productCategoryAdapter;

    RecyclerView rvNested;
    ProductNestedRVAdapter nestedRVAdapter;

    Button btnMore;
    TextView txtMore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_test, container, false);
        addControls(view);
        addEvents();

        presenter = new HomePagePresenter(this, getContext());
        ((HomePagePresenter) presenter).getDataProductCategory();
        ((HomePagePresenter) presenter).getDataNestedProduct();
        presenter.getDataSaleList();


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trang chủ");


        return view;
    }

    private void addControls(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.indicator);
        rvProductCategory = view.findViewById(R.id.rv_product_category);
        rvNested = view.findViewById(R.id.my_recycler_view);
        //btnMore = view.findViewById(R.id.btnMore);
        txtMore = view.findViewById(R.id.txtMore);
    }

    private void addEvents() {
        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toOpenProductCategory();
            }
        });

    }

    @Override
    public void onCallBackCategorySucess(final ArrayList<ProductCategory> list) {
        productCategoryAdapter = new ProductCategoryAdapter(list, getContext());
        rvProductCategory.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        rvProductCategory.setAdapter(productCategoryAdapter);
        productCategoryAdapter.setOnItemClickListener(new ProductCategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                ProductCategory category = list.get(position);
                presenter.toGetProductList(category.getIdDanhMuc());
            }
        });

    }

    @Override
    public void onCallBackCategoryFailure(String mess) {
        Log.d("TAG ERROR", mess);
    }

    @Override
    public void onCallBackProductSucess(ArrayList<ListRVProduct> list) {
        Log.d("TAG ERROR", list.size() + "");
        nestedRVAdapter = new ProductNestedRVAdapter(getContext(), list);
        rvNested.setHasFixedSize(true);
        rvNested.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNested.setAdapter(nestedRVAdapter);
    }

    @Override
    public void onCallBackProductFailure(String mess) {
        Log.d("TAG", mess);
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

    @Override
    public void onOpenProductCategory() {
        FragmentTransaction fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager()
                .beginTransaction();
        ProductCategoryFragment fragment = new ProductCategoryFragment();
        fragmentManager.replace(R.id.fragment_container, fragment);
        fragmentManager.addToBackStack(null);
        fragmentManager.commit();
    }

    @Override
    public void onCallBackSaleListsSucess(ArrayList<SaleLists> list) {
        saleListAdapter = new SaleListAdapter(getContext(), list);
        viewPager.setAdapter(saleListAdapter);
        viewPager.startAutoScroll(2000);

        circleIndicator.setViewPager(viewPager);
    }

    @Override
    public void onCallBackSaleListsFailure(String mess) {
        Log.d("AAAA", mess);
    }
}