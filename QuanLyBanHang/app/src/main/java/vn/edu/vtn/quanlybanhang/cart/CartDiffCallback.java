package vn.edu.vtn.quanlybanhang.cart;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;
import java.util.List;

import vn.edu.vtn.quanlybanhang.data.model.Cart;

public class CartDiffCallback extends DiffUtil.Callback {

    private final ArrayList<Cart> mOldCartList;
    private final ArrayList<Cart> mNewCartList;

    public CartDiffCallback(ArrayList<Cart> mOldCartList, ArrayList<Cart> mNewCartList) {
        this.mOldCartList = mOldCartList;
        this.mNewCartList = mNewCartList;
    }

    @Override
    public int getOldListSize() {
        return mOldCartList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewCartList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItemPosition == newItemPosition;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldCartList.get(oldItemPosition) == mNewCartList.get(newItemPosition);
    }

//    @Nullable
//    @Override
//    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
//        // Implement method if you're going to use ItemAnimator
//        return super.getChangePayload(oldItemPosition, newItemPosition);
//    }
}
