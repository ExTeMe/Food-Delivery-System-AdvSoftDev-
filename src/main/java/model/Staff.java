package model;

import java.time.LocalDate;

public class Staff extends User {
    private int StaffID;
    private int restaurantID;
    private int privilege;
    private String positionl;

    public Staff(int userID, String fname, String lname, String pass, String email, int phoneNo, LocalDate dob, int streetNo, String streetName, int postcode, String state, String suburb, String country, Boolean activated) {
        super(userID, fname, lname, pass, email, phoneNo, dob, streetNo, streetName, postcode, state, suburb, country, activated);
    }

    public int getStaffID() {
        return StaffID;
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

    public String getPositionl() {
        return positionl;
    }

    public void setPositionl(String positionl) {
        this.positionl = positionl;
    }
}
