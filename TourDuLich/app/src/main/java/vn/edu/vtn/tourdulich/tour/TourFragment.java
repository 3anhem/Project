package vn.edu.vtn.tourdulich.tour;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.vtn.tourdulich.R;
import vn.edu.vtn.tourdulich.data.api.APIService;
import vn.edu.vtn.tourdulich.data.api.APIUtils;
import vn.edu.vtn.tourdulich.data.model.Tour;
import vn.edu.vtn.tourdulich.main.MainActivity;

public class TourFragment extends Fragment {
    APIService apiService;
    ListView lvTour;


    TourAdapter tourAdapter;
    FloatingActionButton myFab;

    private void getListTour() {
        apiService.getListTour().enqueue(new Callback<ArrayList<Tour>>() {
            @Override
            public void onResponse(Call<ArrayList<Tour>> call, Response<ArrayList<Tour>> response) {
                ArrayList<Tour> listTour = response.body();
                Log.d("API", listTour.toString());
                tourAdapter = new TourAdapter(getContext(), R.layout.row_tour, listTour);
                lvTour.setAdapter(tourAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Tour>> call, Throwable t) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_tour, viewGroup, false);

        lvTour = (ListView) v.findViewById(R.id.lvTour);
        myFab = (FloatingActionButton) v.findViewById(R.id.fab);
        registerForContextMenu(lvTour);
        apiService = APIUtils.getServer();
        this.getListTour();

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(getView().getContext());
                View promptsView = li.inflate(R.layout.info_tour, null);

                final EditText tourRoute = (EditText) promptsView
                        .findViewById(R.id.txtRoute);
                final EditText tourJourney = (EditText) promptsView
                        .findViewById(R.id.txtJourney);
                final EditText tourPrice = (EditText) promptsView
                        .findViewById(R.id.txtPirce);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getView().getContext());


                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setPositiveButton("Thêm", null);
                alertDialogBuilder.setNegativeButton("Hủy", null);

                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (tourRoute.getText().toString().trim().equalsIgnoreCase("")) {
                            tourRoute.requestFocus();
                            tourRoute.setError("Vui lòng nhập lộ trình");
                        } else if (tourJourney.getText().toString().trim().equalsIgnoreCase("")) {
                            tourJourney.requestFocus();
                            tourJourney.setError("Vui lòng nhập hành trình");
                        } else if (tourPrice.getText().toString().trim().equalsIgnoreCase("")) {
                            tourPrice.requestFocus();
                            tourPrice.setError("Vui lòng nhập giá tour");
                        } else {
                            Tour tour = new Tour();
                            tour.setJourney(tourJourney.getText().toString());
                            tour.setPrice(Double.parseDouble(tourPrice.getText().toString()));
                            tour.setRoute(tourRoute.getText().toString());
                            final ProgressDialog dialog = ProgressDialog.show(getView().getContext(), "",
                                    "Vui lòng chờ...", true);
                            apiService.addTour(tour).enqueue(new Callback<Tour>() {
                                @Override
                                public void onResponse(Call<Tour> call, Response<Tour> response) {

                                    alertDialog.dismiss();
                                    getListTour();
                                    dialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<Tour> call, Throwable t) {
                                    Log.d("API", "onFailure: " + t.getMessage());
                                    new AlertDialog.Builder(getView().getContext())
                                            .setTitle("Lỗi")
                                            .setMessage("Đã có lỗi trong quá trình thêm. Vui lòng thử lại.?")
                                            .show();
                                    dialog.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        });


        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Sửa");
        menu.add(0, v.getId(), 0, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView ids = (TextView) getView().findViewById(R.id.tvTourId);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        final Tour tour = tourAdapter.getItem(position);


        if (item.getTitle() == "Sửa") {

            LayoutInflater li = LayoutInflater.from(getView().getContext());
            View promptsView = li.inflate(R.layout.info_tour, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getView().getContext());


            alertDialogBuilder.setView(promptsView);

            final EditText tourRoute = (EditText) promptsView
                    .findViewById(R.id.txtRoute);
            final EditText tourJourney = (EditText) promptsView
                    .findViewById(R.id.txtJourney);
            final EditText tourPrice = (EditText) promptsView
                    .findViewById(R.id.txtPirce);

            tourRoute.setText(tour.getRoute());
            tourJourney.setText(tour.getJourney());
            tourPrice.setText(String.valueOf(tour.getPrice()));

            alertDialogBuilder.setView(promptsView);
            alertDialogBuilder.setPositiveButton("Sửa", null);
            alertDialogBuilder.setNegativeButton("Hủy", null);

            // create alert dialog
            final AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (tourRoute.getText().toString().trim().equalsIgnoreCase("")) {
                        tourRoute.requestFocus();
                        tourRoute.setError("Vui lòng nhập lộ trình");
                    } else if (tourJourney.getText().toString().trim().equalsIgnoreCase("")) {
                        tourJourney.requestFocus();
                        tourJourney.setError("Vui lòng nhập hành trình");
                    } else if (tourPrice.getText().toString().trim().equalsIgnoreCase("")) {
                        tourPrice.requestFocus();
                        tourPrice.setError("Vui lòng nhập giá tour");
                    } else {

                        tour.setJourney(tourJourney.getText().toString());
                        tour.setPrice(Double.parseDouble(tourPrice.getText().toString()));
                        tour.setRoute(tourRoute.getText().toString());

                        final ProgressDialog dialog = ProgressDialog.show(getView().getContext(), "",
                                "Vui lòng chờ...", true);
                        apiService.updateTour(tour).enqueue(new Callback<Tour>() {
                            @Override
                            public void onResponse(Call<Tour> call, Response<Tour> response) {
                                alertDialog.dismiss();
                                getListTour();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Tour> call, Throwable t) {
                                Log.d("API", "onFailure: " + t.getMessage());
                                new AlertDialog.Builder(getView().getContext())
                                        .setTitle("Lỗi")
                                        .setMessage("Đã có lỗi trong quá trình sửa. Vui lòng thử lại.?")
                                        .show();
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });
        } else {
            new AlertDialog.Builder(getView().getContext())
                    .setTitle("Xóa tour")
                    .setMessage("Bạn có chắc muốn xóa " + tour.getRoute() + "?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog = ProgressDialog.show(getView().getContext(), "",
                                    "Vui lòng chờ...", true);
                            final DialogInterface finalDialog = dialog;
                            apiService.deleteTour(tour.getId()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Log.d("AAAA", response.code() + " " + tour.getId());
                                    finalDialog.dismiss();
                                    getListTour();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.d("API", "onFailure: " + t.getMessage());
                                    new AlertDialog.Builder(getView().getContext())
                                            .setTitle("Lỗi")
                                            .setMessage("Đã có lỗi trong quá trình xóa. Vui lòng thử lại.?")
                                            .show();
                                    finalDialog.dismiss();
                                }
                            });
                        }
                    })
                    .setNegativeButton("Hủy", null).show();
        }


        return true;
    }
}
