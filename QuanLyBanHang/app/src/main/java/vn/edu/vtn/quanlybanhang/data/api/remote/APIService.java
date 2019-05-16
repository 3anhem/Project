package vn.edu.vtn.quanlybanhang.data.api.remote;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.edu.vtn.quanlybanhang.data.model.bill.Bill;
import vn.edu.vtn.quanlybanhang.data.model.ProductFavorite;
import vn.edu.vtn.quanlybanhang.data.model.SaleLists;
import vn.edu.vtn.quanlybanhang.data.model.address.Address;
import vn.edu.vtn.quanlybanhang.data.model.address.AddressLists;
import vn.edu.vtn.quanlybanhang.data.model.Customer;
import vn.edu.vtn.quanlybanhang.data.model.address.District;
import vn.edu.vtn.quanlybanhang.data.model.ListRVProduct;
import vn.edu.vtn.quanlybanhang.data.model.PagingProduct;
import vn.edu.vtn.quanlybanhang.data.model.Product;
import vn.edu.vtn.quanlybanhang.data.model.ProductCategory;
import vn.edu.vtn.quanlybanhang.data.model.address.Province;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;
import vn.edu.vtn.quanlybanhang.data.model.bill.BillList;
import vn.edu.vtn.quanlybanhang.data.model.bill.LoginFCM;
import vn.edu.vtn.quanlybanhang.data.model.bill.PagingBill;
import vn.edu.vtn.quanlybanhang.data.model.Password;
import vn.edu.vtn.quanlybanhang.data.model.bill.ProductBillDetail;

public interface APIService {
    //Check Login
//    @GET("KhachHang/DangNhap")
//    Call<Customer> getInfo(@Query("ten") String email, @Query("matKhau") String password);

    @POST("KhachHang/DangNhapFCM")
    Call<Customer> getInfo(@Body LoginFCM loginFCM);

    @GET("KhachHang/DangXuatFCM")
    Call<Void> logout(@Query("idKhachHang") int id);

    // Sign Up New Customer
    @POST("KhachHang/insert")
    Call<Customer> setInfo(@Body Customer customer);

    @POST("KhachHang/update")
    Call<Customer> updateInfo(@Body Customer customer);

    @POST("KhachHang/doiMatKhau")
    Call<Void> changePassword(@Body Password password);

    @GET("HomePage/home")
    Call<ArrayList<ListRVProduct>> getProduct();

    @GET("DanhMucSanPham/danhSach")
    Call<ArrayList<ProductCategory>> getProductCategory();

    @GET("SanPham/getTheoDanhMuc")
    Call<ArrayList<Product>> getProductLists(@Query("id") int id);

    @POST("SanPham/getPhanTrang")
    Call<ArrayList<Product>> getProductLists(@Body PagingProduct pagingProduct);

    @GET("Tinh/lay")
    Call<ArrayList<Province>> getProvinceLists();

    @GET("Quan/lay")
    Call<ArrayList<District>> getDistrictLists(@Query("idTinh") String idTinh);

    @GET("Xa/lay")
    Call<ArrayList<XaPhuong>> getXaPhuongLists(@Query("idQuan") String idQuan);

    @GET("DiaChiKhachHang/DanhSach")
    Call<ArrayList<AddressLists>> getAddressLists(@Query("id") int id);

    @POST("DiaChiKhachHang/taoDiaChiMoi")
    Call<Address> CreateNewAddress(@Body Address address);

    @POST("DiaChiKhachHang/update")
    Call<Void> updateAdress(@Body AddressLists addressLists);

    @DELETE("DiaChiKhachHang/XoaDiaChi")
    Call<Void> deleteAdress(@Query("id") int id);

    @POST("DonDatHang/TaoHoaDon")
    Call<Bill> createNewBill(@Body Bill bill);

    //Check Exists
    @POST("SanPhamYeuThich/kiemTra")
    Call<ProductFavorite> checkExists(@Query("idSanPham") int idProduct, @Query("IdKhachHang") int idCustomer);

    @POST("SanPhamYeuThich/themMoi")
    Call<ProductFavorite> setProductFavorite(@Body ProductFavorite favorite);

    @GET("SanPhamYeuThich/layMot")
    Call<ArrayList<ProductFavorite>> getProductFavorite(@Query("id") int id);

    @DELETE("SanPhamYeuThich/Xoa")
    Call<Void> deleteProductFavorite(@Query("id") int id);

    @GET("DanhSachKhuyenMai/getListKhuyenMai")
    Call<ArrayList<SaleLists>> getSaleLists();

    @POST("DonDatHang/getList")
    Call<ArrayList<BillList>> getListBill(@Body PagingBill pagingBill);

    @GET("DonDatHang/hoaDon")
    Call<ProductBillDetail> getListBillDetail(@Query("id") int id);

    @POST("FCM/TaoMoi")
    Call<Void> sendTokenAndId(@Query("token") String token, @Query("device") String device);
}
