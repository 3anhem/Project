package vn.edu.vtn.quanlybanhang.apdater.pay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;

public class AddressReciveAdapter extends RecyclerView.Adapter<AddressReciveAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AddressLists> lists;
    private static ClickListener clickListener;
    private int mSelectedItem = -1;
    private boolean firstCheck = true;

    public AddressReciveAdapter(Context context, ArrayList<AddressLists> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_address, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i == 0 && !viewHolder.radioButton.isChecked() && firstCheck) {
            viewHolder.radioButton.setChecked(true);
            firstCheck = false;
        } else {
            viewHolder.radioButton.setChecked(false);
            viewHolder.radioButton.setChecked(i == mSelectedItem);
        }
        viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtUsername, txtPhone, txtAddress;
        ImageView imgDeleteAddress, imgEditAddress;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            imgDeleteAddress = itemView.findViewById(R.id.imgDeleteAddress);
            imgEditAddress = itemView.findViewById(R.id.imgEditAddress);
            radioButton = itemView.findViewById(R.id.radioButton);


            imgDeleteAddress.setOnClickListener(this);
            imgEditAddress.setOnClickListener(this);
            radioButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bindData(int position) {
            AddressLists addressLists = lists.get(position);
            txtUsername.setText(addressLists.getTenKhachHang());
            txtPhone.setText(addressLists.getSoDt());
            txtAddress.setText(addressLists.getTenDiaChi());
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(AddressReciveAdapter.ClickListener clickListener) {
        AddressReciveAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setRadioButton(int position) {
        mSelectedItem = position;
        notifyDataSetChanged();
    }
}
