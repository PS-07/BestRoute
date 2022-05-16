package models;

import java.util.List;

public class Customer {

    private String customerId;

    private Location customerLocation;

    // added for extensibility
    private List<Order> orders;

    public String getCustomerId() {
        return this.customerId;
    }

    public Customer(String customerId, Location customerLocation, List<Order> orders) {
        this.customerId = customerId;
        this.customerLocation = customerLocation;
        this.orders = orders;
    }

    public Location getCustomerLocation() {
        return customerLocation;
    }
}
