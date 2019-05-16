package vn.edu.vtn.quanlybanhang.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.vtn.quanlybanhang.main.MainActivity;
import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginMvpView, View.OnClickListener {
    TextView txtSignup;
    TextInputEditText txtLoginEmailET, txtLoginPasswordET;
    TextInputLayout txtLoginEmail, txtLoginPassword;
    ImageView imgClose;
    Button btnLogin;
    LoginMvpPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
    }

    private void addControls() {
        txtLoginEmail = findViewById(R.id.txtLoginEmail);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);
        txtLoginPasswordET = findViewById(R.id.txtLoginPasswordET);
        txtSignup = findViewById(R.id.txtSignup);
        btnLogin = findViewById(R.id.btnLogin);
        imgClose = findViewById(R.id.imgClose);
        loginPresenter = new LoginPresenter(LoginActivity.this, this);
        btnLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
        imgClose.setOnClickListener(this);

        txtLoginPasswordET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    //do what you want on the press of 'done'
                    btnLogin.performClick();
                }
                return false;
            }
        });

    }

    @Override
    public void onSuscess(String mess) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onFailure(String mess) {
        Toast.makeText(LoginActivity.this, mess, Toast.LENGTH_SHORT).show();
        Log.d("AAAA", mess);
    }

    @Override
    public void onCheckInputData(String mess) {
        Toast.makeText(LoginActivity.this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOpenSignUpActivity() {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String email = txtLoginEmail.getEditText().getText().toString();
                String password = txtLoginPassword.getEditText().getText().toString();
                loginPresenter.onLoadData(email, password);
                break;
            case R.id.txtSignup:
                loginPresenter.onClickLogin();
                break;
            case R.id.imgClose:
                finish();
                break;
        }
    }
}
