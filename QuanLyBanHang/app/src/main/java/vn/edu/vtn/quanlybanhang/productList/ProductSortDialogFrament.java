package vn.edu.vtn.quanlybanhang.productList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import vn.edu.vtn.quanlybanhang.R;
import vn.edu.vtn.quanlybanhang.pay.newAddress.dialog.DistrictDialogFragment;

public class ProductSortDialogFrament extends AppCompatDialogFragment {
    //    productSortDialogListener listener;
    RadioGroup radioGroup;
    RadioButton radioLowPrice;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sort, null);
        builder.setView(view);


        radioGroup = view.findViewById(R.id.radioGroup);
        radioLowPrice = view.findViewById(R.id.radioLowPrice);
       // radioLowPrice.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioLowPrice) {
                    // listener.onFinishProductSortDialog("Giá thấp", 0);
                    Intent intent = getActivity().getIntent();
                    intent.putExtra("SORT_NAME", "Giá thấp");
                    intent.putExtra("SORT_TYPE", 0);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 1508, intent);

                    dismiss();
                }
                if (checkedId == R.id.radioHighPrice) {
                    // listener.onFinishProductSortDialog("Giá cao", 1);
                    Intent intent = getActivity().getIntent();
                    intent.putExtra("SORT_NAME", "Giá cao");
                    intent.putExtra("SORT_TYPE", 1);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 1508, intent);
                    dismiss();
                }
                if (checkedId == R.id.radioLowPercent) {
                    //listener.onFinishProductSortDialog("Giảm giá nhiều", 3);
                    Intent intent = getActivity().getIntent();
                    intent.putExtra("SORT_NAME", "Giảm nhiều");
                    intent.putExtra("SORT_TYPE", 2);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 1508, intent);
                    dismiss();
                }
            }
        });
        return builder.create();
    }

//    public interface productSortDialogListener {
//        void onFinishProductSortDialog(String productSortName, int productSortType);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the EditNameDialogListener so we can send events to the host
//            listener = (productSortDialogListener) context;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(context.toString()
//                    + " must implement EditNameDialogListener " + e.getMessage());
//        }
//    }
}
