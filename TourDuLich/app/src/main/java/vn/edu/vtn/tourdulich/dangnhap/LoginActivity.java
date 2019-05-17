package vn.edu.vtn.tourdulich.dangnhap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.SharedPrefsHelper;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;

import vn.edu.vtn.tourdulich.data.model.Admin;
import vn.edu.vtn.tourdulich.main.Main2Activity;


public class LoginActivity extends AppCompatActivity {
    APIService apiService;
    EditText txtEmail, txtPass;
    Button btnLogin;
    SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
    }

    private void addControls() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        sharedPrefsHelper = new SharedPrefsHelper(LoginActivity.this);
        if (sharedPrefsHelper.checklogin()) {
            startActivity(new Intent(LoginActivity.this, Main2Activity.class));
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmail.getText().toString().trim().equalsIgnoreCase("")) {
                    txtEmail.requestFocus();
                    txtEmail.setError("Vui long nhap email");
                } else if (txtPass.getText().toString().trim().equalsIgnoreCase("")) {
                    txtPass.requestFocus();
                    txtPass.setError("Vui long nhap mat khau");
                } else {
                    final Admin ad = new Admin();
                    ad.setEMAIL(txtEmail.getText().toString());
                    ad.setMATKHAU(txtPass.getText().toString());
                    apiService = APIUtils.getServer();
                    apiService.DangNhap(ad).enqueue(new Callback<Admin>() {
                        @Override
                        public void onResponse(Call<Admin> call, Response<Admin> response) {
                            if (response.code() == 204) {
                                Toast toast = Toast.makeText(LoginActivity.this, "Tai khoan khong ton tai", Toast.LENGTH_SHORT);
                                toast.show();

                            } else {
                                sharedPrefsHelper.setLoggedin(true, ad);
                                startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                            }

                        }

                        @Override
                        public void onFailure(Call<Admin> call, Throwable t) {
                            Log.d("SAISAI=DUNGDUNG ", "onFailure: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }


}
