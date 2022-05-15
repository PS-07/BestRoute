package models;

import java.util.List;

public class Customer {

    private String customerId;

    private Location customerLocation;

    private List<Order> orders;

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public Customer(String customerId, Location customerLocation, List<Order> orders) {
        this.customerId = customerId;
        this.customerLocation = customerLocation;
        this.orders = orders;
    }


    public Location getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final Location customerLocation) {
        this.customerLocation = customerLocation;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }
}
