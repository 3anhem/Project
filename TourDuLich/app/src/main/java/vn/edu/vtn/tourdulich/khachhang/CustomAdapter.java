package vn.edu.vtn.tourdulich.khachhang;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.List;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.model.Customer;

/**
 * Created by Tuan Dau on 5/12/2019.
 */

public class CustomAdapter implements ListAdapter {

    List<Customer> customerList;
    Context context;

    public CustomAdapter(Context context, List<Customer> customers) {
        this.context = context;
        customerList = customers;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         Customer customer = customerList.get(position);


            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_item_row, null);
            TextView tvCustomerCode = convertView.findViewById(R.id.tvMaKH);
            TextView tvCustomerName = convertView.findViewById(R.id.tvTenKH);
            TextView tvCustomerAddress = convertView.findViewById(R.id.tvDiachi);

            tvCustomerCode.setText(customer.getCustomerCode());
            tvCustomerName.setText(customer.getCustomerName());
            tvCustomerAddress.setText(customer.getAddress());

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
