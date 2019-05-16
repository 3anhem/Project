package vn.edu.vtn.quanlybanhang.data.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int idCart;
    private int idProduct;
    private String name;
    private float salePrice;
    private String price;
    private int amount;
    private String image;
    private int status;

    public Cart(int amount) {
        this.amount = amount;
    }

    public Cart(int idCart, int idProduct, String name, float salePrice, String price, int amount, String image, int status) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.name = name;
        this.salePrice = salePrice;
        this.price = price;
        this.amount = amount;
        this.image = image;
        this.status = status;
    }

    public Cart(int idProduct, String name, float salePrice, String price, int amount, String image, int status) {
        this.idProduct = idProduct;
        this.name = name;
        this.salePrice = salePrice;
        this.price = price;
        this.amount = amount;
        this.image = image;
        this.status = status;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
