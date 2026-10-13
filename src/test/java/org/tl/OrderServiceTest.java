package org.tl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void createOrder_shouldReturnOrderWithCreatedStatus() {
        OrderService orderService = new OrderService();

        Order order = orderService.createOrder("Ivan", 100.0);

        assertNotNull(order);
        assertEquals("Ivan", order.getCustomer());
        assertEquals(100.0, order.getAmount());
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void cancelOrder_shouldSetCancelledStatus() {
    }

    @Test
    void cancelOrder_shouldReturnFalseWhenNoOrder() {
    }

    @Test
    void getOrder_shouldReturnNullWhenNoOrder() {
    }
}
