package vn.edu.vtn.quanlybanhang.signup;

import android.content.Context;

import vn.edu.vtn.quanlybanhang.data.api.APIHelper;
import vn.edu.vtn.quanlybanhang.data.api.model.SignUpIntractor;
import vn.edu.vtn.quanlybanhang.data.model.Customer;

public class SignUpPresenter implements SignUpMvpPresenter, APIHelper {
    Context context;
    SignUpMvpView view;
    SignUpIntractor signUpIntractor;

    public SignUpPresenter(Context context, SignUpMvpView view) {
        this.context = context;
        this.view = view;
        signUpIntractor = new SignUpIntractor(this, context);
    }

    @Override
    public void onLoadData(Customer customer) {
//        if (email == null || email.isEmpty()) {
//            view.onCheckInputData("Email is empty");
//            return;
//        }
////        if (!CommonUtils.isEmailValid(email)) {
////            getMvpView().onError(R.string.invalid_email);
////            return;
////        }
//        if (password == null || password.isEmpty()) {
//            view.onCheckInputData("Password is empty");
//            return;
//        }
//        if (username == null || username.isEmpty()) {
//            view.onCheckInputData("Username is empty");
//            return;
//        }
//        if (phone == null || phone.isEmpty()) {
//            view.onCheckInputData("Phone is empty");
//            return;
//        }
        signUpIntractor.initRetrofitCall(customer);
    }

    @Override
    public void onClickOpenLogin() {
        view.onOpenLoginActivity();
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
