package com.zy.test;

import org.springframework.stereotype.Repository;

@Repository("order1")
public class Order {
    private long id;

    public Order(long id) {
        this.id = id;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
