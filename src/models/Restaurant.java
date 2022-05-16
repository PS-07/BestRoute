package models;

import java.util.List;

public class Restaurant {

    private String restaurantId;

    private Location restaurantLocation;

    private Double preparationTime;

    // added for extensibility
    private List<Order> orders;

    public String getRestaurantId() {
        return this.restaurantId;
    }

    public Restaurant(String restaurantId, Location restaurantLocation, Double preparationTime, List<Order> orders) {
        this.restaurantId = restaurantId;
        this.restaurantLocation = restaurantLocation;
        this.preparationTime = preparationTime;
        this.orders = orders;
    }

    public Location getRestaurantLocation() {
        return restaurantLocation;
    }

    public Double getPreparationTime() {
        return preparationTime;
    }
}
