package vn.edu.vtn.tourdulich.khachhang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.dangnhap.LoginActivity;
import vn.edu.vtn.tourdulich.data.SharedPrefsHelper;
import vn.edu.vtn.tourdulich.data.api.APIClient;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;
import vn.edu.vtn.tourdulich.data.model.Customer;
import vn.edu.vtn.tourdulich.main.Main2Activity;

public class CustomerFragment extends Fragment {
    List<Customer> listCustomers;
    private ListView lvCustomers;
    private CustomAdapter customAdapter;
    private Button btnAdd, btnLogout;
    Dialog dialogAdd;
    SharedPrefsHelper sharedPrefsHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, null);
        listCustomers = new ArrayList<>();
        lvCustomers = view.findViewById(R.id.lvCustomer);
        btnAdd = view.findViewById(R.id.btnAddCustomer);
        btnLogout = view.findViewById(R.id.btnLogout);
        sharedPrefsHelper = new SharedPrefsHelper(getContext());
        getData();
        initAction();


        return view;
    }

    private void initAction() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefsHelper.setLoggedin(false, null);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().onBackPressed();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd = new Dialog(Objects.requireNonNull(getContext()));
                dialogAdd.setContentView(R.layout.dialog_add_customer);
                Objects.requireNonNull(dialogAdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final EditText edName = dialogAdd.findViewById(R.id.edt_name);
                final EditText edAddress = dialogAdd.findViewById(R.id.edt_address);
                Button button = dialogAdd.findViewById(R.id.bt_add);
                Button button1 = dialogAdd.findViewById(R.id.bt_cc);
                dialogAdd.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createCustomer(edName.getText().toString(), edAddress.getText().toString());
                        dialogAdd.dismiss();

                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogAdd.dismiss();
                    }
                });
            }

        });

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String[] colors = {"Xoá", "Sửa"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Options");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Xóa");
                                builder.setMessage("Bạn có muốn xóa không ?");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        delCustomer(listCustomers.get(position).getCustomerCode());
                                        dialogInterface.dismiss();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();


                                break;
                            case 1:
                                dialogAdd = new Dialog(Objects.requireNonNull(getContext()));
                                dialogAdd.setContentView(R.layout.dialog_add_customer);
                                Objects.requireNonNull(dialogAdd.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                final EditText edName = dialogAdd.findViewById(R.id.edt_name);
                                final EditText edAddress = dialogAdd.findViewById(R.id.edt_address);
                                Button button = dialogAdd.findViewById(R.id.bt_add);
                                Button button1 = dialogAdd.findViewById(R.id.bt_cc);
                                edName.setText(listCustomers.get(position).getCustomerName());
                                edAddress.setText(listCustomers.get(position).getAddress());
                                dialogAdd.show();
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        editCustomer(listCustomers.get(position).getCustomerCode(), edName.getText().toString(), edAddress.getText().toString());
                                        dialogAdd.dismiss();
                                    }
                                });
                                button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogAdd.dismiss();
                                    }
                                });

                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void editCustomer(String MAKH, String name, String adđres) {
        final APIService service = APIUtils.getServer();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("MAKH", MAKH);
        jsonObject.addProperty("DIACHI", adđres);
        jsonObject.addProperty("TENKH", name);
        service.editCustomer(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Void>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Void> voidResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AAA", e + "");

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Thanh cong!!!", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });
    }

    private void createCustomer(String name, String adđres) {
        final APIService service = APIUtils.getServer();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("DIACHI", adđres);
        jsonObject.addProperty("TENKH", name);
        service.createCustomer(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Customer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Customer customer) {


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Thanh Cong!!!", Toast.LENGTH_SHORT).show();
                        getData();

                    }
                });

    }

    private void delCustomer(String MAKH) {
        final APIService service = APIUtils.getServer();
        service.delCustomer(MAKH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Void>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<Void> jsonObject) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Thanh cong!!!", Toast.LENGTH_SHORT).show();
                        getData();

                    }
                });

    }

    private void getData() {
        final APIService service = APIUtils.getServer();
        listCustomers.clear();
        service.getListCustomers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Customer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Customer> customers) {
                        for (int i = 0; i < customers.size(); i++) {
                            listCustomers.add(customers.get(i));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AAA", e + "");

                    }

                    @Override
                    public void onComplete() {
                        CustomAdapter customAdapter = new CustomAdapter(getContext(), listCustomers);
                        lvCustomers.setAdapter(customAdapter);

                    }
                });
    }

    public void makeAddDialog() {
    }
}
