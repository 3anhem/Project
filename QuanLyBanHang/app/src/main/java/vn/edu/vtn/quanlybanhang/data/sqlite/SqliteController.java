package vn.edu.vtn.quanlybanhang.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.Cart;

public class SqliteController {
    private SqliteHelper sqliteHelper;
    Context context;

    public SqliteController(Context mContext) {
        this.context = mContext;
        sqliteHelper = new SqliteHelper(context);
    }


    public boolean saveCartData(Cart cart) {
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COL_PRODUCT_ID, cart.getIdProduct());
        values.put(SqliteTable.COL_PRODUCT_NAME, cart.getName());
        values.put(SqliteTable.COL_PRODUCT_SALE_PRICE, cart.getSalePrice());
        values.put(SqliteTable.COL_PRODUCT_PRICE, cart.getPrice());
        values.put(SqliteTable.COL_PRODUCT_AMUONT, cart.getAmount());
        values.put(SqliteTable.COL_PRODUCT_IMAGE, cart.getImage());
        values.put(SqliteTable.COL_CART_STATUS, cart.getStatus());

        return sqliteHelper.insertData(SqliteTable.TABLE_NAME, values, cart.getIdProduct());
    }

    public int updateCartData(Cart cart, int idProduct) {
        ContentValues values = new ContentValues();
        values.put(SqliteTable.COL_PRODUCT_ID, cart.getIdProduct());
        values.put(SqliteTable.COL_PRODUCT_NAME, cart.getName());
        values.put(SqliteTable.COL_PRODUCT_SALE_PRICE, cart.getSalePrice());
        values.put(SqliteTable.COL_PRODUCT_PRICE, cart.getPrice());
        values.put(SqliteTable.COL_PRODUCT_AMUONT, cart.getAmount());
        values.put(SqliteTable.COL_PRODUCT_IMAGE, cart.getImage());
        values.put(SqliteTable.COL_CART_STATUS, cart.getStatus());
        return sqliteHelper.updateData(SqliteTable.TABLE_NAME, values, idProduct);
    }

    public ArrayList<Cart> getDataCart() {
        return sqliteHelper.showData();
    }

    public int getTotalProduct() {
        return sqliteHelper.getTotalProduct();
    }

    public int toProcessDelete(int idCart) {
        return sqliteHelper.deleteDataWithId(idCart);
    }

    public int toProcessDeleteAll() {
        return sqliteHelper.deleteAllData();
    }

    public float getTotalMoney() {
        return sqliteHelper.getTotalMoney();
    }

    public int getAmount(int idCart) {
        return sqliteHelper.getAmount(idCart);
    }

    public int minusAmount(int idProduct) {
        return sqliteHelper.minusAmount(SqliteTable.TABLE_NAME, idProduct);
    }
}
