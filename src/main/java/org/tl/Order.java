package org.tl;

public class Order {
    private final int id;
    private final String customer;
    private final double amount;
    private String status;

    public Order(int id, String customer, double amount) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.status = "CREATED";
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
