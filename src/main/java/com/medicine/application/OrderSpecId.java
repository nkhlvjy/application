package com.medicine.application;

import java.io.Serializable;
import java.util.Objects;

public class OrderSpecId implements Serializable {

    int c_unique_id;
    int quantity;
    String c_name;
    int order_id;

    public OrderSpecId() {
    }

    public OrderSpecId(int c_unique_id, int quantity, String c_name, int order_id) {
        this.c_unique_id = c_unique_id;
        this.quantity = quantity;
        this.c_name = c_name;
        this.order_id = order_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSpecId accountId = (OrderSpecId) o;
        return c_name.equals(accountId.c_name) &&
                quantity == accountId.quantity && c_name.equals(accountId.c_name)
                && order_id==accountId.order_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c_unique_id, c_name, quantity);
    }
}
