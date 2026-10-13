package org.tl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void createOrder_shouldReturnOrderWithCreatedStatus() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertNotNull(order);
        assertEquals("Ivan", order.getCustomer());
        assertEquals(100.0, order.getAmount());
        assertEquals("CREATED", order.getStatus());
    }

    @Test
    void cancelOrder_shouldSetCancelledStatus() {
        Order order = orderService.createOrder("Ivan", 100.0);

        assertTrue(orderService.cancelOrder(order.getId()));
        assertEquals("CANCELLED", orderService.getOrder(order.getId()).getStatus());
    }

    @Test
    void cancelOrder_shouldReturnFalseForUnknownOrder() {
        assertFalse(orderService.cancelOrder(999));
    }

    @Test
    void getOrder_shouldReturnNullForUnknownOrder() {
        assertNull(orderService.getOrder(999));
    }
}
