package vn.edu.vtn.quanlybanhang.productList;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.Product;

public interface ProductListMvpView {
    void onCallBackDataSucess(ArrayList<Product> list);

    void onCallBackDataFailure(String mess);

}
