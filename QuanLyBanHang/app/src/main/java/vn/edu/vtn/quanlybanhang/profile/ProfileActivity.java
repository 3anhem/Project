package vn.edu.vtn.quanlybanhang.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.main.MainActivity;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;
import vn.edu.vtn.quanlybanhang.profile.addressbooks.AddressBooksActivity;
import vn.edu.vtn.quanlybanhang.profiledetail.ProfileDetailActivity;

public class ProfileActivity extends AppCompatActivity implements ProfileMvpView {
    TextView txtProfileName, txtProfilePhone, txtDateRegister, txtBillSuccess, txtTotalPrice, txtProductSuccess;
    ImageView imgEditProfile;
    ProfileMvpPresenter presenter;
    RelativeLayout relativelayoutEdit;
    LinearLayout rlAddress;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Quản lý tài khoản");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void addEvents() {
        relativelayoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileDetailActivity.class));
            }
        });
        rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AddressBooksActivity.class));
            }
        });
    }

    private void addControls() {
        txtProfileName = findViewById(R.id.txtProfileName);
        txtProfilePhone = findViewById(R.id.txtProfilePhone);
        imgEditProfile = findViewById(R.id.imgEditProfile);
        txtDateRegister = findViewById(R.id.txtDateRegister);
        relativelayoutEdit = findViewById(R.id.relativelayoutEdit);
        txtBillSuccess = findViewById(R.id.txtBillSuccess);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtProductSuccess = findViewById(R.id.txtProductSuccess);
        rlAddress = findViewById(R.id.rlAddress);
        toolbar = findViewById(R.id.toolbar);

        presenter = new ProfilePresenter(ProfileActivity.this, this);
        presenter.onGetView();

    }

    public void toProcessLogout(View view) {
        presenter.onLogOut();

    }

    @Override
    public void onSetView(Customer customer) {
        Log.d("AAAA", customer.getTotalPrice() + "");
        txtProfileName.setText(customer.getName());
        txtProfilePhone.setText(customer.getPhone());
        txtDateRegister.setText(customer.getRegistrationDate());

        String formattedPrice = new DecimalFormat("#,### đ").format(customer.getTotalPrice());

        txtBillSuccess.setText(customer.getBillSuccess() + "");
        txtTotalPrice.setText(formattedPrice);
        txtProductSuccess.setText(customer.getProductSucess() + "");
    }

    @Override
    public void onSetLogOut() {
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onGetView();
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
}
