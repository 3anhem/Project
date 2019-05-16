package vn.edu.vtn.quanlybanhang.pay;

public interface PayMvpView {
    void openAddressFragment();

    void openPayFormFragment();

    void openPayLastFragment();

    void backPressedOne();

    void backPressedTwo();

    void createBillSucess(String mess);

    void createBillFailure(String mess);
}
