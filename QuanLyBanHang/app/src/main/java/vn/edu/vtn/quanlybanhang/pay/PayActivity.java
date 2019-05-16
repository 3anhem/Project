package vn.edu.vtn.quanlybanhang.pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.kofigyan.stateprogressbar.StateProgressBar;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.main.MainActivity;
import vn.edu.vtn.quanlybanhang.pay.address.AddressFragment;
import vn.edu.vtn.quanlybanhang.pay.form.PayFormFragment;
import vn.edu.vtn.quanlybanhang.pay.paylast.PayLastFragment;


public class PayActivity extends AppCompatActivity implements PayMvpView {
    Toolbar toolbar;
    String[] descriptionData = {"Địa chỉ", "Thanh toán", "Xác nhận"};
    StateProgressBar stateProgressBar;
    Button btnSubmit;
    int currentStateNumber = 1;
    boolean checkBackStack = false;

    PayMvpPresenter payMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Địa chỉ nhận hàng");
        }
        stateProgressBar.setStateDescriptionData(descriptionData); // step by step
        payMvpPresenter.onOpenAddressFragment(); // open Address Fragment
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        btnSubmit = findViewById(R.id.btnSubmit);
        stateProgressBar = findViewById(R.id.your_state_progress_bar_id);
        payMvpPresenter = new PayPresenter(PayActivity.this, this);
    }

    private void addEvents() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentStateNumber) {
                    case 1: {
                        payMvpPresenter.onOpenPayFormFragment(); // open PayForm Fragment
                        break;
                    }
                    case 2: {
                        payMvpPresenter.onOpenPayLastFragment(); // open PayLast Fragment
                        break;
                    }
                    case 3: {
                        payMvpPresenter.onCreateBill(); // Create new Bill
                        break;
                    }
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        switch (currentStateNumber) {
            case 3: {
                payMvpPresenter.onBackPressedOne();
                break;
            }
            case 2: {
                payMvpPresenter.onBackPressedTwo();
                break;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openAddressFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.pay_container, new AddressFragment()).commit();
    }

    @Override
    public void openPayFormFragment() {
        btnSubmit.setText(getString(R.string.continuee));
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
        getSupportFragmentManager().beginTransaction().replace(R.id.pay_container, new PayFormFragment()).addToBackStack(null).commit();
        currentStateNumber++;
    }

    @Override
    public void openPayLastFragment() {
        btnSubmit.setText(getString(R.string.pay));
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
        if (!checkBackStack) { // Kiểm tra nếu Fragment đã tồn tại trong Stack thì ko thêm nữa
            getSupportFragmentManager().beginTransaction().replace(R.id.pay_container, new PayLastFragment()).addToBackStack(null).commit();
            checkBackStack = true;
            currentStateNumber++;
        }
    }

    @Override
    public void backPressedOne() {
        btnSubmit.setText(getString(R.string.continuee));
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
        currentStateNumber--;
        checkBackStack = false;
    }

    @Override
    public void backPressedTwo() {
        btnSubmit.setText(getString(R.string.receive_address));
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
        currentStateNumber--;
        checkBackStack = false;
    }

    @Override
    public void createBillSucess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show();
        startActivity(new Intent(PayActivity.this, MainActivity.class));
    }

    @Override
    public void createBillFailure(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }
}
