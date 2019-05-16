package vn.edu.vtn.quanlybanhang.main;

public interface MainMvpPresenter {
    void onClickHomePage();

    void onProductCategory();

    void onClickOderList();

    void onClickFavoriteProducts();

    void onClickProfile();

    void onClickNotification();

    void onClickSuport();

    void onSendTokenAndId(String token, String id);
}
