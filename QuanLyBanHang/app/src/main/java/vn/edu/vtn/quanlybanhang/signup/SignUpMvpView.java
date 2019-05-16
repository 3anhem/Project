package vn.edu.vtn.quanlybanhang.signup;

public interface SignUpMvpView {
    void onSuscess(String mess);

    void onFailure(String mess);

    void onCheckInputData(String mess);

    void onOpenLoginActivity();

}
