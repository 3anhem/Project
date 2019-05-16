package vn.edu.vtn.quanlybanhang.apdater.bill;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.bill.BillList;


public class BillManagementAdapter extends RecyclerView.Adapter<BillManagementAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BillList> list;
    private static ClickListener clickListener;

    public BillManagementAdapter(Context context, ArrayList<BillList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_bill_manage, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtBillCode, txtOderDate, txtStatus;
        ImageView imgStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBillCode = itemView.findViewById(R.id.txtBillCode);
            txtOderDate = itemView.findViewById(R.id.txtOderDate);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            imgStatus = itemView.findViewById(R.id.imgStatus);
            itemView.setOnClickListener(this);
        }

        public void bindData(int position) {
            final BillList billList = list.get(position);
            txtBillCode.setText(billList.getId() + "");
            txtOderDate.setText(billList.getNgayLap());
            txtStatus.setText(billList.getTinhTrang());
            if (billList.getTinhTrang().equals("Đặt hàng thành công")) {
                imgStatus.setImageResource(R.drawable.check_detail);
            }
            if (billList.getTinhTrang().equals("Đang đóng gói")) {
                imgStatus.setImageResource(R.drawable.status_pack);
            }
            if (billList.getTinhTrang().equals("Đang vận chuyển")) {
                imgStatus.setImageResource(R.drawable.status_deliver);
            }
            if (billList.getTinhTrang().equals("Đặt hàng thành công")) {
                imgStatus.setImageResource(R.drawable.status_pay_success);
            }
            if (billList.getTinhTrang().equals("Đã hủy")) {
                imgStatus.setImageResource(R.drawable.check_error);
            }
            if (billList.getTinhTrang().equals("VTN đã tiếp nhận đơn hàng")) {
                imgStatus.setImageResource(R.drawable.status_vtn_receive);
            }
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(BillManagementAdapter.ClickListener clickListener) {
        BillManagementAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
