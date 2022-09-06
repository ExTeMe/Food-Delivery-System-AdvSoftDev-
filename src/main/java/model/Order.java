package model;

public class Order {
    private int orderID;
    private int customerID;
    private String orderType;
    private int couponID;
    private String status;
    private int foodRating;
    private String foodInstructions;
    private String foodFeedback;

    public Order(int orderID, int customerID, String orderType, int couponID, String status, int foodRating,
            String foodInstructions, String foodFeedback) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderType = orderType;
        this.couponID = couponID;
        this.status = status;
        this.foodRating = foodRating;
        this.foodInstructions = foodInstructions;
        this.foodFeedback = foodFeedback;
    }

    // for testing
    public Order(int orderID, int customerID) {
        this.orderID = orderID;
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getCouponID() {
        return couponID;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(int foodRating) {
        this.foodRating = foodRating;
    }

    public String getFoodInstructions() {
        return foodInstructions;
    }

    public void setFoodInstructions(String foodInstructions) {
        this.foodInstructions = foodInstructions;
    }

    public String getFoodFeedback() {
        return foodFeedback;
    }

    public void setFoodFeedback(String foodFeedback) {
        this.foodFeedback = foodFeedback;
    }

}
