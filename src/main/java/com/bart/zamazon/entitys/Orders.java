package com.bart.zamazon.entitys;

public class Orders {
    private int order_id;
    private String email;

    public Orders(int order_id, String email) {
        this.order_id = order_id;
        this.email = email;
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
}
