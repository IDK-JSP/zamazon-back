package com.bart.zamazon.entitys;

public class Orders {
    private int order_id;
    private String email;
    private Double total;

    public Orders(int order_id, String email, Double total) {
        this.order_id = order_id;
        this.email = email;
        this.total = total;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
