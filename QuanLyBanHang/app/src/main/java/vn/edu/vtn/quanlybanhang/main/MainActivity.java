package vn.edu.vtn.quanlybanhang.main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.billmanagement.BillManagementFragment;
import vn.edu.vtn.quanlybanhang.cart.CartActivity;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.prefs.SharedPrefsHelper;
import vn.edu.vtn.quanlybanhang.data.sqlite.SqliteController;
import vn.edu.vtn.quanlybanhang.homepage.HomePageFragment;
import vn.edu.vtn.quanlybanhang.login.LoginActivity;
import vn.edu.vtn.quanlybanhang.productCategory.ProductCategoryFragment;
import vn.edu.vtn.quanlybanhang.productdetail.ProductDetailActivity;
import vn.edu.vtn.quanlybanhang.productfavorite.ProductFavoriteFragment;
import vn.edu.vtn.quanlybanhang.profile.ProfileActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainMvpView {
    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    NavigationView navigationView;
    ImageView imgCart;
    TextView txtCart_badge;

    MainMvpPresenter presenter;

    SharedPrefsHelper sharedPrefsHelper;
    SqliteController sqliteController;
    int totalProduct;


    String DATABASE_NAME = "CartProduct.db";
    String DB_PATH_SUFFIX = "/databases/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }


    private void addEvents() {
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void toProcessCopyDatabaseFromAssetsToSystem() {
        try {
            toCopyDatabaseFormAsset();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "ABC" + e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void toCopyDatabaseFormAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME); // mở file lên
            String outFileName = toGetDatabasePath(); // lấy cái đường dẫn lưu trữ
            File file = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!file.exists()) {// kiểm tra cái thư mục databases đã tồn tại chưa nếu chưa thì tạo
                file.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        } catch (Exception e) {
            Log.e("Lỗi Sao Chép !!!", e.toString());
        }
    }

    private String toGetDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME; // Trả về đường dẫn phải lưu trữ

    }

    private void addControls() {

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        fab = findViewById(R.id.fab);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        sharedPrefsHelper = new SharedPrefsHelper(this); // Check Login

        File dbFile = getDatabasePath(DATABASE_NAME);
        boolean result = sharedPrefsHelper.sharedPreferences.getBoolean("DBName", false);
        if (!dbFile.exists() && !result) {
            toProcessCopyDatabaseFromAssetsToSystem();
            sharedPrefsHelper.setDatabaseName(true);
            Log.d("AAAA", "Thành công !!!");

        } else {
            Log.d("AAAA", "Đã tồn tại trong hệ thống !!!");
        }

        presenter = new MainPresenter(this, MainActivity.this);
        sqliteController = new SqliteController(MainActivity.this);

        totalProduct = sqliteController.getTotalProduct(); // Get tổng số lượng sản phẩm

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        onOpenHomePage();

        checkLogedin();

        String token = sharedPrefsHelper.sharedPreferences.getString("TOKEN", "");
        Log.d("AAAA", token);
        if (token.equals("")) {
            getTokenFCM(); // Save Token And Device Id To Preference And API DB
        }


    }

    private void checkLogedin() {
        TextView txtHeaderName, txtHeaderEmail;
        LinearLayout linearLayout;
        View view = navigationView.getHeaderView(0);

        txtHeaderEmail = view.findViewById(R.id.txtHeaderEmail);
        txtHeaderName = view.findViewById(R.id.txtHeaderName);
        linearLayout = view.findViewById(R.id.llHeader);
        Customer customer = null;

        Menu menu = navigationView.getMenu();
        MenuItem support = menu.findItem(R.id.nav_support);
        String styledText = "HOT LINE: <font color='green'>0971.434.3988</font> (Gặp Cún)";
        support.setTitle(Html.fromHtml(styledText));

        //Kiểm tra đã đăng nhập hay chưa
        if (sharedPrefsHelper.checklogin()) {
            String json = sharedPrefsHelper.sharedPreferences.getString("CUSTOMER", "");
            customer = new Gson().fromJson(json, Customer.class);
            txtHeaderName.setText(customer.getName());
            txtHeaderEmail.setText(customer.getEmail());
        } else {
            txtHeaderName.setText("Tài khoản");
            txtHeaderEmail.setText("Đăng nhập/Đăng ký");

        }
        final Customer finalCustomer = customer;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPrefsHelper.checklogin()) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("CUSTOMER", finalCustomer);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.cart);
        View actionView = item.getActionView();
        txtCart_badge = actionView.findViewById(R.id.txtCart_badge);
        imgCart = actionView.findViewById(R.id.imgCart);

        txtCart_badge.setText(totalProduct + ""); // Set tổng số lượng của từng  sản phẩm đang có trong giỏ hàng

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                presenter.onClickHomePage();
                break;
            case R.id.nav_list:
                presenter.onProductCategory();
                break;
            case R.id.nav_oder:
                presenter.onClickOderList();
                break;
            case R.id.nav_like:
                presenter.onClickFavoriteProducts();
                break;
            case R.id.nav_user:
                presenter.onClickProfile();
                break;
            case R.id.nav_notification:
                presenter.onClickNotification();
                break;
            case R.id.nav_support:
                presenter.onClickSuport();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOpenHomePage() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomePageFragment())
                .commit();
    }

    @Override
    public void onOpenProductCategory() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProductCategoryFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onOpenOderList() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new BillManagementFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onOpenLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    public void onOpenFavoriteProducts() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProductFavoriteFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onOpenProfile() {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));

    }

    @Override
    public void onOpenNotification() {

    }

    @Override
    public void onOpenSuport() {
        String phoneNumber = "09714343988";
        startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalProduct = sqliteController.getTotalProduct();
        if (txtCart_badge != null) {
            txtCart_badge.setText(totalProduct + "");
        }
    }

    private void getTokenFCM() {
        final String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.d("AAAA", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        Log.d("AAAA", token);
                        sharedPrefsHelper.setTokenAndId(token, android_id);
                        presenter.onSendTokenAndId(token, android_id);
                    }
                });

    }
}
