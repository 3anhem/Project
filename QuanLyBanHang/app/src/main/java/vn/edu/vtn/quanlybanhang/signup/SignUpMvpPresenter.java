package vn.edu.vtn.quanlybanhang.signup;

import vn.edu.vtn.quanlybanhang.data.model.Customer;

public interface SignUpMvpPresenter {
    void onLoadData(Customer customer);

    void onClickOpenLogin();
}
