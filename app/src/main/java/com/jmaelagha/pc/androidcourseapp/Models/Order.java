package com.jmaelagha.pc.androidcourseapp.Models;

import java.util.ArrayList;

public class Order {
    private int order_id;
    private String user_id;
    private ArrayList<OrderItem> order_list= new ArrayList<>();


    public Order(int order_id, String user_id, ArrayList<OrderItem> order_list) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_list = order_list;
    }

    public Order() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<OrderItem> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(ArrayList<OrderItem> order_list) {
        this.order_list = order_list;
    }
}
