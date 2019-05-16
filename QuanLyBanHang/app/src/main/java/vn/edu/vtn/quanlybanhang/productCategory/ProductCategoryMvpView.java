package vn.edu.vtn.quanlybanhang.productCategory;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;

public interface ProductCategoryMvpView {
    void onCallBackDataSucess(ArrayList<ProductCategory> list);

    void onCallBackDataFailure(String mess);

    void onOpenProductLists(int position);
}
