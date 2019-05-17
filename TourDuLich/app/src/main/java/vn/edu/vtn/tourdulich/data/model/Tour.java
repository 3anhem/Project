package vn.edu.vtn.tourdulich.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tour {
    @SerializedName("MATOUR")
    @Expose
    private Integer id;
    @SerializedName("LOTRINH")
    @Expose
    private String route;
    @SerializedName("HANHTRINH")
    @Expose
    private String journey;
    @SerializedName("GIATOUR")
    @Expose
    private double price;

    public  Tour(){

    }
    public Tour(Integer id, String route, String journey, double price) {
        this.id = id;
        this.route = route;
        this.journey = journey;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
