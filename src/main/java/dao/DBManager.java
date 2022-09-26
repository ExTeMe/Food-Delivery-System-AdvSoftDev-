package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.*;

public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public void testAdder(String firstName, String lastName) {
        String fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "')";
        System.out.println(fetch);
    }

    public void addCustomer(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, String cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        String fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '" + cardNumber + "', '" + cardExpiration + "', '" + cardPin + "', '" + cardName + "')";
        System.out.println(fetch);
    }

    public void addStaff(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, int privilege, String position) throws SQLException {
        String fetch = "INSERT INTO db.tables.Staff " + "VALUES (" + firstName + ", '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '" + privilege + "', '" + position + "')";
        System.out.println(fetch);
    }

    public Customer findCustomer(String email, String password) {
        return null;
    }

    public Staff findStaff(String email, String password) {
        return null;
    }

    // Delivery
    public void createDelivery(Delivery delivery) {
        try {
            st.executeUpdate(
                    "INSERT INTO DELIVERY(ORDER_ID, DRIVER_ID, DELIVERY_STREET, DELIVERY_SUBURB, DELIVERY_STATE, DELIVERY_POSTAL, DELIVERY_FEE, DRIVER_INSTRUCTIONS) VALUES ("
                            + delivery.getOrderID() + ", NULL, '"
                            + delivery.getDeliveryStreet() + "', '" + delivery.getDeliverySuburb() + "', '"
                            + delivery.getDeliveryState() + "', '" + delivery.getDeliveryPostal() + "', "
                            + ((double) Math.round(delivery.getDeliveryFee() * 100) / 100) + ", "
                            + (delivery.getDriverInstructions() == null ? "NULL "
                                    : ("'" + delivery.getDriverInstructions() + "' "))
                            + ");");
        } catch (

        Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
    }

    public void updateDelivery(Delivery delivery) {
        try {
            st.executeUpdate("UPDATE DELIVERY SET " +
                    "ORDER_ID = " + delivery.getOrderID() +
                    ", DRIVER_ID = " + delivery.getDriverID() +
                    ", DELIVERY_STREET = '" + delivery.getDeliveryStreet() +
                    "', DELIVERY_SUBURB = '" + delivery.getDeliverySuburb() +
                    "', DELIVERY_STATE = '" + delivery.getDeliveryState() +
                    "', DELIVERY_POSTAL = '" + delivery.getDeliveryPostal() +
                    "', DELIVERY_FEE = " + ((double) Math.round(delivery.getDeliveryFee() * 100) / 100) +
                    ", DRIVER_RATING = " + delivery.getDriverRating() +
                    ", DRIVER_INSTRUCTIONS = '" + delivery.getDriverInstructions() +
                    "', DRIVER_TIP = " + delivery.getDriverTip() +
                    " WHERE DELIVERY_ID = " + delivery.getDeliveryID());
        } catch (

        Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
    }

    public Delivery getDelivery(Order order) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM DELIVERY WHERE ORDER_ID = " + order.getOrderID());
            while (rs.next()) {
                Delivery delivery = new Delivery(
                        rs.getInt("DELIVERY_ID"),
                        rs.getInt("ORDER_ID"),
                        rs.getInt("DRIVER_ID"),
                        rs.getString("DELIVERY_STREET"),
                        rs.getString("DELIVERY_SUBURB"),
                        rs.getString("DELIVERY_STATE"),
                        rs.getString("DELIVERY_POSTAL"),
                        rs.getFloat("DELIVERY_FEE"),
                        rs.getInt("DRIVER_RATING"),
                        rs.getString("DRIVER_INSTRUCTIONS"),
                        rs.getString("DRIVER_FEEDBACK"),
                        rs.getFloat("DRIVER_TIP"));
                return delivery;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // Driver
    public DeliveryDriver getDriver(User user) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM DRIVER WHERE USER_ID = " + user.getUserID());
            while (rs.next()) {
                DeliveryDriver driver = new DeliveryDriver(
                        rs.getInt("DRIVER_ID"),
                        rs.getInt("USER_ID"),
                        rs.getString("NUMBER_PLATE"),
                        rs.getString("VEHICLE_DESCRIPTION"),
                        rs.getFloat("RATING"),
                        rs.getString("D_ACCOUNT_NAME"),
                        rs.getInt("D_BSB"),
                        rs.getInt("D_ACCOUNT_NUMBER"));
                return driver;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // AppStaff Login - Benz
    public AppStaff appStaffLogin(String email, String pass) throws SQLException, Exception {
        ResultSet rs = st.executeQuery("SELECT * FROM db.user U INNER JOIN db.appstaff A WHERE U.UserID = A.UserID AND " +
                "Email ='" + email + "' AND Password='" + pass + "'");

        if (rs.next()) {
            String userID = rs.getString("U.UserID");
            String asID = rs.getString("A.A_Staff_ID");
            return new AppStaff(Integer.parseInt(userID), Integer.parseInt(asID));
        } else {
            return null;
        }
    }
}
