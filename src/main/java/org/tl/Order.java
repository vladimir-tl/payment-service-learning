package org.tl;

public class Order {
    private final String customer;
    private final double amount;
    private OrderStatus status;

    public Order(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
        this.status = OrderStatus.CREATED;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
