package vn.edu.vtn.quanlybanhang.login;

import android.content.Context;
import android.widget.Toast;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.api.APIHelper;
import vn.edu.vtn.quanlybanhang.data.api.model.LoginIntractor;

public class LoginPresenter implements LoginMvpPresenter, APIHelper {
    private Context context;
    private LoginMvpView view;
    private LoginIntractor loginIntractor;

    public LoginPresenter(Context context, LoginMvpView view) {
        this.context = context;
        this.view = view;
        loginIntractor = new LoginIntractor(this, context);
    }

    @Override
    public void onLoadData(String email, String password) {
        if (email == null || email.isEmpty()) {
            view.onCheckInputData("Email is empty");
            return;
        }
//        if (!CommonUtils.isEmailValid(email)) {
//            getMvpView().onError(R.string.invalid_email);
//            return;
//        }
        if (password == null || password.isEmpty()) {
            view.onCheckInputData("Password is empty");
            return;
        }

        loginIntractor.initRetrofitCall(email, password);

    }

    @Override
    public void onClickLogin() {
        view.onOpenSignUpActivity();
    }

    @Override
    public void onSucess(String mess) {
        view.onSuscess(mess);
    }

    @Override
    public void onFailure(String mess) {
        view.onFailure(mess);
    }
}
