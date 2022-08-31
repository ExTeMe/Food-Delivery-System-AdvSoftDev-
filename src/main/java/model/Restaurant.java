package model;

public class Restaurant {

    private final int restaurantID;
    private RCategory category;
    private String restaurantName;
    private int streetNum;
    private String streetName;
    private int postcode;
    private String state;
    private String suburb;
    private String country;
    private Boolean activate;
    private final long abn;
    private String accountName;
    private int bsb;
    private int accountNum;

    public Restaurant(int restaurantID, RCategory category, String restaurantName, int streetNum, String streetName,
                      int postcode, String state, String suburb, String country, Boolean activate, long abn, String accountName,
                      int bsb, int accountNum) {
        this.restaurantID = restaurantID;
        this.category = category;
        this.restaurantName = restaurantName;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activate = activate;
        this.abn = abn;
        this.accountName = accountName;
        this.bsb = bsb;
        this.accountNum = accountNum;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public RCategory getCategory() {
        return category;
    }

    // Rather than calling UpdateServlet to update the database, and recreate the a new object with the updated fields and
    // then passing it to the view through Session for display.
    // We can immediately update (set) the current Object's attributes and at the same time, update the database
    // The main menu will contain the restaurants and their categories, when we want to update a restaurant, we will retrieve
    // the RCategory from the dropdown list
    // (There will be an ArrayList in the jsp to store the list of categories, simply retrieve from there)
    public void setCategory(RCategory category) {
        this.category = category;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public long getAbn() {
        return abn;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBsb() {
        return bsb;
    }

    public void setBsb(int bsb) {
        this.bsb = bsb;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

}