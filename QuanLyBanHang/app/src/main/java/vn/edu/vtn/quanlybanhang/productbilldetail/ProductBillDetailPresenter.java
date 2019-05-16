package vn.edu.vtn.quanlybanhang.productbilldetail;

import android.content.Context;

import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;

public class ProductBillDetailPresenter implements ProductBillDetailMvpPresenter {
    private Context context;
    private ProductBillDetailMvpView view;

    public ProductBillDetailPresenter(Context context, ProductBillDetailMvpView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void onGetData(ProductBillDetail detail) {
        view.onSetDataView(detail);
    }
}
