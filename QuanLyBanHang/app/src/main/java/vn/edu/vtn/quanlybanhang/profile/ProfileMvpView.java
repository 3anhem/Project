package vn.edu.vtn.quanlybanhang.profile;

import vn.edu.vtn.quanlybanhang.data.model.Customer;

public interface ProfileMvpView {
    void onSetView(Customer customer);
    void onSetLogOut();
}
