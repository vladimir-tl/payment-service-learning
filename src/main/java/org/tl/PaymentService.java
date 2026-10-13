package org.tl;

public class PaymentService {
    private final OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    public boolean pay(int orderId, double amount) {
        Order order = orderService.getOrder(orderId);
        if (order == null || !order.getStatus().equals("CREATED") || order.getAmount() != amount) {
            return false;
        }
        order.setStatus("PAID");
        return true;
    }

    public boolean refund(int orderId) {
        Order order = orderService.getOrder(orderId);
        if (order == null || !order.getStatus().equals("PAID")) {
            return false;
        }
        order.setStatus("CANCELLED");
        return true;
    }
}
