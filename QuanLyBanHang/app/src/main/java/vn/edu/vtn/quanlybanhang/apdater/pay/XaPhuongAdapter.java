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
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;

public class XaPhuongAdapter extends RecyclerView.Adapter<XaPhuongAdapter.ViewHolder> {
    private Context context;
    private ArrayList<XaPhuong> xaPhuongArrayList;
    private ArrayList<XaPhuong> temp;
    private static ClickListener clickListener;

    public XaPhuongAdapter(Context context, ArrayList<XaPhuong> xaPhuongArrayList) {
        this.context = context;
        this.xaPhuongArrayList = xaPhuongArrayList;
        temp = new ArrayList<>();
        temp.addAll(xaPhuongArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_province_district, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XaPhuongAdapter.ViewHolder viewHolder, int i) {
        XaPhuong xaPhuong = xaPhuongArrayList.get(i);
        viewHolder.txtXaPhuongName.setText(xaPhuong.getTen());
    }

    @Override
    public int getItemCount() {
        return xaPhuongArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtXaPhuongName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtXaPhuongName = itemView.findViewById(R.id.txtNameProvince);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        XaPhuongAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void filter(String text) {

        xaPhuongArrayList.clear();
        if (text.length() == 0) {
            xaPhuongArrayList.addAll(temp);
        } else {
            ArrayList<XaPhuong> xaPhuongs = new ArrayList<>();
            for (XaPhuong xaPhuong : temp) {
                Log.d("AAAA", temp.size() + "");
                String dat = text.toLowerCase();
                if (xaPhuong.getTen().toLowerCase().contains(dat)) {
                    xaPhuongs.add(xaPhuong);
                }
            }
            xaPhuongArrayList.clear();
            xaPhuongArrayList.addAll(xaPhuongs);
        }
        notifyDataSetChanged();
    }
}
