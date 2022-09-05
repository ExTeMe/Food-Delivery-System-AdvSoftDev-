package model;

public class Order {
    private int Order_ID;
    private int Customer_ID;
    private String Order_Type;
    private int Coupon_ID;
    private String Status;
    private int Food_Rating;
    private String Food_Instructions;
    private String Food_Feedback;

    public Order(
            int Order_ID,
            int Customer_ID,
            String Order_Type,
            int Coupon_ID,
            String Status,
            int Food_Rating,
            String Food_Instructions,
            String Food_Feedback) {
        this.Order_ID = Order_ID;
        this.Customer_ID = Customer_ID;
        this.Order_Type = Order_Type;
        this.Coupon_ID = Coupon_ID;
        this.Status = Status;
        this.Food_Rating = Food_Rating;
        this.Food_Instructions = Food_Instructions;
        this.Food_Feedback = Food_Feedback;
    }

    public Order(int Order_ID, int Customer_ID) {
        this.Order_ID = Order_ID;
        this.Customer_ID = Customer_ID;
    }

    // Get
    public int getOrderID() {
        return Order_ID;
    }

    public int getCustomerID() {
        return Customer_ID;
    }

    public String getOrderType() {
        return Order_Type;
    }

    public int getCouponID() {
        return Coupon_ID;
    }

    public String getStatus() {
        return Status;
    }

    public int getFoodRating() {
        return Food_Rating;
    }

    public String getFoodInstructions() {
        return Food_Instructions;
    }

    public String getFoodFeedback() {
        return Food_Feedback;
    }

    // Set
    public void setOrderID(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    public void setCustomerID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public void setOrderType(String Order_Type) {
        this.Order_Type = Order_Type;
    }

    public void setCouponID(int Coupon_ID) {
        this.Coupon_ID = Coupon_ID;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setFoodRating(int Food_Rating) {
        this.Food_Rating = Food_Rating;
    }

    public void setFoodInstructions(String Food_Instructions) {
        this.Food_Instructions = Food_Instructions;
    }

    public void setFoodFeedback(String Food_Feedback) {
        this.Food_Feedback = Food_Feedback;
    }
}
