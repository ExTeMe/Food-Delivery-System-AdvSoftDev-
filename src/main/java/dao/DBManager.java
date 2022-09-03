package dao;

import java.sql.*;

import model.Customer;
import model.Staff;


public class DBManager {
    
    private Statement st;
   
    public DBManager(Connection conn) throws SQLException {       
        st = conn.createStatement();   
    }

    public void addUser(String firstName, String lastName, String email, String phone, String password, String dateOfBirth, String address, String cardNumber, String cardExpiration, int cardPin, String cardName) {
        
    }

    public Customer findCustomer(String email, String password) {
        return null;
    }

    public Staff findStaff(String email, String password) {
        return null;
    }

}
