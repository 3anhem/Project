package vn.edu.vtn.quanlybanhang.pay.address;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public interface AddressMvpView {
    void onLoadDataSucess(ArrayList<AddressLists> addressLists);

    void onLoadDataFailure(String mess);

    void onOpenNewAddress();

}
