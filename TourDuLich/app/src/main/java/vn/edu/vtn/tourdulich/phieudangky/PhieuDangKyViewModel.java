package vn.edu.vtn.tourdulich.phieudangky;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;

import okhttp3.ResponseBody;
import retrofit2.*;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.chitietphieu.CTPhieuDialogFragment;
import vn.edu.vtn.tourdulich.data.api.*;
import vn.edu.vtn.tourdulich.data.model.*;

public class PhieuDangKyViewModel extends ViewModel implements CTPhieuDialogFragment.CTPhieuDialogListener {
    // TODO: Implement the ViewModel

    private ArrayList<PhieuDangKy> arrPhieuDangKy;
    private ArrayList<Customer> arrKhachHang;
    private ArrayList<Tour> arrTour;
    private ArrayList<ChiTietPhieuDangKy> arrChiTietPDK;
    private PhieuDangKyAdapter adapterPhieuDangKy;
    private PhieuDangKy holder = null;
    private Activity context;
    private APIService service;
    public Button update, delete, insert;
    ListView listView;

    public void init(Context context) {
        this.context = (Activity) context;
        listView = this.context.findViewById(R.id.listview);
        update = this.context.findViewById(R.id.sua);
        delete = this.context.findViewById(R.id.xoa);
        insert = this.context.findViewById(R.id.them);
        service = APIUtils.getServer();
        arrPhieuDangKy = new ArrayList<>();
        arrChiTietPDK = new ArrayList<>();
        arrKhachHang = new ArrayList<>();
        arrTour = new ArrayList<>();
        adapterPhieuDangKy = new PhieuDangKyAdapter(this.context, R.layout.row_phieudangky, arrPhieuDangKy);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                holder = arrPhieuDangKy.get(position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                holder = arrPhieuDangKy.get(position);
                openCTPhieuDialog(holder);
                return false;
            }
        });
        listView.setAdapter(adapterPhieuDangKy);

        getAllPhieuDangKy();
        getAllKhachHang();
        getAllTour();
        getAllChiTietPhieuDangKy();

    }

    private void openCTPhieuDialog(final PhieuDangKy holder) {
        service.getOneDetail(holder.getSoPhieu()).enqueue(new Callback<ChiTietPhieuDangKy>() {
            @Override
            public void onResponse(Call<ChiTietPhieuDangKy> call, Response<ChiTietPhieuDangKy> response) {
//                Log.d("AAAA", response.code() + "");
//                Log.d("AAAAB", holder.getSoPhieu() + "");
//                Log.d("AAAAB", response.body().getSoNguoi() + "");
                Bundle bundle = new Bundle();
                bundle.putSerializable("PCT", response.body());
                CTPhieuDialogFragment provinceDialogFragment = new CTPhieuDialogFragment();
                provinceDialogFragment.setArguments(bundle);
                provinceDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "Show dialog");
            }

            @Override
            public void onFailure(Call<ChiTietPhieuDangKy> call, Throwable t) {
                Log.d("AAAA", "onFailure : " + t.toString());
            }
        });

    }


    public void getAllPhieuDangKy() {
        service.getListPhieuDangKy().enqueue(new Callback<ArrayList<PhieuDangKy>>() {
            @Override
            public void onResponse(Call<ArrayList<PhieuDangKy>> call, Response<ArrayList<PhieuDangKy>> response) {
                ArrayList<PhieuDangKy> list = response.body();
                arrPhieuDangKy.addAll(list);
                adapterPhieuDangKy.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<PhieuDangKy>> call, Throwable t) {
                System.out.println(false);
            }
        });


    }

    void getAllChiTietPhieuDangKy() {
        service.getListChiTietPhieuDangKy().enqueue(new Callback<ArrayList<ChiTietPhieuDangKy>>() {
            @Override
            public void onResponse(Call<ArrayList<ChiTietPhieuDangKy>> call, Response<ArrayList<ChiTietPhieuDangKy>> response) {
                ArrayList<ChiTietPhieuDangKy> arr = response.body();
                if (arr == null || arr.size() == 0) return;
                arrChiTietPDK.addAll(arr);
            }

            @Override
            public void onFailure(Call<ArrayList<ChiTietPhieuDangKy>> call, Throwable t) {

            }
        });
    }

    void getAllTour() {
        service.getListTour().enqueue(new Callback<ArrayList<Tour>>() {
            @Override
            public void onResponse(Call<ArrayList<Tour>> call, Response<ArrayList<Tour>> response) {
                ArrayList<Tour> arr = response.body();
                if (arr == null || arr.size() == 0) return;
                arrTour.addAll(arr);
            }

            @Override
            public void onFailure(Call<ArrayList<Tour>> call, Throwable t) {

            }
        });
    }

    void getAllKhachHang() {
        service.getListCustomerss().enqueue(new Callback<ArrayList<Customer>>() {
            @Override
            public void onResponse(Call<ArrayList<Customer>> call, Response<ArrayList<Customer>> response) {
                ArrayList<Customer> arr = response.body();
                if (arr == null || arr.size() == 0) return;
                arrKhachHang.addAll(arr);
            }

            @Override
            public void onFailure(Call<ArrayList<Customer>> call, Throwable t) {

            }
        });
    }

    public void insertPhieuDangKy(final PhieuDangKy phieu) {

        service.insertPhieuDangKy(phieu).enqueue(new Callback<PhieuDangKy>() {
            @Override
            public void onResponse(Call<PhieuDangKy> call, Response<PhieuDangKy> response) {
                if (response.body() != null) {
                    PhieuDangKy result = response.body();
                    phieu.setSoPhieu(result.getSoPhieu());
                    phieu.getChiTiet().setSoPhieu(phieu.getSoPhieu());
                    insertChiTietPhieuDangKy(phieu.getChiTiet());
                    arrPhieuDangKy.add(phieu);
                    adapterPhieuDangKy.notifyDataSetChanged();
                }
                Log.d("AAAAB", response.code() + "");
            }

            @Override
            public void onFailure(Call<PhieuDangKy> call, Throwable t) {
                System.out.println(false + "\n" + t.getMessage());
                t.printStackTrace();
                Log.d("AAAAB", "onFailure" + t.toString());
            }
        });

    }

    public void insertChiTietPhieuDangKy(final ChiTietPhieuDangKy phieu) {
        service.insertChiTietPhieuDangKy(phieu).enqueue(new Callback<ChiTietPhieuDangKy>() {
            @Override
            public void onResponse(Call<ChiTietPhieuDangKy> call, Response<ChiTietPhieuDangKy> response) {
                if (response.body() != null) {
                    phieu.setID(response.body().getID());
                    arrChiTietPDK.add(phieu);
                }

            }

            @Override
            public void onFailure(Call<ChiTietPhieuDangKy> call, Throwable t) {
                Log.d("AAAA", "onFailure" + t.toString());
                t.printStackTrace();
            }
        });
    }

    public void updateChiTietPhieuDangKy(final ChiTietPhieuDangKy phieu) {
        service.updateChiTietPhieuDangKy(phieu).enqueue(new Callback<ChiTietPhieuDangKy>() {
            @Override
            public void onResponse(Call<ChiTietPhieuDangKy> call, Response<ChiTietPhieuDangKy> response) {
                if (response.body() != null) {
                    ChiTietPhieuDangKy chiIiet = response.body();
                    phieu.setSoNguoi(chiIiet.getSoNguoi());
                    phieu.setMaTour(chiIiet.getMaTour());
                } else {
                    throw new NullPointerException();
                }
            }

            @Override
            public void onFailure(Call<ChiTietPhieuDangKy> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updatePhieuDangKy(final PhieuDangKy phieu) {

        service.updatePhieuDangKy(phieu).enqueue(new Callback<PhieuDangKy>() {
            @Override
            public void onResponse(Call<PhieuDangKy> call, Response<PhieuDangKy> response) {
                if (response.body() != null) {
                    PhieuDangKy result = response.body();
                    phieu.setDateNgayDK(result.getNgayDK());
                    phieu.setSoPhieu(result.getSoPhieu());
                    phieu.setMaKH(result.getMaKH());
                    updateChiTietPhieuDangKy(phieu.getChiTiet());
                    adapterPhieuDangKy.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PhieuDangKy> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void deletePhieuDangKy(final PhieuDangKy phieu) {
        service.deletePhieuDangKy(phieu.getSoPhieu()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                listView.clearChoices();
                listView.requestLayout();
                synchronized (service) {
                    adapterPhieuDangKy.remove(phieu);
                    adapterPhieuDangKy.notifyDataSetChanged();
                    if (phieu.getChiTiet() != null) {
                        deleteChiTietPhieuDangKy(phieu.getChiTiet());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteChiTietPhieuDangKy(final ChiTietPhieuDangKy chiTiet) {
        service.deleteChiTietPhieuDangKy(chiTiet.getID()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                arrChiTietPDK.remove(chiTiet);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public PhieuDangKy getSelectedItem() {
        return holder;
    }

    public int getIndexArrayKhachHang(int id) {
        for (int i = 0; i < arrKhachHang.size(); i++) {
            if (id == Integer.parseInt(arrKhachHang.get(i).getCustomerCode())) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexArrayTour(int id) {
        for (int i = 0; i < arrTour.size(); i++) {
            if (id == arrTour.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexArrayChiTietPDK(int id) {
        for (int i = 0; i < arrChiTietPDK.size(); i++) {
            if (id == arrChiTietPDK.get(i).getSoPhieu()) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Customer> getListKhachHang() {
        return arrKhachHang;
    }

    public ArrayList<Tour> getListTour() {
        return arrTour;
    }

    public ArrayList<ChiTietPhieuDangKy> getListChiTietPDK() {
        return arrChiTietPDK;
    }

    @Override
    public void onFinishCTPhieuDialog() {

    }
}
