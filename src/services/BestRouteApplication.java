package services;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import manager.PathsGenerator;
import manager.TreeBuilder;

public class BestRouteApplication {

    public static void main(String[] args) {
        final int customerRestaurantPairs = 2;
        Double pt1 = 50.0;
        Double pt2 = 70.0;
        Double amanLatitude = 5.0;
        Double amanLongitude = 10.0;
        Double c1Latitude = 10.0;
        Double c1Longitude = 20.0;
        Double r1Latitude = 20.0;
        Double r1Longitude = 30.0;
        Double c2Latitude = 40.0;
        Double c2Longitude = 20.0;
        Double r2Latitude = 30.0;
        Double r2Longitude = 30.0;

        Location amanLocation = new Location(amanLatitude, amanLongitude);
        Location c1Location = new Location(c1Latitude, c1Longitude);
        Location r1Location = new Location(r1Latitude, r1Longitude);
        Location c2Location = new Location(c2Latitude, c2Longitude);
        Location r2Location = new Location(r2Latitude, r2Longitude);

        DeliveryAgent deliveryAgent = new DeliveryAgent("1", amanLocation);

        List<Order> lc1 = new ArrayList<>();
        List<Order> lr1 = new ArrayList<>();
        Customer c1 = new Customer(c1Location, lc1);
        Restaurant r1 = new Restaurant(r1Location, pt1, lr1);
        Order o1 = new Order(c1, r1, "1", deliveryAgent, OrderState.RECEIVED);
        lc1.add(o1);
        lr1.add(o1);

        List<Order> lc2 = new ArrayList<>();
        List<Order> lr2 = new ArrayList<>();
        Customer c2 = new Customer(c2Location, lc2);
        Restaurant r2 = new Restaurant(r2Location, pt2, lr2);
        Order o2 = new Order(c2, r2, "2", deliveryAgent, OrderState.RECEIVED);
        lc2.add(o2);
        lr2.add(o2);
        
        PathsGenerator generator = new PathsGenerator();
        List<List<String>> validPathsList = generator.getValidPaths(customerRestaurantPairs);

        HashMap<String, Restaurant> restaurantMap = new HashMap<>();
        restaurantMap.put("R1", r1);
        restaurantMap.put("R2", r2);

        HashMap<String, Location> locationMap = new HashMap<>();
        locationMap.put("A", amanLocation);
        locationMap.put("R1", r1Location);
        locationMap.put("C1", c1Location);
        locationMap.put("R2", r2Location);
        locationMap.put("C2", c2Location);

        HashMap<String, Double> graph = precomputeDistance(customerRestaurantPairs, locationMap);

        TreeBuilder treeBuilder = new TreeBuilder();
        
        List<TreeNode> leafNodeList = new ArrayList<>();
        treeBuilder.buildTree(validPathsList, restaurantMap, locationMap, leafNodeList, graph);
        
        TreeNode minCostNode = getMinCostNode(leafNodeList);
        System.out.println("Cost: " + minCostNode.getCost());
        if(minCostNode!=null) {
            List<TreeNode> optimizedPath = getOptimizedPath(minCostNode);
            printPath(optimizedPath);
        }
        
    }   

    private static void printPath(List<TreeNode> optimizedPath) {
        int i;
        System.out.print("Path: ");
        for(i=optimizedPath.size()-1; i>0; i--){
            System.out.print(optimizedPath.get(i).getNodeName() + "->");
        }
        System.out.println(optimizedPath.get(i).getNodeName());
    }

    public static TreeNode getMinCostNode(List<TreeNode> leafNodeList) {
        double cost = Integer.MAX_VALUE;
        TreeNode optimizedLeafNode = null;
        for(TreeNode leafNode: leafNodeList) {
            if (cost > leafNode.getCost()) {
                cost = leafNode.getCost();
                optimizedLeafNode = leafNode;
            }
        }
        return optimizedLeafNode;
    }

    public static List<TreeNode> getOptimizedPath(TreeNode node) {
        List<TreeNode> optimizedPath = new ArrayList<>();
        while (node != null) {
            optimizedPath.add(node);
            node = node.getParent();
        }
        return optimizedPath;
    }

    public static HashMap<String, Double> precomputeDistance(int customerRestaurantPairs, HashMap<String, Location> locationMap) {
        HashMap<String, Double> graph = new HashMap<>();
        HaversineDistance harvesineDistance = new HaversineDistance();

        // save all distance from Aman
        for (int i = 1;i<=customerRestaurantPairs;i++) {
            String restaurantKey = "R" + i;
            Double restaurantDistance = harvesineDistance.calculateDistance(locationMap.get("A"), locationMap.get(restaurantKey));

            graph.put("A" + restaurantKey, restaurantDistance);
            graph.put(restaurantKey + "A", restaurantDistance);
        }

        // save complete graph of restaurants and customers
        for (int i = 1;i<=customerRestaurantPairs;i++) {
            String sourceCustomerKey = "C" + i;
            String sourceRestaurant = "R" + i;
            for (int j = 1;j<=customerRestaurantPairs;j++) {
                String customerKey = "C" + j;
                String restaurantKey = "R" + j;

                Double restaurantDistance = harvesineDistance.calculateDistance(locationMap.get(sourceCustomerKey), locationMap.get(restaurantKey));
                Double customerDistance = harvesineDistance.calculateDistance(locationMap.get(sourceCustomerKey), locationMap.get(customerKey));
                Double restaurantSourceRestaurantDistance = harvesineDistance.calculateDistance(locationMap.get(sourceRestaurant), locationMap.get(restaurantKey));
                
                if (i != j) {
                    graph.put(sourceCustomerKey + customerKey, customerDistance);
                    graph.put(customerKey + sourceCustomerKey, customerDistance);

                    graph.put(sourceRestaurant + restaurantKey, restaurantSourceRestaurantDistance);
                    graph.put(restaurantKey + sourceRestaurant, restaurantSourceRestaurantDistance);
                }

                graph.put(sourceCustomerKey + restaurantKey, restaurantDistance);
                graph.put(restaurantKey + sourceCustomerKey, restaurantDistance);
            }
        }
        return graph;
    }
}
