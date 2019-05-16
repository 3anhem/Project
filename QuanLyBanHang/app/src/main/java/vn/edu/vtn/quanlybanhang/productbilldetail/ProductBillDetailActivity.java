package vn.edu.vtn.quanlybanhang.productbilldetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.bill.ProductListBillAdapter;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;

public class ProductBillDetailActivity extends AppCompatActivity implements ProductBillDetailMvpView {
    Toolbar toolbar;
    ProductBillDetail detail;
    ProductBillDetailMvpPresenter presenter;

    TextView txtBillCode, txtOderDate, txtStatus, txtUsername, txtPhone, txtAddress, txtLastPrice;
    RecyclerView rvListCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_bill_detail);
        addControls();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Đơn hàng");
        }


        Intent intent = getIntent();
        detail = (ProductBillDetail) intent.getSerializableExtra("BILL_DETAIL");
        presenter = new ProductBillDetailPresenter(ProductBillDetailActivity.this, this);
        presenter.onGetData(detail);

    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        txtBillCode = findViewById(R.id.txtBillCode);
        txtOderDate = findViewById(R.id.txtOderDate);
        txtStatus = findViewById(R.id.txtStatus);
        txtUsername = findViewById(R.id.txtUsername);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtLastPrice = findViewById(R.id.txtLastPrice);
        rvListCart = findViewById(R.id.rv_listCart);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSetDataView(ProductBillDetail detail) {

        txtBillCode.setText(detail.getIdDonDatHang() + "");
        txtOderDate.setText(detail.getNgayLap());
        txtStatus.setText(detail.getTrangThai());
        txtUsername.setText(detail.getTenNguoiNhan());
        txtPhone.setText(detail.getSoDT());
        txtAddress.setText(detail.getDiaChi());
        Float sum = 0f;

        for (int i = 0; i < detail.getList().size(); i++) {
            sum += detail.getList().get(i).getTongGia();

        }


        String formattedPrice = new DecimalFormat("#,### đ").format(sum);
        txtLastPrice.setText(formattedPrice + "");


        ProductListBillAdapter adapter = new ProductListBillAdapter(ProductBillDetailActivity.this, detail.getList());
        rvListCart.setLayoutManager(new LinearLayoutManager(this));
        rvListCart.setAdapter(adapter);

    }
}
