package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Location restaurantLocation;
    private Double preparationTime;
    private List<Order> orders;

    public void acceptOrder(final Order order) {
        this.orders.add(order);
    }

    public boolean isOrderPickedUp(final Order order) {
        return false;
    }

    public Restaurant(final Location restaurantLocation, final Double preparationTime) {
        this.restaurantLocation = restaurantLocation;
        this.preparationTime = preparationTime;
        this.orders = new ArrayList<>();
    }

    public Location getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(final Location restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public Double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(final Double preparationTime) {
        this.preparationTime = preparationTime;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }
}
