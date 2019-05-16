package vn.edu.vtn.quanlybanhang.productfavorite;

import vn.edu.vtn.quanlybanhang.data.model.Product;

public interface ProductFavoriteMvpPresenter {
    void onGetData();

    void onPassDataToDetail(Product product);
}
