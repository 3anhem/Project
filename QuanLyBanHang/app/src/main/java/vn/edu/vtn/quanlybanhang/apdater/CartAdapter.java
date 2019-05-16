package vn.edu.vtn.quanlybanhang.apdater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.cart.CartDiffCallback;
import vn.edu.vtn.quanlybanhang.data.model.Cart;
import vn.edu.vtn.quanlybanhang.data.model.Product;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Cart> cartArrayList;
    private Context context;
    private static ClickListener clickListener;


    public CartAdapter(ArrayList<Cart> cartArrayList, Context contex) {
        this.cartArrayList = cartArrayList;
        this.context = contex;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cart, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindingData(i);

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgProductImage, imgDelete;
        TextView txtProductName, txtProductSalePrice, txtProductPrice,txtQuantity;
        Button btnMinus, btnPlus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductImage = itemView.findViewById(R.id.imgProductImage);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductSalePrice = itemView.findViewById(R.id.txtProductSalePrice);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);

            imgDelete.setOnClickListener(this);
            btnPlus.setOnClickListener(this);
            btnMinus.setOnClickListener(this);

        }

        void bindingData(int position) {
            Cart cart = cartArrayList.get(position);
            Glide.with(context)
                    .load(cart.getImage())
                    .into(imgProductImage);
            txtProductName.setText(cart.getName());

            String formattedPriceSale = new DecimalFormat("#,### đ").format(cart.getSalePrice());
            txtProductSalePrice.setText(formattedPriceSale);


            float price = Float.parseFloat(cart.getPrice());
            String formattedPrice = new DecimalFormat("#,### đ").format(price);
            txtProductPrice.setText(formattedPrice + "");
            txtQuantity.setText(cart.getAmount() + "");

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }


    }

    public void setOnItemClickListener(CartAdapter.ClickListener clickListener) {
        CartAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void refreshData(ArrayList<Cart> carts) {
        cartArrayList.clear();
        cartArrayList.addAll(carts);
        notifyDataSetChanged();
    }

    public void setAmount(int amount, int postion) {
//        txtQuantity.setText(amount + "");
        cartArrayList.get(postion).setAmount(amount);
        notifyItemChanged(postion);
    }

    public void updateData(ArrayList<Cart> carts) {
        CartDiffCallback cartDiffCallback = new CartDiffCallback(cartArrayList, carts);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(cartDiffCallback);
        cartArrayList.clear();
        cartArrayList.addAll(carts);
        result.dispatchUpdatesTo(this);
    }
}
