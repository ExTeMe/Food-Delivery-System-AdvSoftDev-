package model;

import java.time.LocalDate;

public class Staff {
    private Integer staffID;
    private Integer restaurantID;
    private Integer privilege;
    private String position;
    private User user;

    public Staff(){}
    public Staff(Integer staffID, Integer restaurantID, Integer privilege, String position, User user){
        this.staffID = staffID;
        this.restaurantID = restaurantID;
        this.privilege = privilege;
        this.position = position;
        this.user = user;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
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
