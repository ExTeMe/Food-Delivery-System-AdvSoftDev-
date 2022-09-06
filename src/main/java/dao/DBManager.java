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

    public void addUser(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated) throws SQLException {
        String fetch = "INSERT INTO db.user " + "VALUES (" + 32456 + ",'" + firstName + "', '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', " + 1 + ")";
        System.out.println(fetch);
    }

    public void addStaff(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, int privilege, String position) throws SQLException {
        String fetch = "INSERT INTO db.tables.Staff " + "VALUES (" + firstName + ", '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '" + privilege + "', '" + position + "')";
        System.out.println(fetch);
    }

    public Boolean findCustomer(String email, String password) throws SQLException {

        String fetch = "select * from db.user where email='" + email + "' and Password= '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        while (rs.next()) {
            String custEmail = rs.getString(5);
            String custPassword = rs.getString(4);
            if (custEmail.equals(email) && custPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Staff findStaff(String email, String password) {
        return null;
    }

}
