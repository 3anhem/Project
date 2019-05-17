package vn.edu.vtn.tourdulich.tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.model.Tour;

public class TourAdapter extends ArrayAdapter<Tour> {
    private Context context;
    private int resource;
    private List<Tour> listTour;

    public TourAdapter(Context context, int resource, ArrayList<Tour> listTour) {
        super(context, resource, listTour);
        this.context = context;
        this.resource = resource;
        this.listTour = listTour;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_tour, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTourId = (TextView) convertView.findViewById(R.id.tvTourId);
            viewHolder.tvTourRoute = (TextView) convertView.findViewById(R.id.tvTourRoute);
            viewHolder.tvTourJourney = (TextView) convertView.findViewById(R.id.tvTourJourney);
            viewHolder.tvTourPrice = (TextView) convertView.findViewById(R.id.tvTourPrice);
            viewHolder.tvAvatar = (TextView) convertView.findViewById(R.id.tvAvatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Tour tour = listTour.get(position);
        viewHolder.tvTourId.setText(tour.getId().toString());
        viewHolder.tvAvatar.setText(String.valueOf(position + 1));
        viewHolder.tvTourRoute.setText(tour.getRoute());
        viewHolder.tvTourJourney.setText(tour.getJourney());
        viewHolder.tvTourPrice.setText(String.valueOf(tour.getPrice()));
        return convertView;
    }

    public class ViewHolder {
        TextView tvTourId, tvTourRoute, tvTourJourney, tvTourPrice, tvAvatar;

    }
}
