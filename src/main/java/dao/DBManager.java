package dao;

import java.sql.*;

import model.Customer;
import model.Staff;


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

}
