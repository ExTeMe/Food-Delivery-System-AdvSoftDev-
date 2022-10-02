package model;

import java.time.LocalDate;

public class Staff {
    private int staffID;
    private int restaurantID;
    private int privilege;
    private String position;
    private User user;

    public Staff(){}
    public Staff(int staffID, int restaurantID, int privilege, String position, User user){
        this.staffID = staffID;
        this.restaurantID = restaurantID;
        this.privilege = privilege;
        this.position = position;
        this.user = user;
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

    public void setUser(User user) { this.user = user; }

    public User getUser() { return user; }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID=" + staffID +
                ", restaurantID=" + restaurantID +
                ", privilege=" + privilege +
                ", position='" + position + '\'' +
                ", user=" + user +
                '}';
    }
}
