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

        assertTrue(paymentService.pay(100.0));
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void pay_shouldFailOnWrongAmount() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertFalse(paymentService.pay(50.0));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void pay_shouldFailForCancelledOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);
        orderService.cancelOrder();

        assertFalse(paymentService.pay(100.0));
    }

    @Test
    void refund_shouldCancelPaidOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);
        paymentService.pay(100.0);

        assertTrue(paymentService.refund());
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    void refund_shouldFailForUnpaidOrder() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertFalse(paymentService.refund());
    }
}
