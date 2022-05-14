package models;

import java.util.List;

public class Customer {
    private Location customerLocation;
    private List<Order> orders;

    public Customer(final Location customerLocation, final List<Order> orders) {
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
