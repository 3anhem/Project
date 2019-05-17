package vn.edu.vtn.tourdulich.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.chitietphieu.ChiTietActivity;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;
import vn.edu.vtn.tourdulich.khachhang.KhachHangActivity;
import vn.edu.vtn.tourdulich.phieudangky.PhieuDKActivity;
import vn.edu.vtn.tourdulich.tour.TourFragment;

public class MainActivity extends AppCompatActivity {
    APIService apiService;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Trang chủ");
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //Cái này làm demo nè !!!
        apiService = APIUtils.getServer();
//        apiService.getListCustomers().enqueue(new Callback<ArrayList<Customer>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
//                Log.d("AAAA", response.body().size() + "");
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {
//                Log.d("AAAA", "onFailure " + t.toString());
//            }
//        });
    }

    public void toProcessCustomer(View view) {
        startActivity(new Intent(MainActivity.this, KhachHangActivity.class));
    }

    public void toProcessTour(View view) {
        startActivity(new Intent(MainActivity.this, TourFragment.class));
    }

    public void toProcessPDK(View view) {
        startActivity(new Intent(MainActivity.this, PhieuDKActivity.class));
    }

    public void toProcessChiTietPDK(View view) {
        startActivity(new Intent(MainActivity.this, ChiTietActivity.class));
    }
}
