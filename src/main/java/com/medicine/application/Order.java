package com.medicine.application;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {

    @Column(name="order_id")
    int order_id;


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
}
