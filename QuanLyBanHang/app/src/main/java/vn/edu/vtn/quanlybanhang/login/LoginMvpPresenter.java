package vn.edu.vtn.quanlybanhang.login;

public interface LoginMvpPresenter {
    void onLoadData(String email, String password);

    void onClickLogin();
}
