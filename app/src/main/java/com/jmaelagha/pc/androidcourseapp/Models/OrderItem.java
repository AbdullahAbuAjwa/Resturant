package com.jmaelagha.pc.androidcourseapp.Models;

public class OrderItem {
    Menu order_item;
    int number;

    public OrderItem(Menu order_item, int number) {
        this.order_item = order_item;
        this.number = number;
    }

    public OrderItem() {
    }

    public Menu getOrder_item() {
        return order_item;
    }

    public void setOrder_item(Menu order_item) {
        this.order_item = order_item;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
