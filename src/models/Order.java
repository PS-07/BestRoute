package models;

public class Order {
    //customer
    //restaurant
    private Customer customer;
    private Restaurant restaurant;
    private String orderId;
    private DeliveryAgent deliveryAgent;
    private OrderState orderState;

    public Order(final Customer customer, final Restaurant restaurant, final String orderId, final DeliveryAgent deliveryAgent, final OrderState orderState) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderId = orderId;
        this.deliveryAgent = deliveryAgent;
        this.orderState = orderState;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public void setDeliveryAgent(final DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(final OrderState orderState) {
        this.orderState = orderState;
    }
}
