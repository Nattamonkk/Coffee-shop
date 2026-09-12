package com.example.coffeeshop;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String customerName;

    private List<OrderItem> items = new ArrayList<>();

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public void addItem(Product product, int quantity) {

        OrderItem item =
                new OrderItem(product, quantity);

        items.add(item);
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }
}