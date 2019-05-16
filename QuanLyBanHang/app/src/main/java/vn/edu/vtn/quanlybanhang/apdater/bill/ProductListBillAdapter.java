package vn.edu.vtn.quanlybanhang.apdater.bill;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillList;

public class ProductListBillAdapter extends RecyclerView.Adapter<ProductListBillAdapter.ViewHolder> {
    private Context context;
    private List<ProductBillList> list;

    public ProductListBillAdapter(Context context, List<ProductBillList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_last_form, viewGroup, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtProductSalePrice, txtAmount;
        ImageView imgProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductSalePrice = itemView.findViewById(R.id.txtProductSalePrice);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            imgProductImage = itemView.findViewById(R.id.imgProductImage);
        }

        public void bindData(int position) {
            final ProductBillList product = list.get(position);
            txtProductName.setText(product.getProduct().getName());

            float price = Float.parseFloat(product.getProduct().getPrice());
            String formattedPrice = new DecimalFormat("#,### đ").format(price);
            txtProductSalePrice.setText(formattedPrice);


            txtAmount.setText(product.getSoLuong() + "");
            Glide.with(context)
                    .load(product.getProduct().getImage())
                    .into(imgProductImage);

        }
    }
}
