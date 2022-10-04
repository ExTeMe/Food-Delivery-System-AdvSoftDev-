package model;

public class MenuItem {
    private int itemID;
    private int restaurantID;
    private String itemType;
    private int serving;
    private double price;
    private int calories;
    private String image;
    private String description;
    private String ingredients;
    private String allergy;
    private int stock;

    public MenuItem(int itemID, int restaurantID, String itemType, int serving, double price, int calories,
            String image, String description, String ingredients, String allergy, int stock) {
        this.itemID = itemID;
        this.restaurantID = restaurantID;
        this.itemType = itemType;
        this.serving = serving;
        this.price = price;
        this.calories = calories;
        this.image = image;
        this.description = description;
        this.ingredients = ingredients;
        this.allergy = allergy;
        this.stock = stock;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
