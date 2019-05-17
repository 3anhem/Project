package vn.edu.vtn.tourdulich.data.api;

import io.reactivex.Observable;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.edu.vtn.tourdulich.data.model.ChiTiet;
import vn.edu.vtn.tourdulich.data.model.ChiTietPhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.Customer;
import vn.edu.vtn.tourdulich.data.model.PhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.Tour;
import vn.edu.vtn.tourdulich.data.model.Admin;

public interface APIService {
    @GET("api/KhachHang/danhsach")
    Call<ArrayList<Customer>> getListCustomerss();

    @POST("api/DangNhap/DangNhap")
    Call<Admin> DangNhap(@Body Admin admin);

    @GET("api/ChiTietPhieuDK/DanhSach")
    Call<ArrayList<ChiTiet>> getListCTPhieu();

    @DELETE("api/ChiTietPhieuDK/Xoa")
    Call<Void> deleteCTPhieu(@Query("id") int id);

    @GET("api/Tour/danhsach")
    Call<ArrayList<Tour>> getListTour();

    @GET("api/Tour/{maTour}")
    Call<Tour> getTour(@Path("maTour") Integer tourId);

    @POST("api/Tour/ThemMoi")
    Call<Tour> addTour(@Body Tour tour);

    @POST("api/Tour/sua")
    Call<Tour> updateTour(@Body Tour tour);

    @DELETE("api/Tour/xoa")
    Call<Void> deleteTour(@Query("MATOUR") int id);

    @GET("api/PhieuDK/DanhSach")
    Call<ArrayList<PhieuDangKy>> getListPhieuDangKy();

    @POST("api/PhieuDK/Them")
    Call<PhieuDangKy> insertPhieuDangKy(@Body PhieuDangKy phieu);

    @POST("api/PhieuDK/Sua")
    Call<PhieuDangKy> updatePhieuDangKy(@Body PhieuDangKy phieu);

    @DELETE("api/PhieuDK/Xoa?")
    Call<ResponseBody> deletePhieuDangKy(@Query("SOPHIEU") int soPhieu);

    @GET("api/ChiTietPhieuDK/DanhSach")
    Call<ArrayList<ChiTietPhieuDangKy>> getListChiTietPhieuDangKy();

    @POST("api/ChiTietPhieuDK/Them")
    Call<ChiTietPhieuDangKy> insertChiTietPhieuDangKy(@Body ChiTietPhieuDangKy phieu);

    @POST("api/ChiTietPhieuDK/Sua")
    Call<ChiTietPhieuDangKy> updateChiTietPhieuDangKy(@Body ChiTietPhieuDangKy phieu);

    @DELETE("api/ChiTietPhieuDK/Xoa/{ID}")
    Call<ResponseBody> deleteChiTietPhieuDangKy(@Path("ID") int id);

    @GET("api/ChiTietPhieuDK/ChiTiet")
    Call<ChiTietPhieuDangKy> getOneDetail(@Query("id") int id);

    @GET("api/KhachHang/danhsach")
    Observable<ArrayList<Customer>> getListCustomers();

    @POST("api/KhachHang/ThemMoi")
    Observable<Customer> createCustomer(@Body JsonObject jsonObject);

    @DELETE("api/KhachHang/Xoa")
    Observable <Response<Void>> delCustomer(@Query("MAKH") String makh);

    @PUT("api/KhachHang/Sua")
    Observable <Response<Void>> editCustomer(@Body JsonObject jsonObject);

}
