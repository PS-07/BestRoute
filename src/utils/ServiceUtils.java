package utils;

import models.*;
import java.util.HashMap;

// all inputs go here
public class ServiceUtils {

    public static final int customerRestaurantPairs = 2;

    public static Double amanLatitude = 5.0;
    public static Double amanLongitude = 10.0;
    public static Location amanLocation = new Location(amanLatitude, amanLongitude);

    // for more customerRestaurantPairs please add pt3 and latitudes, longitudes for c_i and r_i
    public static Double pt1 = 50.0;
    public static Double pt2 = 70.0;
    
    public static Double c1Latitude = 10.0;
    public static Double c1Longitude = 20.0;
    public static Double r1Latitude = 20.0;
    public static Double r1Longitude = 30.0;
    public static Double c2Latitude = 40.0;
    public static Double c2Longitude = 20.0;
    public static Double r2Latitude = 30.0;
    public static Double r2Longitude = 30.0;


    // for more customerRestaurantPairs please add new locations
    public static Location c1Location = new Location(c1Latitude, c1Longitude);
    public static Location r1Location = new Location(r1Latitude, r1Longitude);
    public static Location c2Location = new Location(c2Latitude, c2Longitude);
    public static Location r2Location = new Location(r2Latitude, r2Longitude);

    public static HashMap<String, Location> getLocationMap() {
        HashMap<String, Location> locationMap = new HashMap<>();
        locationMap.put("A", amanLocation);
        locationMap.put("R1", r1Location);
        locationMap.put("C1", c1Location);
        locationMap.put("R2", r2Location);
        locationMap.put("C2", c2Location);

        return locationMap;
    }
}