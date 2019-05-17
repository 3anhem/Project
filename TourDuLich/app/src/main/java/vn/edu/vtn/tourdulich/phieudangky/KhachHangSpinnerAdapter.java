package vn.edu.vtn.tourdulich.phieudangky;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.tourdulich.data.model.Customer;

public class KhachHangSpinnerAdapter extends ArrayAdapter<Customer> {
    Context context;
    int layoutID;
    ArrayList<Customer> arr;

    public KhachHangSpinnerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Customer> arr) {
        super(context, resource, arr);
        this.context = context;
        this.layoutID = resource;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView item = (TextView) super.getView(position, view, parent);
        Customer khachHang = arr.get(position);
        String text = String.format("[%d] %s", Integer.parseInt(khachHang.getCustomerCode()), khachHang.getCustomerName());
        item.setText(text);
        return item;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        TextView item = (TextView) super.getDropDownView(position, view, parent);
        Customer khachHang = arr.get(position);
        String text = String.format("[%d] %s", Integer.parseInt(khachHang.getCustomerCode()), khachHang.getCustomerName());
        item.setText(text);
        return item;
    }
}
