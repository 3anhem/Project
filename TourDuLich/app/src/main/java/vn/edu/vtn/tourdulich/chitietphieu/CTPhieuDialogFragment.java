package vn.edu.vtn.tourdulich.chitietphieu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;
import vn.edu.vtn.tourdulich.data.model.ChiTietPhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.PhieuDangKy;
import vn.edu.vtn.tourdulich.data.model.Tour;
import vn.edu.vtn.tourdulich.phieudangky.PhieuDangKyViewModel;
import vn.edu.vtn.tourdulich.phieudangky.TourSpinnerAdapter;

public class CTPhieuDialogFragment extends AppCompatDialogFragment {
    TextView txtID, txtSoPhieu, txtMaTour, txtSoNguoi, txtSTT, txtCancel, txtOk;
    Spinner spinnerTour;
    EditText txtEditSoNguoi;
    //ImageView imgEdit;
    CTPhieuDialogListener ctPhieuDialogListener;
    ChiTietPhieuDangKy chiTietPhieuDangKy = null;
    private APIService service;
    LinearLayout llEdit;
    CheckBox chkEdit;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            chiTietPhieuDangKy = (ChiTietPhieuDangKy) getArguments().getSerializable("PCT");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout, null);
        builder.setView(view);

        addControls(view);
        addEvents();

        return builder.create();
    }

    private void addControls(View view) {
        txtID = view.findViewById(R.id.txtId);
        txtSoPhieu = view.findViewById(R.id.txtSoPhieu);
        txtMaTour = view.findViewById(R.id.txtMaTour);
        txtSoNguoi = view.findViewById(R.id.txtSoNguoi);
        txtSTT = view.findViewById(R.id.txtSTT);
        llEdit = view.findViewById(R.id.llEdit);
        chkEdit = view.findViewById(R.id.chkEdit);
        txtCancel = view.findViewById(R.id.txtCancel);
        txtOk = view.findViewById(R.id.txtOk);
        txtEditSoNguoi = view.findViewById(R.id.soNguoi);
        //imgEdit = view.findViewById(R.id.imgEdit);
        service = APIUtils.getServer();

        txtID.setText(chiTietPhieuDangKy.getID() + "");
        txtSoPhieu.setText(chiTietPhieuDangKy.getSoPhieu() + "");
        txtMaTour.setText(chiTietPhieuDangKy.getMaTour() + "");
        txtSoNguoi.setText(chiTietPhieuDangKy.getSoNguoi() + "");
        txtSTT.setText(chiTietPhieuDangKy.getSoPhieu() + "");

        spinnerTour = view.findViewById(R.id.spinner_maTour);


    }

    void getAllTour() {
        service.getListTour().enqueue(new Callback<ArrayList<Tour>>() {
            @Override
            public void onResponse(Call<ArrayList<Tour>> call, Response<ArrayList<Tour>> response) {
                ArrayList<Tour> arr = response.body();
                if (arr == null || arr.size() == 0) return;
                ArrayList<Tour> arrTour = arr;
                TourSpinnerAdapter tourSpinnerAdapter = new TourSpinnerAdapter(getContext(), R.layout.layout_textview_for_spinner, arrTour);
                spinnerTour.setAdapter(tourSpinnerAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Tour>> call, Throwable t) {

            }
        });
    }

    private void addEvents() {
        chkEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    llEdit.setVisibility(View.VISIBLE);
                    getAllTour();
                    txtEditSoNguoi.setText(chiTietPhieuDangKy.getSoNguoi() + "");
                    spinnerTour.setSelection(3);

                } else {
                    llEdit.setVisibility(View.GONE);
                }
            }
        });
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chkEdit.setChecked(false);
            }
        });
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tour tour = (Tour) spinnerTour.getSelectedItem();
                int idTour = tour.getId();
                if (txtEditSoNguoi.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Số người bị trống !!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int soNguoi = Integer.parseInt(txtEditSoNguoi.getText().toString());
                int id = chiTietPhieuDangKy.getID();
                ChiTietPhieuDangKy chiTietPhieuDangKy = new ChiTietPhieuDangKy(id, idTour, soNguoi);
                updateCTP(chiTietPhieuDangKy);
                dismiss();
            }
        });
    }

    private void updateCTP(ChiTietPhieuDangKy chiTietPhieuDangKy) {
        service.updateChiTietPhieuDangKy(chiTietPhieuDangKy).enqueue(new Callback<ChiTietPhieuDangKy>() {
            @Override
            public void onResponse(Call<ChiTietPhieuDangKy> call, Response<ChiTietPhieuDangKy> response) {
                Log.d("AAAA", response.code() + "");
            }

            @Override
            public void onFailure(Call<ChiTietPhieuDangKy> call, Throwable t) {
                Log.d("AAAA", " onFailure : " + t.toString());
            }
        });
    }

    public interface CTPhieuDialogListener {
        void onFinishCTPhieuDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the EditNameDialogListener so we can send events to the host
            ctPhieuDialogListener = (CTPhieuDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement CTPhieuDialogListener" + e.toString());
        }
    }
}
