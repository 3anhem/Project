package vn.edu.vtn.quanlybanhang.apdater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> listPhones;

    public ProductAdapter(Context context, ArrayList<Product> listPhones) {
        this.context = context;
        this.listPhones = listPhones;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_new_product, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return listPhones.size() > 4 ? 4 : listPhones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgHinh);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(new Intent(v.getContext(), ProductDetailActivity.class));
                    Product product = listPhones.get(getAdapterPosition());
                    intent.putExtra("PRODUCTLIST", product);
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bindData(int position) {
            Product product = listPhones.get(position);

            Glide.with(context)
                    .load(product.getImage())
                    .into(imageView);

            txtName.setText(product.getName());

            float salePrice = Float.parseFloat(product.getPriceSale());
            String formattedSalePrice = new DecimalFormat("#,### đ").format(salePrice);
            txtPrice.setText(formattedSalePrice);

        }
    }
}
