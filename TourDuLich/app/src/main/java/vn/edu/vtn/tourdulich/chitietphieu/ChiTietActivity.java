package vn.edu.vtn.tourdulich.chitietphieu;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;
import vn.edu.vtn.tourdulich.data.model.ChiTiet;

public class ChiTietActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnEdit, btnAdd;
    ListView lvDetail;
    ArrayList<ChiTiet> list;
    ChiTietAdapter adapter;
    APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        addControls();
        addEvents();
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        lvDetail = findViewById(R.id.lvDetail);
        apiService = APIUtils.getServer();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chi tiết phiếu đăng ký");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        loadData();
    }

    private void loadData() {
        apiService.getListCTPhieu().enqueue(new Callback<ArrayList<ChiTiet>>() {
            @Override
            public void onResponse(Call<ArrayList<ChiTiet>> call, Response<ArrayList<ChiTiet>> response) {
                list = response.body();
                adapter = new ChiTietAdapter(ChiTietActivity.this, R.layout.item_chi_tiet, list);
                lvDetail.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ChiTiet>> call, Throwable t) {
                Log.d("AAAA", "onFailure :" + t.toString());
            }
        });
    }


    private void addEvents() {
        lvDetail.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietActivity.this);
                builder.setTitle("Remove");
                builder.setMessage("Bạn muốn xóa ID : " + list.get(position).getId());
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(list.get(position).getId());
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });

    }

    public void delete(int position) {
        apiService.deleteCTPhieu(position).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("AAAA", response.code() + "");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("AAAA", "onFailure + " + t.toString());
            }
        });
    }

    public void showDialog() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
