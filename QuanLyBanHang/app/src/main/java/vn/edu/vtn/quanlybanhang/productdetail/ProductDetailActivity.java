package vn.edu.vtn.quanlybanhang.productdetail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.dk.animation.circle.CircleAnimationUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.cart.CartActivity;
import vn.edu.vtn.quanlybanhang.data.model.Cart;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;
import vn.edu.vtn.quanlybanhang.data.sqlite.SqliteController;
import vn.edu.vtn.quanlybanhang.main.MainActivity;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailMvpView {
    TextView txtProductName, txtProductPrice,
            txtCart_badge, txtProductSalePrice, txtPercent, txtDescrip;
    Button btnBuy, btnAddCart;
    ImageView imgProduct, imgCart, imgProductHide;
    Menu mMenu;
    WebView wvDes;

    Toolbar toolbar;
    boolean checkStatusLike = false;

    SqliteController sqliteController;
    int totalProduct;
    float totalMoney;
    ArrayList<Cart> cartArrayList;
    ProductDetailMvpPresenter presenter;
    Product product;
    int idFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        addControls();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductSalePrice = findViewById(R.id.txtProductSalePrice);
        txtPercent = findViewById(R.id.txtPercent);
        //txtDescrip = findViewById(R.id.txtDescrip);
        imgProduct = findViewById(R.id.imgProduct);
        imgProductHide = findViewById(R.id.imgProductHide);
        btnBuy = findViewById(R.id.btnBuy);
        btnAddCart = findViewById(R.id.btnAddCart);
        wvDes = findViewById(R.id.wvDes);

        sqliteController = new SqliteController(ProductDetailActivity.this);
        presenter = new ProductDetailPresenter(ProductDetailActivity.this, this);

        totalProduct = sqliteController.getTotalProduct(); // Get tổng số lượng sản phẩm

        totalMoney = sqliteController.getTotalMoney(); // Thành tiền

        cartArrayList = sqliteController.getDataCart(); // Get List sản phẩm

        product = (Product) getIntent().getSerializableExtra("PRODUCTLIST");

        presenter.onSetViewProductDetail(product);    // Set data cho Detail
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSetViewBottomSheet(product);     // Khi click Button Sheet mua hàng
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgProductHide.setVisibility(View.VISIBLE);
                final Animation animationTrans = AnimationUtils.loadAnimation(ProductDetailActivity.this, R.anim.abc);
                imgProductHide.startAnimation(animationTrans);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toProcessCart();
                    }
                }, 1200);

//                new CircleAnimationUtil()
//                        .setMoveDuration(1000)
//                        .setBorderWidth(5)
//                        .setCircleDuration(1000)
//                        .attachActivity(ProductDetailActivity.this)
//                        .setTargetView(imgProduct)
//                        .setDestView(imgCart)
//                        .startAnimation();
            }
        });

        presenter.onCheckExists(product.getIdSanPham()); // Check Exist Favorite
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail, menu);

        MenuItem item = menu.findItem(R.id.cart);
        View actionView = item.getActionView();
        mMenu = menu;

        txtCart_badge = actionView.findViewById(R.id.txtCart_badge);
        imgCart = actionView.findViewById(R.id.imgCart);

        txtCart_badge.setText(totalProduct + ""); // Set tổng số lượng của từng  sản phẩm đang có trong giỏ hàng

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.like:
                toProcessLike(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void toProcessLike(MenuItem item) {
        if (!checkStatusLike) {
            item.setIcon(R.drawable.like_48px);
            checkStatusLike = true;
            presenter.onSetLikeProduct(product); // thêm sản phẩm được yêu thích vào DB
        } else {
            item.setIcon(R.drawable.dislike_48px);
            checkStatusLike = false;
            Log.d("AAAAB", idFavorite + "");
            presenter.onSetDislikeProduct(idFavorite); // Xóa sản phẩm đã được thêm vào DB
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalProduct = sqliteController.getTotalProduct();
        if (txtCart_badge != null) {
            txtCart_badge.setText(totalProduct + "");
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void setViewProductDetail(Product product) {
        txtProductName.setText(product.getName());

        float price = Float.parseFloat(product.getPrice());
        final String formattedPrice = new DecimalFormat("#,### đ").format(price);
        txtProductPrice.setText(formattedPrice);

        float salePrice = Float.parseFloat(product.getPriceSale());
        String formattedSalePrice = new DecimalFormat("#,### đ").format(salePrice);
        txtProductSalePrice.setText(formattedSalePrice);

        txtPercent.setText("-" + product.getPercent() + "%");

        String des = product.getDescribe();
        String mime = "text/html";
        String encoding = "utf-8";

        wvDes.getSettings().setJavaScriptEnabled(true); // set Description for product
        wvDes.loadDataWithBaseURL(null, des, mime, encoding, null);

        Glide.with(this)
                .load(product.getImage())
                .into(imgProduct);
        Glide.with(this)
                .load(product.getImage())
                .into(imgProductHide);
    }

    @Override
    public void setViewBottomSheet(Product product) {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_detail, null);
        TextView txtProductName = view.findViewById(R.id.txtProductName);
        TextView txtProductSalePrice = view.findViewById(R.id.txtProductSalePrice);
        TextView txtProductPrice = view.findViewById(R.id.txtProductPrice);
        TextView txtPercent = view.findViewById(R.id.txtPercent);
        ImageView imgProductImage = view.findViewById(R.id.imgProductImage);
        ImageView imgClose = view.findViewById(R.id.imgClose);
        Button btnOpenCart = view.findViewById(R.id.btnOpenCart);

        toProcessCart();

        // Set data cho Bottom Sheet
        txtProductName.setText(product.getName());

        float price = Float.parseFloat(product.getPrice());
        String formattedPrice = new DecimalFormat("#,### đ").format(price);
        txtProductPrice.setText(formattedPrice + "");

        float salePrice = Float.parseFloat(product.getPriceSale());
        String formattedSalePrice = new DecimalFormat("#,### đ").format(salePrice);
        txtProductSalePrice.setText(formattedSalePrice);

        txtPercent.setText("-" + product.getPercent() + "%");

        Glide.with(ProductDetailActivity.this)
                .load(product.getImage())
                .into(imgProductImage);


        final BottomSheetDialog dialog = new BottomSheetDialog(ProductDetailActivity.this);
        dialog.setContentView(view);
        dialog.show();
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOpenCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailActivity.this, CartActivity.class));
            }
        });

    }

    private void toProcessCart() {
        int idProduct = product.getIdSanPham();
        String name = product.getName();
        float salePrice1 = Float.parseFloat(product.getPriceSale());
        String price1 = product.getPrice();
        int amount = 1;
        String image = product.getImage();
        int status = 1;

        Cart cart = new Cart(idProduct, name, salePrice1, price1, amount, image, status);
        boolean result = sqliteController.saveCartData(cart); // Thêm 1 sản phẩm vào giỏ hàng
        cartArrayList = sqliteController.getDataCart(); // Get tổng số lượng sản phẩm
        totalProduct = sqliteController.getTotalProduct(); // Get
        totalMoney = sqliteController.getTotalMoney();
        txtCart_badge.setText(totalProduct + "");
    }

    @Override
    public void setLikeProduct(ProductFavorite favorite) {
        Log.d("AAAAB", favorite.getIdYeuThich() + "");
        idFavorite = favorite.getIdYeuThich();
    }

    @Override
    public void setResultCheckExist(boolean result, int idFavorite) {
        if (result) {
            checkStatusLike = true;
            this.idFavorite = idFavorite;
            Log.d("AAAA", idFavorite + "");
            mMenu.getItem(1).setIcon(R.drawable.like_48px);
        } else {
            checkStatusLike = false;
            mMenu.getItem(1).setIcon(R.drawable.dislike_48px);
            this.idFavorite = idFavorite;
        }
    }

}
