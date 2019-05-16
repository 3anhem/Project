package vn.edu.vtn.quanlybanhang.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.CartAdapter;
import vn.edu.vtn.quanlybanhang.data.model.Cart;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.data.sqlite.SqliteController;
import vn.edu.vtn.quanlybanhang.login.LoginActivity;
import vn.edu.vtn.quanlybanhang.main.MainActivity;
import vn.edu.vtn.quanlybanhang.pay.PayActivity;

public class CartActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rvListCart;
    CartAdapter cartAdapter;
    ArrayList<Cart> list;
    TextView txtTempPrice, txtLastPrice, txtQuantity;
    Button btnOder;

    SqliteController sqliteController;
    SharedPrefsHelper sharedPrefsHelper;
    float totalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Giỏ hàng");
        }
        addControls();
        list = sqliteController.getDataCart(); // Set dữ liệu cho list từ SQLite
        if (list.size() == 0) {
            setContentView(R.layout.cart_no_product);
            onOpenMainActivity();
        }
        cartAdapter = new CartAdapter(list, CartActivity.this);

        rvListCart.setLayoutManager(new LinearLayoutManager(this));
        rvListCart.setAdapter(cartAdapter);


        cartAdapter.setOnItemClickListener(new CartAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (v.getId()) {
                    case R.id.imgDelete:
                        toProcessDeleteItem(position);
                        break;
                    case R.id.btnPlus:
                        toProcessPlusAmount(position, v);
                        break;
                    case R.id.btnMinus:
                        toProcessMinusAmount(position, v);
                }

            }
        });
        toProcessTotalMoney();
    }

    private void onOpenMainActivity() {
        Button btnHomePage = findViewById(R.id.btnHomePage);
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void addEvents() {
        btnOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPrefsHelper.checklogin()) {
                    startActivity(new Intent(CartActivity.this, PayActivity.class));
                } else {
                    startActivity(new Intent(CartActivity.this, LoginActivity.class));
                }
            }
        });
    }

    private void toProcessMinusAmount(int position, View v) {
        Cart cart = list.get(position);
        sqliteController.minusAmount(cart.getIdProduct());
        toProcessTotalMoney();
        cartAdapter.refreshData(list);
    }

    private void toProcessPlusAmount(int position, View v) {
        Cart cart = list.get(position);
        sqliteController.saveCartData(cart);
        toProcessTotalMoney();
        cartAdapter.updateData(list);
//        // cartAdapter.notifyDataSetChanged();
//
//        Log.d("AAAA", list.get(position).getAmount() + "");
//        cartAdapter.refreshData(list);
//        Log.d("AAAA", cart1.getAmount() + "");
//        Log.d("AAAA", position + "");
//
//        cartAdapter.setAmount(cart1.getAmount(), position);

        // Log.d("AAAA", sqliteController.getAmount(cart.getIdCart()) + "");
        //  Log.d("AAAA", txtQuantity.getText().toString());

    }


    void toProcessTotalMoney() {
        list = sqliteController.getDataCart();
        totalMoney = sqliteController.getTotalMoney();
        String formattedPrice = new DecimalFormat("#,### đ").format(totalMoney);
        txtTempPrice.setText(formattedPrice);
        txtLastPrice.setText(formattedPrice);
    }

    void toProcessDeleteItem(int position) {
        Cart cart = list.get(position);
        sqliteController.toProcessDelete(cart.getIdCart()); // xóa theo id cart
        list = sqliteController.getDataCart();
        toProcessTotalMoney(); // Xử lý thành tiền
        cartAdapter.refreshData(list); // Update lại cái data

        if (list.size() == 0) {
            setContentView(R.layout.cart_no_product);
            onOpenMainActivity();
        }
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        rvListCart = findViewById(R.id.rv_listCart);
        txtTempPrice = findViewById(R.id.txtTempPrice);
        txtLastPrice = findViewById(R.id.txtLastPrice);
        btnOder = findViewById(R.id.btnOder);
        sqliteController = new SqliteController(CartActivity.this);
        sharedPrefsHelper = new SharedPrefsHelper(CartActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
