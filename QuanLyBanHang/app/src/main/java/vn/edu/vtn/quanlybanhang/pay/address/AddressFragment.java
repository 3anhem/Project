package vn.edu.vtn.quanlybanhang.pay.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.pay.AddressReciveAdapter;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.pay.newAddress.NewAddressActivity;
import vn.edu.vtn.quanlybanhang.pay.updateAdress.UpdateAdressActivity;

public class AddressFragment extends Fragment implements AddressMvpView {
    RecyclerView rv_listAddress;
    AddressReciveAdapter addressReciveAdapter;

    LinearLayout linearLayout;
    AddressMvpPresenter presenter;
    int defaultPosition = 0;

    SharedPrefsHelper sharedPrefsHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recive_address, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addControls(View view) {
        linearLayout = view.findViewById(R.id.linearLayoutAddNewAddress);
        rv_listAddress = view.findViewById(R.id.rv_listAddress);
        presenter = new AddressPresenter(getContext(), this);
        presenter.callData();// get list address show RV
        sharedPrefsHelper = new SharedPrefsHelper(getContext());
    }

    private void addEvents() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewAddressActivity.class));
            }
        });

    }


    @Override
    public void onLoadDataSucess(final ArrayList<AddressLists> addressLists) {
        addressReciveAdapter = new AddressReciveAdapter(getContext(), addressLists);
        rv_listAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_listAddress.setAdapter(addressReciveAdapter);
        toProcessPassAddress(defaultPosition, addressLists);// Địa chỉ mặc định nếu không chọn gì cả !!!

        addressReciveAdapter.setOnItemClickListener(new AddressReciveAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (v.getId()) {
                    case R.id.imgEditAddress:
                        toProcessEdit(position, addressLists);
                        break;
                    case R.id.imgDeleteAddress:
                        toprocessDelete(position, addressLists);
                        break;
                    default:
                        addressReciveAdapter.setRadioButton(position);
                        toProcessPassAddress(position, addressLists);
                        break;
                }
            }
        });
    }

    private void toProcessPassAddress(int position, ArrayList<AddressLists> addressLists) {
        AddressLists address = addressLists.get(position);
        sharedPrefsHelper.setData(address); // Lưu địa chỉ được chọn
    }

    private void toProcessEdit(int position, ArrayList<AddressLists> addressLists) {
        AddressLists address = addressLists.get(position);
        Intent intent = new Intent(getContext(), UpdateAdressActivity.class);
        intent.putExtra("AddressLists", address);
        startActivity(intent);
    }

    private void toprocessDelete(int position, ArrayList<AddressLists> addressLists) {
        if (addressLists.size() == 1) {
            Toast.makeText(getContext(), "Bạn không thể xóa địa chỉ cuối cùng", Toast.LENGTH_SHORT).show();
            return;
        }
        AddressLists lists = addressLists.get(position);
        presenter.onDeleteAddress(lists.getIdDiaChiKhachHang());
        presenter.callData(); // refresh data
    }

    @Override
    public void onLoadDataFailure(String mess) {
        Log.d("AAAA", mess);
    }

    @Override
    public void onOpenNewAddress() {
        startActivity(new Intent(getContext(), NewAddressActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.callData(); // refresh data in RV
    }
}

