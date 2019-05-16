package vn.edu.vtn.quanlybanhang.billmanagement;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.bill.BillList;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;

public interface BillManagementMvpView {
    void onSetData(ArrayList<BillList> list);

    void onsetDataDetail(ProductBillDetail productBillDetail);
}
