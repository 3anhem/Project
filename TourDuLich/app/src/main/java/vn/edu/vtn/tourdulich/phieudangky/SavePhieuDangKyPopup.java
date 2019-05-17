package vn.edu.vtn.tourdulich.phieudangky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import vn.edu.vtn.tourdulich.R;

public class SavePhieuDangKyPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_phieudangky);
        Spinner spinner = findViewById(R.id.spinner_khachhang);

    }


}