package vn.edu.vtn.quanlybanhang.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.data.model.Cart;

public class SqliteHelper {
    private Context context;
    private String DATABASE_NAME = "CartProduct.db";
    private SQLiteDatabase database = null;
    private static final String TAG = SqliteHelper.class.getSimpleName();

    public SqliteHelper(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null); // mở CSDL
    }


    public boolean insertData(String table, ContentValues values, int idProduct) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + SqliteTable.TABLE_NAME + " WHERE " + SqliteTable.COL_PRODUCT_ID + " =" + idProduct, null);
        if (cursor.getCount() <= 0) {
            long result = database.insert(table, null, values);
            if (result == -1) {
                Log.d(TAG, "failed to save data!");
                cursor.close();
                return false;
            } else {
                Log.d(TAG, "save data successful");
                cursor.close();
                return true;
            }

        } else {
            // Kiểm tra nếu thêm trùng thì update lại số lượng
            int amountNow = 0;
            while (cursor.moveToNext()) {
                amountNow = cursor.getInt(5);
            }
            ContentValues valuess = new ContentValues();
            valuess.put(SqliteTable.COL_PRODUCT_AMUONT, amountNow + 1);
            updateData(table, valuess, idProduct);
            cursor.close();
            return true;
        }

    }

    public int updateData(String table, ContentValues values, int idProduct) {
        return database.update(table, values,
                SqliteTable.COL_PRODUCT_ID + " =" + idProduct, null);
    }

    public ArrayList<Cart> showData() {
        ArrayList<Cart> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + SqliteTable.TABLE_NAME + " WHERE " + SqliteTable.COL_CART_STATUS + "=1", null);
        list.clear();
        while (cursor.moveToNext()) {
            int idCart = cursor.getInt(0);
            int idProduct = cursor.getInt(1);
            String name = cursor.getString(2);
            float salePrice = cursor.getFloat(3);
            String price = cursor.getString(4);
            int amount = cursor.getInt(5);
            String image = cursor.getString(6);
            int status = cursor.getInt(7);
            list.add(new Cart(idCart, idProduct, name, salePrice, price, amount, image, status));
        }
        cursor.close();
        return list;
    }

    public int deleteDataWithId(int idCart) {
        int result = database.delete(SqliteTable.TABLE_NAME, SqliteTable.COL_CART_ID + " =" + idCart, null);
        return result;
    }

    public int deleteAllData() {
        int result = database.delete(SqliteTable.TABLE_NAME, "1", null);
        return result;
    }

    public int getTotalProduct() {
        Cursor cursor = database.rawQuery("SELECT SUM(" + SqliteTable.COL_PRODUCT_AMUONT + ") FROM " + SqliteTable.TABLE_NAME, null);
        int total = 0;
        while (cursor.moveToNext()) {
            total = cursor.getInt(0);
        }
        return total;
    }

    public float getTotalMoney() {
        Cursor cursor = database.rawQuery("SELECT SUM(" + SqliteTable.COL_PRODUCT_AMUONT + "*" + SqliteTable.COL_PRODUCT_SALE_PRICE + ") FROM " + SqliteTable.TABLE_NAME, null);
        float total = 0;
        while (cursor.moveToNext()) {
            total = cursor.getFloat(0);
        }
        return total;
    }

    public int getAmount(int idCart) {
        Cursor cursor = database.rawQuery("SELECT " + SqliteTable.COL_PRODUCT_AMUONT + " FROM " + SqliteTable.TABLE_NAME + " WHERE " + SqliteTable.COL_CART_ID + "=" + idCart, null);
        int amount = 0;
        while (cursor.moveToNext()) {
            amount = cursor.getInt(0);
        }
        return amount;
    }

    public int minusAmount(String table, int idProduct) {
        Cursor cursor = database.rawQuery("SELECT " + SqliteTable.COL_PRODUCT_AMUONT + " FROM " + SqliteTable.TABLE_NAME + " WHERE " + SqliteTable.COL_PRODUCT_ID + "=" + idProduct, null);
        int amountNow = 0;
        int result;
        while (cursor.moveToNext()) {
            amountNow = cursor.getInt(0);
        }
        if (amountNow > 1) {
            ContentValues valuess = new ContentValues();
            valuess.put(SqliteTable.COL_PRODUCT_AMUONT, amountNow - 1);
            result = updateData(table, valuess, idProduct);
            cursor.close();
        } else {
            return 0;
        }

        return result;
    }
}
