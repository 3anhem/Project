package vn.edu.vtn.quanlybanhang.pay.newAddress.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.quanlybanhang.R;

import vn.edu.vtn.quanlybanhang.apdater.pay.XaPhuongAdapter;
import vn.edu.vtn.quanlybanhang.data.model.address.XaPhuong;

public class XaPhuongDialogFragment extends AppCompatDialogFragment {
    private ArrayList<XaPhuong> list;
    public ArrayList<XaPhuong> temp;

    private XaPhuongAdapter xaPhuongAdapter;
    private RecyclerView recyclerView;

    private EditText txtSearch;
    private TextView txtTitle;
    private ImageView imgClose;

    private String hintText, title;

    XaPhuongDialogListener xaPhuongDialogListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            title = getArguments().getString("TITLE", "");
            hintText = getArguments().getString("HINT", "");
            list = (ArrayList<XaPhuong>) getArguments().getSerializable("LIST");

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_province, null);

        builder.setView(view);
        addControls(view);
        addEvents();

        return builder.create();
    }


    private void addControls(View view) {
        recyclerView = view.findViewById(R.id.rv_province);
        txtSearch = view.findViewById(R.id.txtSearch);
        txtTitle = view.findViewById(R.id.txtTitle);
        imgClose = view.findViewById(R.id.imgClose);

        txtTitle.setText(title);
        txtSearch.setHint(hintText);
        xaPhuongAdapter = new XaPhuongAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(xaPhuongAdapter);

    }

    private void addEvents() {
        temp = list;
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                ArrayList<XaPhuong> xaPhuongs = new ArrayList<>();
//
//                for (XaPhuong xaPhuong : list) {
//                    String dat = s.toString().toLowerCase();
//                    if (xaPhuong.getTen().toLowerCase().contains(dat)) {
//                        xaPhuongs.add(xaPhuong);
//                    }
//                }
//                temp = xaPhuongs;
//                recyclerView.setAdapter(new XaPhuongAdapter(getContext(), temp));
                xaPhuongAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        xaPhuongAdapter.setOnItemClickListener(new XaPhuongAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                XaPhuong xaPhuong = list.get(position);
                xaPhuongDialogListener.onFinishXaPhuongDialog(xaPhuong.getTen(), xaPhuong.getMaXaPhuong());
                dismiss();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface XaPhuongDialogListener {
        void onFinishXaPhuongDialog(String xaPhuongName, String XaPhuongCode); // pass data to NewAddressActivity
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the EditNameDialogListener so we can send events to the host
            xaPhuongDialogListener = (XaPhuongDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }
}
