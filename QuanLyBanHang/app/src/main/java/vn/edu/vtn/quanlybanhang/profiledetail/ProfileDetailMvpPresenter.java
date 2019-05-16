package vn.edu.vtn.quanlybanhang.profiledetail;

import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.Password;

public interface ProfileDetailMvpPresenter {
    void onUpdateProfile(Customer customer);

    void onChangePass(Password password);
}
