package model;

public class Order {
    private int Order_ID;
    private int Customer_ID;
    private int Driver_ID;
    private String Order_Type;
    private String Delivery_Street;
    private String Delivery_Suburb;
    private String Delivery_State;
    private String Delivery_Postal;
    private float Delivery_Fee;
    private int Coupon_ID;
    private String Status;
    private int Food_Rating;
    private int Driver_Rating;
    private String Food_Instructions;
    private String Driver_Instructions;
    private String Food_Feedback;
    private String Driver_Feedback;
    private float Driver_Tip;

    Order(int Order_ID,
            int Customer_ID,
            int Driver_ID,
            String Order_Type,
            String Delivery_Street,
            String Delivery_Suburb,
            String Delivery_State,
            String Delivery_Postal,
            float Delivery_Fee,
            int Coupon_ID,
            String Status,
            int Food_Rating,
            int Driver_Rating,
            String Food_Instructions,
            String Driver_Instructions,
            String Food_Feedback,
            String Driver_Feedback,
            float Driver_Tip) {
        this.Order_ID = Order_ID;
        this.Customer_ID = Customer_ID;
        this.Driver_ID = Driver_ID;
        this.Order_Type = Order_Type;
        this.Delivery_Street = Delivery_Street;
        this.Delivery_Suburb = Delivery_Suburb;
        this.Delivery_State = Delivery_State;
        this.Delivery_Postal = Delivery_Postal;
        this.Delivery_Fee = Delivery_Fee;
        this.Coupon_ID = Coupon_ID;
        this.Status = Status;
        this.Food_Rating = Food_Rating;
        this.Driver_Rating = Driver_Rating;
        this.Food_Instructions = Food_Instructions;
        this.Driver_Instructions = Driver_Instructions;
        this.Food_Feedback = Food_Feedback;
        this.Driver_Feedback = Driver_Feedback;
        this.Driver_Tip = Driver_Tip;
    }

    // Get
    public int getOrderID() {
        return Order_ID;
    }

    public int getCustomerID() {
        return Customer_ID;
    }

    public int getDriverID() {
        return Driver_ID;
    }

    public String getOrderType() {
        return Order_Type;
    }

    public String getDeliveryStreet() {
        return Delivery_Street;
    }

    public String getDeliverySuburb() {
        return Delivery_Suburb;
    }

    public String getDeliveryState() {
        return Delivery_State;
    }

    public String getDeliveryPostal() {
        return Delivery_Postal;
    }

    public float getDeliveryFee() {
        return Delivery_Fee;
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

    public int getDriverRating() {
        return Driver_Rating;
    }

    public String getFoodInstructions() {
        return Food_Instructions;
    }

    public String getDriverInstructions() {
        return Driver_Instructions;
    }

    public String getFoodFeedback() {
        return Food_Feedback;
    }

    public String getDriverFeedback() {
        return Driver_Feedback;
    }

    public float getDriverTip() {
        return Driver_Tip;
    }

    // Set
    public void setOrderID(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    public void setCustomerID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public void setDriverID(int Driver_ID) {
        this.Driver_ID = Driver_ID;
    }

    public void setOrderType(String Order_Type) {
        this.Order_Type = Order_Type;
    }

    public void setDeliveryStreet(String Delivery_Street) {
        this.Delivery_Street = Delivery_Street;
    }

    public void setDeliverySuburb(String Delivery_Suburb) {
        this.Delivery_Suburb = Delivery_Suburb;
    }

    public void setDeliveryState(String Delivery_State) {
        this.Delivery_State = Delivery_State;
    }

    public void setDeliveryPostal(String Delivery_Postal) {
        this.Delivery_Postal = Delivery_Postal;
    }

    public void setDeliveryFee(float Delivery_Fee) {
        this.Delivery_Fee = Delivery_Fee;
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

    public void setDriverRating(int Driver_Rating) {
        this.Driver_Rating = Driver_Rating;
    }

    public void setFoodInstructions(String Food_Instructions) {
        this.Food_Instructions = Food_Instructions;
    }

    public void setDriverInstructions(String Driver_Instructions) {
        this.Driver_Instructions = Driver_Instructions;
    }

    public void setFoodFeedback(String Food_Feedback) {
        this.Food_Feedback = Food_Feedback;
    }

    public void setDriverFeedback(String Driver_Feedback) {
        this.Driver_Feedback = Driver_Feedback;
    }

    public void setDriverTip(float Driver_Tip) {
        this.Driver_Tip = Driver_Tip;
    }
}
