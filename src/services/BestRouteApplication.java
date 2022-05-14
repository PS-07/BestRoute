package services;

import models.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;

public class BestRouteApplication {
    public static void main(String[] args) {
        Double pt1 = 50.0;
        Double pt2 = 70.0;
        Double d_lat = 5.0;
        Double d_lng = 10.0;
        Double c1_lat = 10.0;
        Double c1_lng = 20.0;
        Double r1_lat = 20.0;
        Double r1_lng = 30.0;
        Double c2_lat = 40.0;
        Double c2_lng = 20.0;
        Double r2_lat = 30.0;
        Double r2_lng = 30.0;

        Location d_loc = new Location(d_lat, d_lng);
        Location c1_loc = new Location(c1_lat, c1_lng);
        Location r1_loc = new Location(r1_lat, r1_lng);
        Location c2_loc = new Location(c2_lat, c2_lng);
        Location r2_loc = new Location(r2_lat, r2_lng);

        Double hd_d_r1 = new HaversineDistance().calculateDistance(d_loc, r1_loc);
        Double hd_d_r2 = new HaversineDistance().calculateDistance(d_loc, r2_loc);
        Double hd_r1_r2 = new HaversineDistance().calculateDistance(r1_loc, r2_loc);
        Double hd_r1_c1 = new HaversineDistance().calculateDistance(r1_loc, c1_loc);
        Double hd_r1_c2 = new HaversineDistance().calculateDistance(r1_loc, c2_loc);
        Double hd_r2_c1 = new HaversineDistance().calculateDistance(r2_loc, c1_loc);
        Double hd_r2_c2 = new HaversineDistance().calculateDistance(r2_loc, c2_loc);
        Double hd_c1_c2 = new HaversineDistance().calculateDistance(c1_loc, c2_loc);

        DeliveryAgent d = new DeliveryAgent("1", d_loc);

        List<Order> lc1 = new ArrayList<>();
        List<Order> lr1 = new ArrayList<>();
        Customer c1 = new Customer(c1_loc, lc1);
        Restaurant r1 = new Restaurant(r1_loc, pt1, lr1);
        Order o1 = new Order(c1, r1, "1", d, OrderState.RECEIVED);
        lc1.add(o1);
        lr1.add(o1);

        List<Order> lc2 = new ArrayList<>();
        List<Order> lr2 = new ArrayList<>();
        Customer c2 = new Customer(c2_loc, lc2);
        Restaurant r2 = new Restaurant(r2_loc, pt2, lr2);
        Order o2 = new Order(c2, r2, "2", d, OrderState.RECEIVED);
        lc2.add(o1);
        lr2.add(o1);

        Double path_d_r1 = Math.max(hd_d_r1, pt1) + hd_r1_r2;
        Double path_d_r1_r2 = Math.max((path_d_r1 + hd_r1_r2), pt2);
        Double path_d_r2 = Math.max(hd_d_r2, pt2);
        Double path_d_r2_r1 = Math.max((path_d_r2 + hd_r1_r2), pt1);

        // path 1: A -> R1 -> R2 -> C1 -> C2
        Double path1 = path_d_r1_r2 + hd_r2_c1 + hd_c1_c2;

        // path 2: A -> R1 -> R2 -> C2 -> C1
        Double path2 = path_d_r1_r2 + hd_r2_c2 + hd_c1_c2;

        // path 3: A -> R1 -> C1 -> R2 -> C2
        Double path3 = Math.max((path_d_r1 + hd_r1_c1 + hd_r2_c1), pt2) + hd_r2_c2;

        // path 4: A -> R2 -> R1 -> C1 -> C2
        Double path4 = path_d_r2_r1 + hd_r1_c1 + hd_c1_c2;

        // path 5: A -> R2 -> R1 -> C2 -> C1
        Double path5 = path_d_r2_r1 + hd_r1_c2 + hd_c1_c2;

        // path 6: A -> R2 -> C2 -> R1 -> C1
        Double path6 = Math.max((path_d_r2 + hd_r2_c2 + hd_r1_c2), pt1) + hd_r1_c1;

        System.out.println("path1 = " + path1);
        System.out.println("path2 = " + path2);
        System.out.println("path3 = " + path3);
        System.out.println("path4 = " + path4);
        System.out.println("path5 = " + path5);
        System.out.println("path6 = " + path6);


    }
}
