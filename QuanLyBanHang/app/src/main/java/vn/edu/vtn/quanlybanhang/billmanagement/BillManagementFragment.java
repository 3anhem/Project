package vn.edu.vtn.quanlybanhang.billmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.bill.BillManagementAdapter;
import vn.edu.vtn.quanlybanhang.data.model.bill.BillList;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;
import vn.edu.vtn.quanlybanhang.productbilldetail.ProductBillDetailActivity;

public class BillManagementFragment extends Fragment implements BillManagementMvpView {
    RecyclerView rvBillManage;
    BillManagementMvpPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_management, container, false);
        rvBillManage = view.findViewById(R.id.rvBillManage);
        presenter = new BillManagementPresenter(getContext(), this);
        presenter.onGetData(); // get Data by API
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Quản lý đơn hàng");
        return view;
    }

    @Override
    public void onSetData(final ArrayList<BillList> list) {
        BillManagementAdapter adapter = new BillManagementAdapter(getContext(), list);
        rvBillManage.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBillManage.setAdapter(adapter);
        adapter.setOnItemClickListener(new BillManagementAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                BillList billList = list.get(position);
                presenter.onGetDataDetail(billList.getId()); // get Data Detail by API
            }
        });
    }

    @Override
    public void onsetDataDetail(ProductBillDetail productBillDetail) {
        Intent intent = new Intent(getContext(), ProductBillDetailActivity.class);
        intent.putExtra("BILL_DETAIL", productBillDetail);
        startActivity(intent);
    }
}
