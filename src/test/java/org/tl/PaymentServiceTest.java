package org.tl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private OrderService orderService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        paymentService = new PaymentService(orderService);
    }

    @Test
    void pay_shouldMarkOrderAsPaid() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertTrue(paymentService.pay(order.getId(), 100.0));
        assertEquals("PAID", order.getStatus());
    }

    @Test
    void pay_shouldFailOnWrongAmount() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertFalse(paymentService.pay(order.getId(), 50.0));
        assertEquals("CREATED", order.getStatus());
    }

    @Test
    void pay_shouldFailForCancelledOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);
        orderService.cancelOrder(order.getId());

        assertFalse(paymentService.pay(order.getId(), 100.0));
    }

    @Test
    void refund_shouldCancelPaidOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);
        paymentService.pay(order.getId(), 100.0);

        assertTrue(paymentService.refund(order.getId()));
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void refund_shouldFailForUnpaidOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertFalse(paymentService.refund(order.getId()));
    }
}
