package model;

// THIS IS A TEMPORARY CLASS TO ACT AS A DB
// it will store the list of RCategory objects in an ArrayList

import java.util.ArrayList;
import model.RCategory;

public class RCategories {

    private ArrayList<RCategory> categories;

    public RCategories() {
        this.categories = new ArrayList<>();
        categories.add(new RCategory(1, "FastFood", "fast foods"));
        categories.add(new RCategory(2, "SlowFood", "slow foods"));
        // Hard-coded categories, just temporary for demonstration purposes
    }

    public ArrayList<RCategory> getCategories() {
        return categories;
    }
}
