package model;

import java.time.LocalDate;

public class Staff extends User {
    private int staffID;
    private int restaurantID;
    private int privilege;
    private String position;

    public Staff(int userID, String fname, String lname, String pass, String email, int phoneNo, LocalDate dob, int streetNo, String streetName, int postcode, String state, String suburb, String country, Boolean activated, int staffID, int restaurantID, int privilege, String position) {
        super(userID, fname, lname, pass, email, phoneNo, dob, streetNo, streetName, postcode, state, suburb, country, activated);
        this.staffID = staffID;
        this.restaurantID = restaurantID;
        this.privilege = privilege;
        this.position = position;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
