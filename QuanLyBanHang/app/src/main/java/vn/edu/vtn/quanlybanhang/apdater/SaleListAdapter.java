package vn.edu.vtn.quanlybanhang.apdater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.SaleLists;

public class SaleListAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<SaleLists> list;

    public SaleListAdapter(Context context, ArrayList<SaleLists> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SaleLists saleLists = list.get(position);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context)
                .load(saleLists.getUrlHinh())
//                .apply(new RequestOptions().override(385, 215))
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
