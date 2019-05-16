package vn.edu.vtn.quanlybanhang.profile.addressbooks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.apdater.AddressBooksAdapter;
import vn.edu.vtn.quanlybanhang.apdater.pay.AddressReciveAdapter;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.pay.newAddress.NewAddressActivity;
import vn.edu.vtn.quanlybanhang.pay.updateAdress.UpdateAdressActivity;

public class AddressBooksActivity extends AppCompatActivity implements AddressBooksMvpView {
    AddressBooksMvpPresenter presenter;
    RecyclerView rv_listAddress;
    AddressBooksAdapter adapter;

    Toolbar toolbar;
    Button btnNewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_books);
        addControls();
        addEvents();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sổ địa chỉ");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        presenter = new AddressBooksPresenter(AddressBooksActivity.this, this);
        presenter.callData(); // get Data from API
    }

    private void addEvents() {
        btnNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressBooksActivity.this, NewAddressActivity.class));
            }
        });
    }

    private void addControls() {
        rv_listAddress = findViewById(R.id.rv_listAddress);
        toolbar = findViewById(R.id.toolbar);
        btnNewAddress = findViewById(R.id.btnNewAddress);
    }

    @Override
    public void onLoadDataSucess(final ArrayList<AddressLists> addressLists) {
        Log.d("AAAA", "Size : " + addressLists.size() + "");
        adapter = new AddressBooksAdapter(AddressBooksActivity.this, addressLists);
        rv_listAddress.setLayoutManager(new LinearLayoutManager(AddressBooksActivity.this));
        rv_listAddress.setAdapter(adapter);

        adapter.setOnItemClickListener(new AddressBooksAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (v.getId()) {
                    case R.id.imgEditAddress:
                        toProcessEdit(position, addressLists);
                        break;
                    case R.id.imgDeleteAddress:
                        toprocessDelete(position, addressLists);
                        break;
                }
            }
        });
    }

    private void toprocessDelete(int position, ArrayList<AddressLists> addressLists) {
        if (addressLists.size() == 1) {
            Toast.makeText(AddressBooksActivity.this, "Bạn không thể xóa địa chỉ cuối cùng", Toast.LENGTH_SHORT).show();
            return;
        }
        AddressLists lists = addressLists.get(position);
        presenter.onDeleteAddress(lists.getIdDiaChiKhachHang());
//        adapter.deleteAllItem();
        presenter.callData();

    }

    private void toProcessEdit(int position, ArrayList<AddressLists> addressLists) {
        AddressLists address = addressLists.get(position);
        Intent intent = new Intent(AddressBooksActivity.this, UpdateAdressActivity.class);
        intent.putExtra("AddressLists", address);
        startActivity(intent);
    }

    @Override
    public void onOpenNewAddress() {
        startActivity(new Intent(this, NewAddressActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (adapter != null) {
//            adapter.deleteAllItem();
//        }
//        adapter.deleteAllItem();
        Log.d("AAAA", "ON RESUME");
        presenter.callData();
    }
}
