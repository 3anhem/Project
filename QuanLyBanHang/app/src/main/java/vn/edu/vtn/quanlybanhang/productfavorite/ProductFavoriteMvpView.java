package vn.edu.vtn.quanlybanhang.productfavorite;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;

public interface ProductFavoriteMvpView {
    void setDataSucess(ArrayList<ProductFavorite> list);

    void setDataFailure(String mess);

    void setDataEmpty();

    void setDataToDetail(Product product);
}
