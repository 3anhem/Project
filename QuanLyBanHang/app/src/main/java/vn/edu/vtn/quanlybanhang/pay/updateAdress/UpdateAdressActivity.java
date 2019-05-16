package vn.edu.vtn.quanlybanhang.pay.updateAdress;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.model.address.District;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;
import vn.edu.vtn.quanlybanhang.pay.newAddress.dialog.DistrictDialogFragment;
import vn.edu.vtn.quanlybanhang.pay.newAddress.dialog.ProvinceDialogFragment;
import vn.edu.vtn.quanlybanhang.pay.newAddress.dialog.XaPhuongDialogFragment;

public class UpdateAdressActivity extends AppCompatActivity implements UpdateAdressMvpView, ProvinceDialogFragment.EditNameDialogListener
        , DistrictDialogFragment.DistrictDialogListener, XaPhuongDialogFragment.XaPhuongDialogListener {
    TextInputLayout txtName, txtPhone, txtProvince, txtDistrict, txtPhuongXa, txtHomeAddress;
    TextInputEditText txtProvinceET, txtDistrictET, txtPhuongXaET, txtAddressET, txtCustomerNameET, txtPhoneET;
    Button btnSubmit;
    Toolbar toolbar;
    UpdateAdressMvpPresenter mvpPresenter;
    String provinceCode, districtCode, XaPhuongCode;

    AddressLists address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_adress);
        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cập nhập địa chỉ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtProvince = findViewById(R.id.txtProvince);
        txtDistrict = findViewById(R.id.txtDistrict);
        txtPhuongXa = findViewById(R.id.txtPhuongXa);
        txtHomeAddress = findViewById(R.id.txtHomeAddress);

        btnSubmit = findViewById(R.id.btnSubmit);

        txtProvinceET = findViewById(R.id.txtProvinceET);
        txtDistrictET = findViewById(R.id.txtDistrictET);
        txtPhuongXaET = findViewById(R.id.txtPhuongXaET);
        txtAddressET = findViewById(R.id.txtAddressET);
        txtCustomerNameET = findViewById(R.id.txtCustomerNameET);
        txtPhoneET = findViewById(R.id.txtPhoneET);

        mvpPresenter = new UpdateAdressPresenter(UpdateAdressActivity.this, this);

        Intent intent = getIntent();
        address = (AddressLists) intent.getSerializableExtra("AddressLists");

        txtAddressET.setText(address.getTenDiaChi());
        txtPhoneET.setText(address.getSoDt());
        txtCustomerNameET.setText(address.getTenKhachHang());
        txtProvinceET.setText(address.getTenTinh());
        provinceCode = address.getIdTinh();
        txtDistrictET.setText(address.getTenQuanHuyen());
        districtCode = address.getIdQuanHuyen();
        txtPhuongXaET.setText(address.getTenXaPhuong());
        XaPhuongCode = address.getIdXaPhuong();

    }

    private boolean validateName() {
        String emailInput = txtName.getEditText().getText().toString();
        if (emailInput.isEmpty()) {
            txtName.setError("Tên không được bỏ trống !!!");
            return false;
        } else {
            txtName.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = txtPhone.getEditText().getText().toString();
        if (phoneInput.isEmpty()) {
            txtPhone.setError("Số điện thoại không được bỏ trống !!!");
            return false;
        } else if (phoneInput.length() > 10) {
            txtPhone.setError("Số điện thoại quá cỡ !!!");
            return false;
        } else {
            txtPhone.setError(null);
            return true;
        }
    }

    private boolean validateProvince() {
        String provinceInput = txtProvince.getEditText().getText().toString();
        if (provinceInput.isEmpty()) {
            txtProvince.setError("Tỉnh không được bỏ trống !!!");
            return false;
        } else {
            txtProvince.setError(null);
            return true;
        }

    }

    private boolean validateDistrict() {
        String districtInput = txtDistrict.getEditText().getText().toString();
        if (districtInput.isEmpty()) {
            txtDistrict.setError("Quận/Huyện không được bỏ trống !!!");
            return false;
        } else if (!validateProvince()) {
            txtDistrict.setError("Province can't be empty");
            return false;
        } else {
            txtDistrict.setError(null);
            return true;
        }

    }

    private boolean validatePhuongXa() {
        String phuongXaInput = txtPhuongXa.getEditText().getText().toString();
        if (phuongXaInput.isEmpty()) {
            txtPhuongXa.setError("Phường/Xã không được bỏ trống !!!");
            return false;
        } else if (!validateDistrict()) {
            txtPhuongXa.setError("District can't be empty");
            return false;
        } else {
            txtPhuongXa.setError(null);
            return true;
        }

    }

    private boolean validateAddress() {
        String addressInput = txtHomeAddress.getEditText().getText().toString();
        if (addressInput.isEmpty()) {
            txtHomeAddress.setError("Tỉnh không được bỏ trống !!!");
            return false;
        } else {
            txtHomeAddress.setError(null);
            return true;
        }

    }

    public boolean confirmInput() {
        return validateName() && validatePhone() && validateProvince()
                && validateDistrict() && validatePhuongXa() && validateAddress();

    }

    private void addEvents() {
        txtProvinceET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.openProvinceDialog();
            }
        });

        txtDistrictET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateProvince()) {
                    return;
                }
                mvpPresenter.openDistricDialog(provinceCode);
            }
        });
        txtPhuongXaET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateDistrict()) {
                    return;
                }
                mvpPresenter.openXaPhuongDialog(districtCode);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!confirmInput()) {
                    return;
                }
                String defaultAddress = txtAddressET.getText().toString();
                String phone = txtPhoneET.getText().toString();
                String name = txtCustomerNameET.getText().toString();
                AddressLists addressLists = new AddressLists(address.getIdDiaChiKhachHang(), defaultAddress, phone, address.getIdKhachHang(), name, provinceCode, districtCode, XaPhuongCode);
                mvpPresenter.getData(addressLists); // Add new address
                finish();
            }
        });
    }

    @Override
    public void OnopenProvinceDialog(ArrayList<Province> provinceArrayList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST", provinceArrayList);
        bundle.putString("TITLE", "Chọn Tỉnh/Thành");
        bundle.putString("HINT", "Tìm kiếm Tỉnh/Thành");
        ProvinceDialogFragment provinceDialogFragment = new ProvinceDialogFragment();
        provinceDialogFragment.setArguments(bundle);
        provinceDialogFragment.show(getSupportFragmentManager(), "Show dialog");

    }

    @Override
    public void OnopenDistrictDialog(ArrayList<District> districtArrayList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST", districtArrayList);
        bundle.putString("TITLE", "Chọn Quận/Huyện");
        bundle.putString("HINT", "Tìm kiếm Quận/Huyện");
        DistrictDialogFragment districtDialogFragment = new DistrictDialogFragment();
        districtDialogFragment.setArguments(bundle);
        districtDialogFragment.show(getSupportFragmentManager(), "Show dialog");
    }

    @Override
    public void OnopenXaPhuongDialog(ArrayList<XaPhuong> xaPhuongArrayList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("LIST", xaPhuongArrayList);
        bundle.putString("TITLE", "Chọn Xã/Phường");
        bundle.putString("HINT", "Tìm kiếm Xã/Phường");
        XaPhuongDialogFragment xaPhuongDialogFragment = new XaPhuongDialogFragment();
        xaPhuongDialogFragment.setArguments(bundle);
        xaPhuongDialogFragment.show(getSupportFragmentManager(), "Show dialog");
    }

    // set text cho EditText
    @Override
    public void onFinishEditDialog(String provinceName, String provinceCode) {
        txtProvinceET.setText(provinceName);
        this.provinceCode = provinceCode;
        txtProvinceET.clearFocus();
        mvpPresenter.openDistricDialog(provinceCode);
    }

    @Override
    public void onFinishDistrictDialog(String districtName, String districtCode) {
        txtDistrictET.setText(districtName);
        this.districtCode = districtCode;
        txtProvinceET.clearFocus();
        mvpPresenter.openXaPhuongDialog(districtCode);
    }

    @Override
    public void onFinishXaPhuongDialog(String XaPhuongName, String XaPhuongCode) {
        txtPhuongXaET.setText(XaPhuongName);
        txtPhuongXaET.clearFocus();
        this.XaPhuongCode = XaPhuongCode;
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
