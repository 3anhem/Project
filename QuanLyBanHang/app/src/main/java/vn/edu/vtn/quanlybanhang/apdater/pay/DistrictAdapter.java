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
import vn.edu.vtn.quanlybanhang.data.model.address.District;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {
    private Context context;
    private ArrayList<District> districtArrayList;
    private ArrayList<District> temp = new ArrayList<>();
    private static DistrictAdapter.ClickListener clickListener;

    public DistrictAdapter(Context context, ArrayList<District> districtArrayList) {
        this.context = context;
        this.districtArrayList = districtArrayList;
        temp.addAll(districtArrayList);
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
        District district = districtArrayList.get(i);
        viewHolder.txtProvinceName.setText(district.getTenQuanHuyen());
    }

    @Override
    public int getItemCount() {
        return districtArrayList.size();
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

    public void setOnItemClickListener(DistrictAdapter.ClickListener clickListener) {
        DistrictAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void filter(String text) {

        districtArrayList.clear();
        if (text.length() == 0) {
            districtArrayList.addAll(temp);
        } else {
            ArrayList<District> districts = new ArrayList<>();
            for (District district : temp) {
                Log.d("AAAA", temp.size() + "");
                String dat = text.toLowerCase();
                if (district.getTenQuanHuyen().toLowerCase().contains(dat)) {
                    districts.add(district);
                }
            }
            districtArrayList.clear();
            districtArrayList.addAll(districts);
        }
        notifyDataSetChanged();
    }
}
