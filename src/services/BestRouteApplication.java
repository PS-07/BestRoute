package services;

import models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import manager.PathsGenerator;
import manager.TreeBuilder;
import utils.ServiceUtils;

public class BestRouteApplication {

    public static void main(String[] args) {
        DeliveryAgent deliveryAgent = new DeliveryAgent("1", ServiceUtils.amanLocation);

        // just for design level understanding, values aren't used anywhere
        Customer customer1 = new Customer(ServiceUtils.c1Location);
        Restaurant restaurant1 = new Restaurant(ServiceUtils.r1Location, ServiceUtils.pt1);
        Order order1 = new Order(customer1, restaurant1, "1", deliveryAgent, OrderState.RECEIVED);
        customer1.getOrders().add(order1);
        restaurant1.getOrders().add(order1);

        Customer customer2 = new Customer(ServiceUtils.c2Location);
        Restaurant restaurant2 = new Restaurant(ServiceUtils.r2Location, ServiceUtils.pt2);
        Order order2 = new Order(customer2, restaurant2, "2", deliveryAgent, OrderState.RECEIVED);
        customer2.getOrders().add(order2);
        restaurant2.getOrders().add(order2);
        
        PathsGenerator generator = new PathsGenerator();
        List<List<String>> validPathsList = generator.getValidPaths(ServiceUtils.customerRestaurantPairs);

        HashMap<String, Restaurant> restaurantMap = new HashMap<>();
        restaurantMap.put("R1", restaurant1);
        restaurantMap.put("R2", restaurant2);

        HashMap<String, Location> locationMap = ServiceUtils.getLocationMap();

        HashMap<String, Double> graph = precomputeDistance(ServiceUtils.customerRestaurantPairs, locationMap);

        TreeBuilder treeBuilder = new TreeBuilder();
        
        List<TreeNode> leafNodeList = new ArrayList<>();
        treeBuilder.buildTree(validPathsList, restaurantMap, locationMap, leafNodeList, graph);
        
        TreeNode minCostNode = getMinCostNode(leafNodeList);

        if(minCostNode!=null) {
            System.out.println("Cost: " + minCostNode.getCost());
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
