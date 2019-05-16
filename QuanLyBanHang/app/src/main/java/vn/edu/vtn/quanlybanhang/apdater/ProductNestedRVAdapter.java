package vn.edu.vtn.quanlybanhang.apdater;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.data.model.ListRVProduct;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.productList.ProductListFragment;

public class ProductNestedRVAdapter extends RecyclerView.Adapter<ProductNestedRVAdapter.ViewHolder> {
    private ArrayList<ListRVProduct> dataList;
    private Context mContext;

    public ProductNestedRVAdapter(Context context, ArrayList<ListRVProduct> dataList) {
        this.dataList = dataList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_nested, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final String sectionName = dataList.get(i).getHeaderTitle();

        final ArrayList<Product> singleSectionItems = dataList.get(i).getProductsList();

        viewHolder.itemTitle.setText(sectionName);

        ProductAdapter itemListDataAdapter = new ProductAdapter(mContext, singleSectionItems);

        viewHolder.recycler_view_list.setHasFixedSize(true);
        viewHolder.recycler_view_list.setNestedScrollingEnabled(false);
        viewHolder.recycler_view_list.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.HORIZONTAL, false));
        viewHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        viewHolder.txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductListFragment homepage = new ProductListFragment();
                FragmentTransaction fragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager()
                        .beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", singleSectionItems.get(0).getIdDanhMuc()); //key and value
                homepage.setArguments(bundle);
                fragmentManager.replace(R.id.fragment_container, homepage);
                fragmentManager.addToBackStack(null);
                fragmentManager.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle;

        RecyclerView recycler_view_list;

        TextView txtMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            recycler_view_list = itemView.findViewById(R.id.recycler_view_list);
            txtMore = itemView.findViewById(R.id.txtMore);
        }
    }
//
//    onItemClickListner onItemClickListner;
//
//    public void setOnItemClickListner(ProductNestedRVAdapter.onItemClickListner onItemClickListner) {
//        this.onItemClickListner = onItemClickListner;
//    }
//
//    public interface onItemClickListner {
//        void onClick(int position);//pass your object types.
//    }
}
