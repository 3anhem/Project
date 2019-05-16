package vn.edu.vtn.quanlybanhang.pay.paylast;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.Cart;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public interface PayLastMvpView {
    void setDataAdress(AddressLists dataAdress);

    void setDataCart(ArrayList<Cart> dataCart, float totalMoney);

    void setDataPayForm();
}
