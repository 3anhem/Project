package vn.edu.vtn.quanlybanhang.profile.addressbooks;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public interface AddressBooksMvpView {
    void onLoadDataSucess(ArrayList<AddressLists> addressLists);

    void onOpenNewAddress();
}
