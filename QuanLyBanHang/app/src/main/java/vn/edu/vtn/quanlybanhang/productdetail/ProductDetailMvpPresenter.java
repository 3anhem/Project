package vn.edu.vtn.quanlybanhang.productdetail;

import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;

public interface ProductDetailMvpPresenter {
    void onSetViewProductDetail(Product product);

    void onSetViewBottomSheet(Product product);

    void onSetLikeProduct(Product product);

    void onSetDislikeProduct(int id);

    void onCheckExists(int idProduct);
}
