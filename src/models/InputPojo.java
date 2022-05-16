package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputPojo {

    DeliveryAgent deliveryAgent;

    List<Customer> customers;

    List<Restaurant> restaurants;

    public InputPojo() {
        this.customers = new ArrayList<>();
        this.restaurants = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("deliveryAgent")
    private void unpackDeliveryAgent(Map<String,Object> deliveryAgentMap) {
        Map<String, Double> locationMap = (Map<String, Double>) deliveryAgentMap.get("startLocation");
        Location location = new Location(locationMap.get("latitude"), locationMap.get("longitude"));
        this.deliveryAgent = new DeliveryAgent(deliveryAgentMap.get("agentId").toString(), location);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("customers")
    private void unpackCustomers(List<Map<String, Object>> customersList) {
        for(Map<String, Object> customerMap: customersList) {
            Map<String, Double> locationMap = (Map<String, Double>) customerMap.get("customerLocation");
            Location location = new Location(locationMap.get("latitude"), locationMap.get("longitude"));
            Customer customer = new Customer(customerMap.get("customerId").toString(), location, new ArrayList<>());
            this.customers.add(customer);
        }
    }
    
    @SuppressWarnings("unchecked")
    @JsonProperty("restaurants")
    private void unpackRestaurants(List<Map<String, Object>> restaurantList) {
        for(Map<String, Object> restaurantMap: restaurantList) {
            Map<String, Double> locationMap = (Map<String, Double>) restaurantMap.get("restaurantLocation");
            Location location = new Location(locationMap.get("latitude"), locationMap.get("longitude"));
            Restaurant restaurant = new Restaurant(restaurantMap.get("restaurantId").toString(), location, (Double) restaurantMap.get("preparationTime"), new ArrayList<>());
            this.restaurants.add(restaurant);
        }
    }
    
    public DeliveryAgent getDeliveryAgent() {
        return this.deliveryAgent;
    }   

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }    
}
