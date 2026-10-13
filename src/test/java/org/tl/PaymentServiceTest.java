package org.tl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    @Test
    void pay_shouldMarkOrderAsPaid() {
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService(orderService);
        Order order = orderService.createOrder("Ivan", 100.0);

        assertTrue(paymentService.pay(100.0));
        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void pay_shouldFailOnWrongAmount() {
    }

    @Test
    void pay_shouldFailForCancelledOrder() {
    }

    @Test
    void refund_shouldCancelPaidOrder() {
    }

    @Test
    void refund_shouldFailForUnpaidOrder() {
    }
}
