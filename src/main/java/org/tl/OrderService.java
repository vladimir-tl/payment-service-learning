package org.tl;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextId = 1;

    public Order createOrder(String customer, double amount) {
        Order order = new Order(nextId++, customer, amount);
        orders.put(order.getId(), order);
        return order;
    }

    public boolean cancelOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order == null || order.getStatus().equals("PAID")) {
            return false;
        }
        order.setStatus("CANCELLED");
        return true;
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }
}
