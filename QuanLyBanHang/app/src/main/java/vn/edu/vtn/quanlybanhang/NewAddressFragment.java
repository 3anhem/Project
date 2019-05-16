package vn.edu.vtn.quanlybanhang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.pay.newAddress.dialog.ProvinceDialogFragment;

public class NewAddressFragment extends Fragment {
    TextInputLayout txtName, txtPhone, txtProvince, txtDistrict, txtPhuongXa, txtHomeAddress;
    Button btnSubmit;
    TextInputEditText txtProvince1;
    ArrayList<String> listProvince;
    Toolbar toolbar;
    APIService service;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_address, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Thêm địa chỉ mới");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls(view);
        addEvents();
        return view;
    }

    private void addControls(View view) {
        txtName = view.findViewById(R.id.txtName);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtProvince = view.findViewById(R.id.txtProvince);
        txtDistrict = view.findViewById(R.id.txtDistrict);
        txtPhuongXa = view.findViewById(R.id.txtPhuongXa);
        txtHomeAddress = view.findViewById(R.id.txtHomeAddress);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        txtProvince1 = view.findViewById(R.id.txtProvince);
        service = APIUtils.getServer();
        getData();

        //listProvince = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arrTinh)));

    }

    private void addEvents() {
        txtProvince1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("LAYOUT", R.layout.dialog_province);
                    bundle.putStringArrayList("LIST", listProvince);
                    ProvinceDialogFragment provinceDialogFragment = new ProvinceDialogFragment();
                    provinceDialogFragment.setArguments(bundle);
                    provinceDialogFragment.show(getActivity().getSupportFragmentManager(), "Show dialog");
                } else {

                }

            }
        });
    }

    void getData() {
        service.getProvinceLists().enqueue(new Callback<ArrayList<Province>>() {
            @Override
            public void onResponse(Call<ArrayList<Province>> call, Response<ArrayList<Province>> response) {
                Log.d("AAAA", response.body().size() + "");
            }

            @Override
            public void onFailure(Call<ArrayList<Province>> call, Throwable t) {
                Log.d("AAAA", t.toString());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
