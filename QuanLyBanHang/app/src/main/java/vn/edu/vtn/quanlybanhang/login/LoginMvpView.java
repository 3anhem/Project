package vn.edu.vtn.quanlybanhang.login;

public interface LoginMvpView {
    void onSuscess(String mess);

    void onFailure(String mess);

    void onCheckInputData(String mess);

    void onOpenSignUpActivity();
}
