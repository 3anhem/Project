package vn.edu.vtn.tourdulich.chitietphieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.model.ChiTiet;

public class ChiTietAdapter extends ArrayAdapter<ChiTiet> {
    Context contex;
    int resource;
    ArrayList<ChiTiet> objects;

    public ChiTietAdapter(Context context, int resource, ArrayList<ChiTiet> objects) {
        super(context, resource, objects);
        this.contex = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.contex).inflate(this.resource, parent, false);
            holder = new ViewHolder();
            holder.txtId = convertView.findViewById(R.id.txtId);
            holder.txtMaTour = convertView.findViewById(R.id.txtMaTour);
            holder.txtSoNguoi = convertView.findViewById(R.id.txtSoNguoi);
            holder.txtSoPhieu = convertView.findViewById(R.id.txtSoPhieu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChiTiet chiTiet = this.objects.get(position);
        holder.txtId.setText(chiTiet.getId() + "");
        holder.txtSoPhieu.setText(chiTiet.getSoPhieu() + "");
        holder.txtMaTour.setText(chiTiet.getMaTour() + "");
        holder.txtSoNguoi.setText(chiTiet.getSoNguoi() + "");

        return convertView;
    }

    static class ViewHolder {
        TextView txtId, txtSoPhieu, txtMaTour, txtSoNguoi;

    }
}
