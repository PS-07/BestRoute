package models;

import java.util.List;

public class Restaurant {

    private String restaurantId;

    private Location restaurantLocation;

    private Double preparationTime;

    private List<Order> orders;

    public String getRestaurantId() {
        return this.restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void acceptOrder(final Order order) {
        this.orders.add(order);
    }

    public boolean isOrderPickedUp(final Order order) {
        return false;
    }

    public Restaurant(String restaurantId, Location restaurantLocation, Double preparationTime, List<Order> orders) {
        this.restaurantId = restaurantId;
        this.restaurantLocation = restaurantLocation;
        this.preparationTime = preparationTime;
        this.orders = orders;
    }

    public Restaurant(Location restaurantLocation, Double preparationTime) {
        this.restaurantLocation = restaurantLocation;
        this.preparationTime = preparationTime;
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
