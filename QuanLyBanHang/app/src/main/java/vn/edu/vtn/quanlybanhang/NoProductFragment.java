package vn.edu.vtn.quanlybanhang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NoProductFragment extends Fragment {
    Button btnHomePage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_no_product, container, false);
        btnHomePage = view.findViewById(R.id.btnHomePage);
        return view;
    }
}
