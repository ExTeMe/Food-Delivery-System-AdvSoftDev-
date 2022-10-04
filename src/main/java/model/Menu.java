package model;

public class Menu {
    private String name;
    private float price;
    private MenuCategory category;

    Menu(String name, float price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void EditMenu(String name, float price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public MenuCategory GetCategory() {
        return category;
    }

    public void SetCategory(MenuCategory category) {
        this.category = category;
    }

    public String GetName() {
        return name;
    }

    public float GetPrice() {
        return price;
    }

    public String GetString() {
        return price + " " + price;
    }
}