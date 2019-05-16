package vn.edu.vtn.quanlybanhang.apdater.pay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Province> provinceArrayList;
    private ArrayList<Province> temp;
    private static ClickListener clickListener;

    public ProvinceAdapter(Context context, ArrayList<Province> provinceArrayList) {
        this.context = context;
        this.provinceArrayList = provinceArrayList;
        temp = new ArrayList<>();
        temp.addAll(provinceArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_province_district, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Province province = provinceArrayList.get(i);
        viewHolder.txtProvinceName.setText(province.getTen());
    }

    @Override
    public int getItemCount() {
        return provinceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtProvinceName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProvinceName = itemView.findViewById(R.id.txtNameProvince);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ProvinceAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void filter(String text) {

        provinceArrayList.clear();
        if (text.length() == 0) {
            provinceArrayList.addAll(temp);
        } else {
            ArrayList<Province> provinces = new ArrayList<>();
            for (Province province : temp) {
                Log.d("AAAA", temp.size() + "");
                String dat = text.toLowerCase();
                if (province.getTen().toLowerCase().contains(dat)) {
                    provinces.add(province);
                }
            }
            provinceArrayList.clear();
            provinceArrayList.addAll(provinces);
        }
        notifyDataSetChanged();
    }
}

