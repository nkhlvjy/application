package com.medicine.application;

import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {

    int order_id;
    int c_unique_id;
    int quantity;
    String c_name;

    public Order(int order_id, int c_unique_id, int quantity, String c_name) {
        this.order_id = order_id;
        this.c_unique_id = c_unique_id;
        this.quantity = quantity;
        this.c_name = c_name;
    }
    public Order(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getC_unique_id() {
        return c_unique_id;
    }

    public void setC_unique_id(int c_unique_id) {
        this.c_unique_id = c_unique_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
