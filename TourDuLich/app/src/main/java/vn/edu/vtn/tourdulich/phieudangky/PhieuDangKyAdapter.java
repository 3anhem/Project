package vn.edu.vtn.tourdulich.phieudangky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.model.PhieuDangKy;

public class PhieuDangKyAdapter extends ArrayAdapter<PhieuDangKy> {
    Activity context;
    int layoutRow;
    ArrayList<PhieuDangKy> arr;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");



    public PhieuDangKyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PhieuDangKy> objects) {
        super(context, resource, objects);
        this.context = (Activity)context;
        this.layoutRow = resource;
        this.arr = objects;
    }
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View row = convertView;
        PhieuDangKyHolder holder;
        if (row != null) {
            holder = (PhieuDangKyHolder)row.getTag();
        } else {
            row = context.getLayoutInflater().inflate(layoutRow, parent, false);
            holder = new PhieuDangKyHolder();
            holder.ngayDK = row.findViewById(R.id.ngayDK);
            holder.soPhieu = row.findViewById(R.id.soPhieu);
            holder.khachHang = row.findViewById(R.id.khachHang);
            row.setTag(holder);
        }

        PhieuDangKy phieu = arr.get(position);
        holder.khachHang.setText(Integer.toString(phieu.getMaKH()));
        holder.soPhieu.setText(Integer.toString(phieu.getSoPhieu()));
        holder.ngayDK.setText(format.format(phieu.getNgayDK()));

        if (position%2 == 0) {
            row.setBackgroundResource(R.color.colorWhite);
        }
        else {
            row.setBackgroundResource(R.color.colorGray);
        }

        return row;
    }

    @Override
    public void notifyDataSetChanged(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PhieuDangKyAdapter.super.notifyDataSetChanged();
            }
        });
    }

}
