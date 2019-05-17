package vn.edu.vtn.tourdulich.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.chitietphieu.CTPhieuDialogFragment;
import vn.edu.vtn.tourdulich.khachhang.CustomerFragment;
import vn.edu.vtn.tourdulich.phieudangky.PhieuDangKyFragment;
import vn.edu.vtn.tourdulich.tour.TourFragment;

public class Main2Activity extends AppCompatActivity implements CTPhieuDialogFragment.CTPhieuDialogListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_customer:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new CustomerFragment())
                            .commit();
                    return true;
                case R.id.navigation_tour:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new TourFragment())
                            .commit();
                    return true;
                case R.id.navigation_bill:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new PhieuDangKyFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new CustomerFragment())
                .commit();
    }

    @Override
    public void onFinishCTPhieuDialog() {

    }
}
