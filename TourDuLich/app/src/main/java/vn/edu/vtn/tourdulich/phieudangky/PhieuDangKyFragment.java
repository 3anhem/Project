package vn.edu.vtn.tourdulich.phieudangky;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.model.ChiTietPhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.Customer;
import vn.edu.vtn.tourdulich.data.model.PhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.Tour;

public class PhieuDangKyFragment extends Fragment {

    private PhieuDangKyViewModel mViewModel;
    Context context;

    public static PhieuDangKyFragment newInstance() {
        return new PhieuDangKyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.phieu_dang_ky_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PhieuDangKyViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.init((Activity) context);
        mViewModel.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdatePhieuKhachHang(v);
            }
        });


        mViewModel.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertInsertPhieuDangKy(v);
            }
        });

        mViewModel.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeletePhieuDangKy(v);
            }
        });
    }

    private void showAlertInsertPhieuDangKy(View v) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.save_phieudangky, null);

        final EditText soPhieu = view.findViewById(R.id.save_SoPhieu);
        soPhieu.setText("");

        final EditText ngayDK = view.findViewById(R.id.save_NgayDK);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        ngayDK.setText(format.format(new Date()));


        final EditText textSoNguoi = view.findViewById(R.id.soNguoi);
        textSoNguoi.setText("1");

        final Spinner spinnerKhachHang = view.findViewById(R.id.spinner_khachhang);
        ArrayList<Customer> arrKhachHang = mViewModel.getListKhachHang();
        KhachHangSpinnerAdapter khachHangSpinnerAdapter = new KhachHangSpinnerAdapter(context, R.layout.layout_textview_for_spinner, arrKhachHang);
        spinnerKhachHang.setAdapter(khachHangSpinnerAdapter);

        final Spinner spinnerTour = view.findViewById(R.id.spinner_maTour);
        ArrayList<Tour> arrTour = mViewModel.getListTour();
        TourSpinnerAdapter tourSpinnerAdapter = new TourSpinnerAdapter(context, R.layout.layout_textview_for_spinner, arrTour);
        spinnerTour.setAdapter(tourSpinnerAdapter);


        new AlertDialog.Builder(context)
                .setTitle("Thêm Phiếu Đăng Ký")
                .setIcon(R.mipmap.them_icon)
                .setView(view)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int phieuSoPhieu = -1;

                        Customer khachHang = (Customer) spinnerKhachHang.getSelectedItem();
                        int phieuKhachHang = Integer.parseInt(khachHang.getCustomerCode());

                        Tour tour = (Tour) spinnerTour.getSelectedItem();
                        int idTour = tour.getId();

                        int soNguoi = Integer.parseInt(textSoNguoi.getText().toString());

                        String phieuNgayDK = ngayDK.getText().toString();
                        PhieuDangKy phieu = new PhieuDangKy(phieuSoPhieu, phieuNgayDK, phieuKhachHang);
                        ChiTietPhieuDangKy chiTiet = new ChiTietPhieuDangKy();
                        chiTiet.setMaTour(idTour);
                        chiTiet.setSoNguoi(soNguoi);
                        phieu.setChiTiet(chiTiet);
                        mViewModel.insertPhieuDangKy(phieu);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

    }

    private void showUpdatePhieuKhachHang(View v) {
        PhieuDangKy phieu = mViewModel.getSelectedItem();
        if (phieu == null) {
            return;
        }
        if (phieu.getChiTiet() == null) {
            ArrayList<ChiTietPhieuDangKy> arrCT = mViewModel.getListChiTietPDK();
            int indexCT = mViewModel.getIndexArrayChiTietPDK(phieu.getSoPhieu());
            phieu.setChiTiet(arrCT.get(indexCT));
        }
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.save_phieudangky, null);
        final EditText soPhieu = view.findViewById(R.id.save_SoPhieu);
        soPhieu.setText(Integer.toString(phieu.getSoPhieu()));

        final EditText ngayDK = view.findViewById(R.id.save_NgayDK);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        ngayDK.setText(format.format(phieu.getNgayDK()));


        final EditText soNguoi = view.findViewById(R.id.soNguoi);
        soNguoi.setText(Integer.toString(phieu.getChiTiet().getSoNguoi()));

        final Spinner spinnerKhachHang = view.findViewById(R.id.spinner_khachhang);
        ArrayList<Customer> arrKhachHang = mViewModel.getListKhachHang();
        KhachHangSpinnerAdapter khachHangSpinnerAdapter = new KhachHangSpinnerAdapter(context, R.layout.layout_textview_for_spinner, arrKhachHang);
        spinnerKhachHang.setAdapter(khachHangSpinnerAdapter);
        int indexKhachHang = mViewModel.getIndexArrayKhachHang(phieu.getMaKH());
        if (indexKhachHang != -1) {
            spinnerKhachHang.setSelection(indexKhachHang);
        }

        final Spinner tourSpinner = view.findViewById(R.id.spinner_maTour);
        ArrayList<Tour> arrTour = mViewModel.getListTour();
        TourSpinnerAdapter tourSpinnerAdapter = new TourSpinnerAdapter(context, R.layout.layout_textview_for_spinner, arrTour);
        tourSpinner.setAdapter(tourSpinnerAdapter);
        int indexTour = mViewModel.getIndexArrayTour(phieu.getChiTiet().getMaTour());
        if (indexTour != -1) {
            tourSpinner.setSelection(indexTour);
        }


        new AlertDialog.Builder(context)
                .setTitle("Sửa Phiếu Đăng Ký")
                .setIcon(R.mipmap.sua_icon)
                .setView(view)
                .setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhieuDangKy phieu = mViewModel.getSelectedItem();
                        Customer khachHang = (Customer) spinnerKhachHang.getSelectedItem();
                        phieu.setSoPhieu(Integer.parseInt(soPhieu.getText().toString()));
                        phieu.setMaKH(Integer.parseInt(khachHang.getCustomerCode()));
                        phieu.setNgayDK(ngayDK.getText().toString());
                        System.out.println(ngayDK.getText());
                        ChiTietPhieuDangKy chiTiet = phieu.getChiTiet();
                        chiTiet.setSoNguoi(Integer.parseInt(soNguoi.getText().toString()));
                        Tour tour = (Tour) tourSpinner.getSelectedItem();
                        chiTiet.setMaTour(tour.getId());
                        mViewModel.updatePhieuDangKy(phieu);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showDeletePhieuDangKy(View v) {
        PhieuDangKy phieu = mViewModel.getSelectedItem();
        if (phieu == null) return;
        String message = String.format("Xóa phiếu số %d", phieu.getSoPhieu());
        new AlertDialog.Builder(context)
                .setTitle("Xóa")
                .setMessage(message)
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PhieuDangKy phieu = mViewModel.getSelectedItem();
                        mViewModel.deletePhieuDangKy(phieu);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setIcon(R.mipmap.warning_icon)
                .show();
    }
}
