package vn.edu.vtn.quanlybanhang.productList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.ProductListAdapter;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.PagingProduct;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;

public class ProductListFragment extends Fragment implements ProductListMvpView {

    RecyclerView rvProductList;
    ProductListAdapter productListAdapter;
    LinearLayoutManager layoutManager;

    ProductListMvpPresenter presenter;
    TextView txtSort;

    APIService service = APIUtils.getServer();

    int id;
    int page = 1;
    int size = 10;
    int productSortType = 0;

    ProgressBar progressBar;
    Boolean isScrolling = false;
    Boolean lastPage = true;
    Boolean isLoadingAPI = false;
    int currentItem, totalItem, scrollOutItem;
//    public static final int RESULT_OK = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        addControls(view);
        addEvents();

        if (getArguments() != null) {
            id = getArguments().getInt("position"); // Get position from ProductNestedRVAdapter when you click MORE
            PagingProduct pagingProduct = new PagingProduct(id, page, size, productSortType);
            Log.d("IDD", id + "");
            presenter.getData(pagingProduct);
        }


        return view;
    }

    private void addEvents() {
        txtSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductSortDialogFrament sortDialogFrament = new ProductSortDialogFrament();
                sortDialogFrament.setTargetFragment(ProductListFragment.this, 1508);
                sortDialogFrament.show(getActivity().getSupportFragmentManager(), "Show product sort dialog");
            }
        });

    }

    private void addControls(View view) {
        rvProductList = view.findViewById(R.id.rv_product_list);
        progressBar = view.findViewById(R.id.progressBar);
        txtSort = view.findViewById(R.id.txtSort);
        presenter = new ProductListPresenter(this, getContext());
        layoutManager = new LinearLayoutManager(getContext());
    }


    @Override
    public void onCallBackDataSucess(final ArrayList<Product> list) {
        productListAdapter = new ProductListAdapter(getContext(), list);
        rvProductList.setLayoutManager(layoutManager);
        rvProductList.setAdapter(productListAdapter);

        productListAdapter.setOnItemClickListener(new ProductListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) { // Click into item of Product list to open Product Detail
                Product product = list.get(position);
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("PRODUCTLIST", product);
                startActivity(intent);
            }
        });

            rvProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) // Nếu cái trạng thái là là nhấn  , giữ, cuộn
                        isScrolling = true; // Set bằng true cho biết user đang thực hiện cuộn với state trên
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    currentItem = layoutManager.getChildCount(); // Số lượng item hiện tại đang xuất hiện trên màn hình
                    totalItem = layoutManager.getItemCount(); // Tổng số lượng item
                    scrollOutItem = layoutManager.findFirstVisibleItemPosition(); // Số lượng item đã xuất hiện khi cuộn

                    if (isScrolling && (currentItem + scrollOutItem == totalItem)) {
                        isScrolling = false;
                        Log.d("AAAA", "currentItem : " + layoutManager.getChildCount()
                                + " totalItem : " + layoutManager.getItemCount() + " scrollOutItem : " + layoutManager.findFirstVisibleItemPosition());
                        if (lastPage && !isLoadingAPI) {
                            page++;
                            loadMore();
                        }
                        Log.d("AAAA", page + " " + "List: " + list.size());
                    }
                }
            });
    }

    public void loadMore() {
        progressBar.setVisibility(View.VISIBLE);
        isLoadingAPI = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PagingProduct pagingProduct = new PagingProduct(id, page, size, productSortType);
                //Toast.makeText(getContext(), page + " " + productSortType, Toast.LENGTH_SHORT).show();
                service.getProductLists(pagingProduct).enqueue(new Callback<ArrayList<Product>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                        if (response.body() != null) {
                            productListAdapter.addProducts(response.body());
                        } else {
                            lastPage = false;
                            Log.d("AAAA", "K C G");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                    }
                });
                progressBar.setVisibility(View.GONE); // Ẩn và mất khỏi vị trí hiện tại
                isLoadingAPI = false;
            }
        }, 1500);
    }

    @Override
    public void onCallBackDataFailure(String mess) {
        Log.d("ERROR", mess);
    }

//    @Override
//    public void onFinishProductSortDialog(String productSortName, int productSortType) {
//        txtSort.setText(productSortName);
//        PagingProduct pagingProduct = new PagingProduct(id, page, size, productSortType);
//        productListAdapter.clearAllItem();
//        presenter.getData(pagingProduct);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1508) {
            String productSortName = data.getStringExtra("SORT_NAME");
            int productSortType = data.getIntExtra("SORT_TYPE", 0);
            txtSort.setText(productSortName);
            this.productSortType = productSortType;
            this.size = 10;
            this.page = 1;
            this.lastPage = true;
            PagingProduct pagingProduct = new PagingProduct(id, page, size, productSortType);
            productListAdapter.clearAllItem();
            presenter.getData(pagingProduct);
        }
    }
}

