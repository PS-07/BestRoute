package utils;

import models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static InputPojo getInput() throws IOException {        
        InputStream inputStream = ServiceUtils.class.getResourceAsStream("input.json");
        if (inputStream == null) {
            throw new IllegalArgumentException("input.json file not found");
        }

        return objectMapper.readValue(
            inputStream,
            new TypeReference<InputPojo>() {}
        );

    }

    public static HashMap<String, Location> getLocationMap(DeliveryAgent deliveryAgent, List<Customer> customerList, List<Restaurant> restaurantList) {
        HashMap<String, Location> locationMap = new HashMap<>();
        
        locationMap.put(deliveryAgent.getAgentId(), deliveryAgent.getStartLocation());

        for (Customer customer : customerList) {
            locationMap.put(customer.getCustomerId(), customer.getCustomerLocation());
        }

        for (Restaurant customer : restaurantList) {
            locationMap.put(customer.getRestaurantId(), customer.getRestaurantLocation());
        }

        return locationMap;
    }
}