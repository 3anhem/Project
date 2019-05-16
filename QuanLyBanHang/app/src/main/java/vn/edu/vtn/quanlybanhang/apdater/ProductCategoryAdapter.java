package vn.edu.vtn.quanlybanhang.apdater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Random;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {
    private static ClickListener clickListener;
    private ArrayList<ProductCategory> listProductCategory;
    private Context context;

    public ProductCategoryAdapter(ArrayList<ProductCategory> listProductCategory, Context context) {
        this.context = context;
        this.listProductCategory = listProductCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_product_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindData(i);
    }

    @Override
    public int getItemCount() {
        return listProductCategory.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgHinh;
        TextView txtName;
        CardView cvProductCategory;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgHinh);
            txtName = itemView.findViewById(R.id.txtName);
            cvProductCategory = itemView.findViewById(R.id.cvProductCategory);
            itemView.setOnClickListener(this);
        }

        void bindData(int position) {
            ProductCategory category = listProductCategory.get(position);
            txtName.setText(category.getName());
            Glide.with(context)
                    .load(category.getImage())
//                    .apply(new RequestOptions().override(80, 80))
                    .into(imgHinh);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ProductCategoryAdapter.ClickListener clickListener) {
        ProductCategoryAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

