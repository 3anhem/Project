package vn.edu.vtn.quanlybanhang.pay.paylast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.pay.PayLastAdapter;
import vn.edu.vtn.quanlybanhang.data.model.Cart;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;

public class PayLastFragment extends Fragment implements PayLastMvpView {

    TextView txtUsername, txtPhone, txtAddress, txtLastPrice;
    PayLastMvpPresenter presenter;

    RecyclerView rvListCart;
    PayLastAdapter adapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_last, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Xác nhận");
        addControls(view);
        presenter = new PayLastPresenter(getContext(), this);
        presenter.toProcessAdress(); //get data from Address Fragment
        presenter.toProcessCart(); //get data from List Cart
        return view;
    }

    private void addControls(View view) {
        txtUsername = view.findViewById(R.id.txtUsername);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtAddress = view.findViewById(R.id.txtAddress);
        rvListCart = view.findViewById(R.id.rv_listCart);
        txtLastPrice = view.findViewById(R.id.txtLastPrice);
    }

    @Override
    public void setDataAdress(AddressLists dataAdress) {
        txtUsername.setText(dataAdress.getTenKhachHang());
        txtPhone.setText(dataAdress.getSoDt());
        txtAddress.setText(dataAdress.getTenDiaChi());
    }

    @Override
    public void setDataCart(ArrayList<Cart> dataCart, float totalMoney) {
        adapter = new PayLastAdapter(getContext(), dataCart);
        rvListCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListCart.setAdapter(adapter);
        String formattedPrice = new DecimalFormat("#,### đ").format(totalMoney);
        txtLastPrice.setText(formattedPrice + "");
    }

    @Override
    public void setDataPayForm() {

    }
}
