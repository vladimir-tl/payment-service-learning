package org.tl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    public boolean pay(double amount) {
        Order order = orderService.getOrder();
        if (order == null) {
            log.warn("Payment failed: no order");
            return false;
        }
        if (order.getStatus() != OrderStatus.CREATED) {
            log.warn("Payment failed: order has status {}", order.getStatus());
            return false;
        }
        if (order.getAmount() != amount) {
            log.warn("Payment failed: amount mismatch, expected={}, got={}", order.getAmount(), amount);
            return false;
        }
        order.setStatus(OrderStatus.PAID);
        log.info("Order paid: amount={}", amount);
        return true;
    }

    // TODO: implement refund: PAID order -> REFUNDED status
    public boolean refund() {
        return true;
    }
}
