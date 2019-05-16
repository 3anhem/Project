package vn.edu.vtn.quanlybanhang.apdater;

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

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;

public class ProductFavoriteAdapter extends RecyclerView.Adapter<ProductFavoriteAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductFavorite> list;
    private static ClickListener clickListener;

    public ProductFavoriteAdapter(Context context, ArrayList<ProductFavorite> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_favorite, viewGroup, false);
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
        TextView txtProductName, txtProductPrice, txtProductSalePrice, txtPercent;
        ImageView imgProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtProductSalePrice = itemView.findViewById(R.id.txtProductSalePrice);
            txtPercent = itemView.findViewById(R.id.txtPercent);
            imgProductImage = itemView.findViewById(R.id.imgProductImage);
            itemView.setOnClickListener(this);
        }

        private void bindData(int position) {
            Product product = list.get(position).getProduct();

            txtProductName.setText(product.getName());

            float price = Float.parseFloat(product.getPrice());
            String formattedPrice = new DecimalFormat("#,### đ").format(price);
            txtProductSalePrice.setText(formattedPrice);

            float salePrice = Float.parseFloat(product.getPriceSale());
            String formattedSalePrice = new DecimalFormat("#,### đ").format(salePrice);
            txtProductPrice.setText(formattedSalePrice);

            txtPercent.setText("-" + product.getPercent() + "%");


            Glide.with(context)
                    .load(product.getImage())
                    .into(imgProductImage);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ProductFavoriteAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
