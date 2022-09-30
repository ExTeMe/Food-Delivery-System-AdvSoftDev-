package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import model.Customer;
import model.Delivery;
import model.DeliveryDriver;
import model.Order;
import model.Staff;
import model.User;


public class DBManager {
    
    private Statement st;
    private PreparedStatement ps;
    private String fetch;
    private Connection conn;
   
    public DBManager(Connection conn) throws SQLException {       
        st = conn.createStatement();  
//        ps = conn.prepareStatement(); 
        this.conn = conn;
    }
/* 
    public void testAdder(String firstName, String lastName) {
        fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "')";
        System.out.println(fetch);
        ps.executeUpdate();

    }
*/
    public void addUser(String firstName, String lastName, String password, String email, int phoneNumber, String dob, int streetNumber, String streetName, int postcode, String state, String suburb, String country, boolean activated) throws SQLException {
       // fetch = "INSERT INTO db.user " + "VALUES (" + 000123 + ",'" + firstName + "', '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', " + 1 + ")";
       // System.out.println("made it to addUser in dbmanager");
        
        fetch = "insert into db.user(first_name, last_name, password, email, phoneNo, dob, street_number, street_name, postcode, state, suburb, country, activated) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setInt(5, phoneNumber);
        ps.setString(6, dob);
        ps.setInt(7, streetNumber);
        ps.setString(8, streetName);
        ps.setInt(9, postcode);
        ps.setString(10, state);
        ps.setString(11, suburb);
        ps.setString(12, country);
        ps.setBoolean(13, activated);
        System.out.println(fetch);
        ps.executeUpdate();

    }

    public void addStaff(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, int privilege, String position) throws SQLException {
        String fetch = "INSERT INTO db.tables.Staff " + "VALUES (" + firstName + ", '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '" + privilege + "', '" + position + "')";
        
        System.out.println(fetch);
    }

    public Customer findCustomer(String Cemail, String Cpassword) throws SQLException {

        String fetch = "select * from db.user where email='" + Cemail + "' and Password= '" + Cpassword + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);

        if (email.equals(Cemail) && password.equals(Cpassword)) {
            System.out.println("Customer Found!!");
            int userID = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            password = rs.getString(4);
            email = rs.getString(5);
            int phone = rs.getInt(6);
            
            String dateOfBirth = rs.getString(7);
            LocalDate localDate = LocalDate.parse(dateOfBirth);
            int streetNumber = rs.getInt(8);
            String streetName = rs.getString(9);
            int postcode = rs.getInt(10);
            String state = rs.getString(11);
            String suburb = rs.getString(12);
            String country = rs.getString(13);
            boolean activated = rs.getBoolean(14);
            
            fetch = "select * from db.customer where user_ID=" + userID;
            rs = st.executeQuery(fetch);
            rs.next();

            int customerID = rs.getInt(1);
            String cardNumber = rs.getString(3);
            String cardExpiration = rs.getString(4);
            LocalDate localDate2 = LocalDate.parse(cardExpiration);
            int cardPin = rs.getInt(5);
            String cardName = rs.getString(6);
            return new Customer(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, customerID, cardNumber, localDate2, cardPin, cardName);
            
        }

        else {
           System.out.println("DBMANAGER USER NOT FOUND FROM FINDUSER METHOD");
            return null; 
        }
        
        
    }

    public void addPaymentDetails(int userID, String cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        // find the user and get their user id 

        fetch = "insert into db.customer(user_id, card_number, card_expiration, card_pin, card_name) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setString(2, cardNumber);
        ps.setString(3, cardExpiration);
        ps.setInt(4, cardPin);
        ps.setString(5, cardName);

        ps.executeUpdate();

    }

    public User findUser(String email, String password) throws SQLException{
        String fetch = "select * from db.user where email='" + email + "' and Password= '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        while (rs.next()) {
            String custEmail = rs.getString(5);
            String custPassword = rs.getString(4);
            if (custEmail.equals(email) && custPassword.equals(password)) {
                System.out.println("Customer Found!!");
                int userID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                custPassword = rs.getString(4);
                custEmail = rs.getString(5);
                int phone = rs.getInt(6);
                
                String dateOfBirth = rs.getString(7);
                LocalDate localDate = LocalDate.parse(dateOfBirth);
                
                int streetNumber = rs.getInt(8);
                String streetName = rs.getString(9);
                int postcode = rs.getInt(10);
                String state = rs.getString(11);
                String suburb = rs.getString(12);
                String country = rs.getString(13);
                boolean activated = rs.getBoolean(14);
                
                return new User(userID, firstName, lastName, custPassword, custEmail, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated);
                
            }
        }

        return null;
    }

    public void addStaffDetails(String email, int restaurantID, int privilege, String position) throws SQLException{
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.staff(userID, restaurant_ID, privilege, position) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setInt(2, restaurantID);
        ps.setInt(3, privilege);
        ps.setString(4, position);

        ps.executeUpdate();
    }

    // Order
    public Order getOrder(int orderID) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM DB.ORDER WHERE ORDER_ID = " + orderID);
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_TYPE"),
                        rs.getInt("COUPON_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("FOOD_RATING"),
                        rs.getString("FOOD_INSTRUCTIONS"),
                        rs.getString("FOOD_FEEDBACK"));
                return order;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    public void addPaymentDetails(String email, String cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        // find the user and get their user id 
        System.out.println(email + " is the email being passed into the addPaymentDetails method in manager");
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.customer(user_id, card_number, card_expiration, card_pin, card_name) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setString(2, cardNumber);
        ps.setString(3, cardExpiration);
        ps.setInt(4, cardPin);
        ps.setString(5, cardName);

        ps.executeUpdate();

    }
/* 
    public User findUser(String email, String password) throws SQLException{
        String fetch = "select * from db.user where email='" + email + "' and Password= '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        while (rs.next()) {
            String custEmail = rs.getString(5);
            String custPassword = rs.getString(4);
            if (custEmail.equals(email) && custPassword.equals(password)) {
                System.out.println("Customer Found!!");
                int userID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                custPassword = rs.getString(4);
                custEmail = rs.getString(5);
                int phone = rs.getInt(6);
                
                String dateOfBirth = rs.getString(7);
                LocalDate localDate = LocalDate.parse(dateOfBirth);
                
                int streetNumber = rs.getInt(8);
                String streetName = rs.getString(9);
                int postcode = rs.getInt(10);
                String state = rs.getString(11);
                String suburb = rs.getString(12);
                String country = rs.getString(13);
                boolean activated = rs.getBoolean(13);
                
                return new User(userID, firstName, lastName, custPassword, custEmail, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated);
                
            }
        }

        return null;
    }
/* 
    public void addStaffDetails(String email, int restaurantID, int privilege, String position) throws SQLException{
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.staff(userID, restaurant_ID, privilege, position) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setInt(2, restaurantID);
        ps.setInt(3, privilege);
        ps.setString(4, position);

        ps.executeUpdate();
    }
*/
    public void updateCustomer(int userID, String firstName, String lastName, String password, String email, String phone, String dateOfBirth, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, int customerID, String cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        
        fetch = "UPDATE db.user SET first_name = ?, last_name = ?, password = ?, email = ?, phoneNo = ?, dob = ?, street_number = ?, street_name = ?, postcode = ?, state = ?, suburb = ?, country = ?, activated = ? WHERE userID = ?";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, dateOfBirth);
        ps.setString(7, streetNumber);
        ps.setString(8, streetName);
        ps.setString(9, postcode);
        ps.setString(10, state);
        ps.setString(11, suburb);
        ps.setString(12, country);
        ps.setInt(13, 1);
        ps.setInt(14, userID);

        System.out.println(fetch);
        ps.executeUpdate();
        System.out.println(ps);
        /*
         * UPDATE Customers
         * SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
         * WHERE CustomerID = 1;
         */
    }

    public Customer findCustomer(int CuserID) throws SQLException {
        String fetch = "select * from db.user where UserID=" + CuserID;
        ResultSet rs = st.executeQuery(fetch);
   
        rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);
        int userID = rs.getInt(1);
        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        password = rs.getString(4);
        email = rs.getString(5);
        int phone = rs.getInt(6);
        
        String dateOfBirth = rs.getString(7);
        LocalDate localDate = LocalDate.parse(dateOfBirth);
        int streetNumber = rs.getInt(8);
        String streetName = rs.getString(9);
        int postcode = rs.getInt(10);
        String state = rs.getString(11);
        String suburb = rs.getString(12);
        String country = rs.getString(13);
        boolean activated = rs.getBoolean(14);
        
        fetch = "select * from db.customer where user_ID=" + userID;
        rs = st.executeQuery(fetch);
        rs.next();
        int customerID = rs.getInt(1);
        String cardNumber = rs.getString(3);
        String cardExpiration = rs.getString(4);
        LocalDate localDate2 = LocalDate.parse(cardExpiration);
        int cardPin = rs.getInt(5);
        String cardName = rs.getString(6);
        return new Customer(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, customerID, cardNumber, localDate2, cardPin, cardName);

    } 

}
