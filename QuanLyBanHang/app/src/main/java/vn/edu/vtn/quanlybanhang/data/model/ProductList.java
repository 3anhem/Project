package vn.edu.vtn.quanlybanhang.data.model;

import java.io.Serializable;

public class ProductList implements Serializable {
    private String image;
    private String name;
    private String priceSale;
    private String priceStart;
    private String percentSale;

    public ProductList() {
    }

    public ProductList(String image, String name, String priceStart, String priceSale, String percentSale) {
        this.image = image;
        this.name = name;
        this.priceSale = priceSale;
        this.priceStart = priceStart;
        this.percentSale = percentSale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public String getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(String priceStart) {
        this.priceStart = priceStart;
    }

    public String getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(String percentSale) {
        this.percentSale = percentSale;
    }
}
