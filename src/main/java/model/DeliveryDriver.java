package model;

public class DeliveryDriver {
    private int driverID;
    private int userID;
    private String numberPlate;
    private String vehicleDescription;
    private double rating;
    private String dAccountName;
    private int dBSB;
    private int dAccountNumber;

    public DeliveryDriver(int driverID, int userID, String numberPlate, String vehicleDescription, double rating,
            String dAccountName, int dBSB, int dAccountNumber) {
        this.driverID = driverID;
        this.userID = userID;
        this.numberPlate = numberPlate;
        this.vehicleDescription = vehicleDescription;
        this.rating = rating;
        this.dAccountName = dAccountName;
        this.dBSB = dBSB;
        this.dAccountNumber = dAccountNumber;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getdAccountName() {
        return dAccountName;
    }

    public void setdAccountName(String dAccountName) {
        this.dAccountName = dAccountName;
    }

    public int getdBSB() {
        return dBSB;
    }

    public void setdBSB(int dBSB) {
        this.dBSB = dBSB;
    }

    public int getdAccountNumber() {
        return dAccountNumber;
    }

    public void setdAccountNumber(int dAccountNumber) {
        this.dAccountNumber = dAccountNumber;
    }

}