package vn.edu.vtn.quanlybanhang.productdetail;

import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;

public interface ProductDetailMvpView {
    void setViewProductDetail(Product product);

    void setViewBottomSheet(Product product);

    void setLikeProduct(ProductFavorite favorite);

    void setResultCheckExist(boolean result, int idFavorite);

}
