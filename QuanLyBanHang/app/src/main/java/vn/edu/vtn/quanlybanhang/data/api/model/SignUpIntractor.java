package vn.edu.vtn.quanlybanhang.data.api.model;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.quanlybanhang.util.ProgressDialogF;
import vn.edu.vtn.quanlybanhang.data.api.APIHelper;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIService;
import vn.edu.vtn.quanlybanhang.data.api.remote.APIUtils;
import vn.edu.vtn.quanlybanhang.data.model.Customer;

public class SignUpIntractor {
    APIHelper apiHelper;
    APIService service;
    Context context;

    public SignUpIntractor(APIHelper apiHelper, Context context) {
        this.apiHelper = apiHelper;
        service = APIUtils.getServer();
        this.context = context;
    }

    public void initRetrofitCall(Customer customer) {
        ProgressDialogF.showLoading(context);
        service.setInfo(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.code() == 400) {
                    apiHelper.onFailure("Email hoặc số điện thoại của bạn đã trùng !!!");
                }
                if (response.code() == 200) {
                    apiHelper.onSucess("Bạn đã đăng ký thành công, vui lòng kiểm tra email để xác thực tài khoản !!! ");
                }
                ProgressDialogF.hideLoading();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                ProgressDialogF.hideLoading();
            }
        });
//        service.setInfo(customer).enqueue(new Callback<Customer>() {
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
////                if (response.isSuccessful()) {
////                    if (response.body() != null) {
////                        String result = response.body().toString();
////                        if (result.equals("true")) {
////                            apiHelper.onSucess("Sign Up Success !!!");
////                        } else {
////                            apiHelper.onFailure("Your email was exists !!!");
////                        }
////                    } else {
////                        apiHelper.onFailure("Do not Success ");
////                    }
////                } else {
////                    apiHelper.onFailure("ERROR onResponse  : " + response.code());
////                }
//                Toast.makeText(context, response.code() + "", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable t) {
//                apiHelper.onFailure("ERROR onFailure  : " + t.toString());
//            }
//        });


//        service.setInfo(email, username, password, phone).enqueue(new Callback<Object>() {
//
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        String result = response.body().toString();
//                        if (result.equals("true")) {
//                            apiHelper.onSucess("Sign Up Success !!!");
//                        } else {
//                            apiHelper.onFailure("Your email was exists !!!");
//                        }
//                    } else {
//                        apiHelper.onFailure("Do not Success ");
//                    }
//                } else {
//                    apiHelper.onFailure("ERROR onResponse  : " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                apiHelper.onFailure("ERROR onFailure  : " + t.toString());
//            }
//        });
    }
}
