package vn.edu.vtn.quanlybanhang.homepage;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.ListRVProduct;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;
import vn.edu.vtn.quanlybanhang.data.model.SaleLists;

public interface HomePageMvpView {
    void onCallBackCategorySucess(ArrayList<ProductCategory> list);

    void onCallBackCategoryFailure(String mess);

    void onCallBackProductSucess(ArrayList<ListRVProduct> list);

    void onCallBackProductFailure(String mess);

    void onOpenProductLists(int position);

    void onOpenProductCategory();

    void onCallBackSaleListsSucess(ArrayList<SaleLists> list);

    void onCallBackSaleListsFailure(String mess);

}
