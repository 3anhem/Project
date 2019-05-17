package vn.edu.vtn.tourdulich.phieudangky;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vtn.tourdulich.data.model.Tour;

public class TourSpinnerAdapter extends ArrayAdapter<Tour> {

    Context context;
    int layoutID;
    ArrayList<Tour> arr;

    public TourSpinnerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Tour> arr) {
        super(context, resource, arr);
        this.context = context;
        this.layoutID = resource;
        this.arr = arr;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView item = (TextView) super.getView(position, view, parent);
        Tour tour = arr.get(position);
        String text = String.format("[%d] %s", tour.getId(), tour.getJourney());
        item.setText(text);
        return item;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        TextView item = (TextView) super.getDropDownView(position, view, parent);
        Tour tour = arr.get(position);
        String text = String.format("[%d] %s", tour.getId(), tour.getJourney());
        item.setText(text);
        return item;
    }
}
