package model;

import java.util.ArrayList;

public class AppStaff {

    private final int userID;
    private final int staffID;
    private int privilege;

    public AppStaff(int userID, int staffID, int privilege) {
        this.userID = userID;
        this.staffID = staffID;
        this.privilege = privilege;
    }

    public int getUserID() {
        return userID;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        // 0 - Manager Level Privilege
        // 1 - Admin Level Privilege
        // The validation will be moved to the Controller/Servlet in the future
        if (privilege == 0 || privilege == 1) this.privilege = privilege;
    }

    // To be implemented in the Servlet, will use a Session to store the chosen Category. Then simply retrieve the RCategory from the
    // request's parameters and perform the database and model update
    public void categorizeRestaurant(Restaurant restaurant, RCategory category) {
        if (category != null) restaurant.setCategory(category);
    }

    public void activateRestaurant(Restaurant restaurant, Boolean activate) {
        restaurant.setActivate(activate);
    }

    // temporary keeping track of list of requests in the AppStaff class
    ArrayList<Request> requestList = new ArrayList<>();
    public ArrayList<Request> retrieveRequests() {
        return requestList;
    }

    // Temporary add to requestList method (supposingly using a Database to store this)
    public void addRequests(Request request) {
        requestList.add(request);
    }

}
