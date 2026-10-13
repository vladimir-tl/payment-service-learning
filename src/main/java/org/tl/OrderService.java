package org.tl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private Order order;

    public Order createOrder(String customer, double amount) {
        order = new Order(customer, amount);
        log.info("Order created: customer={}, amount={}", customer, amount);
        return order;
    }

    public boolean cancelOrder() {
        if (order == null) {
            log.warn("Cancel failed: no order");
            return false;
        }
        if (order.getStatus() == OrderStatus.PAID) {
            log.warn("Cancel failed: order is already paid");
            return false;
        }
        order.setStatus(OrderStatus.CANCELLED);
        log.info("Order cancelled");
        return true;
    }

    public Order getOrder() {
        return order;
    }
}
