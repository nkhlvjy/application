package com.medicine.application;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Orderspecs")
@IdClass(OrderSpecId.class)
public class OrderSpec implements Serializable {
    @Id
    int c_unique_id;
    @Id
    int quantity;
    @Id
    String c_name;
    @Id
    private int order_id;


    public OrderSpec() {
    }

    public OrderSpec(int c_unique_id, int quantity, String c_name, int order) {
        this.c_unique_id = c_unique_id;
        this.quantity = quantity;
        this.c_name = c_name;
        this.order_id = order;
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

    public int getOrder() {
        return order_id;
    }

    public void setOrder(int order) {
        this.order_id = order;
    }
}
