package com.bart.zamazon.entitys;

public class Product {
    private int product_id;
    private String product_name;
    private String poster_path;
    private String description;

    public Product(int product_id, String product_name, String poster_path, String description) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.poster_path = poster_path;
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
