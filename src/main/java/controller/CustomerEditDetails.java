package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DBConnector;
import dao.DBManager;
import model.Customer;

@WebServlet(name = "CustomerEditDetails", value = "/CustomerEditDetails")

public class CustomerEditDetails extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cardNumber = 0;
        int cardPin = 0;
        
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob"); 

        String streetNumber = request.getParameter("streetNumber");
        String streetName = request.getParameter("streetName");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        String country = request.getParameter("country");

        String cardNumberTemp = request.getParameter("cardNumber");
        String cardExpirationTemp = request.getParameter("cardExpiration");
        String cardPinTemp = request.getParameter("cardPin");
        String cardName = request.getParameter("cardName");

        LocalDate cardExpiration = LocalDate.parse(cardExpirationTemp);

        try{
            cardNumber = Integer.parseInt(cardNumberTemp);
            cardPin = Integer.parseInt(cardPinTemp);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        
        DBConnector db;
        DBManager manager;
        Connection conn;
    
        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
    
            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
    
        } catch (Exception e) {
            System.out.println("MANAGER FAILED SOMEHOW");
            System.out.println("Exception is: " + e);
            e.printStackTrace();
        }

        manager = (DBManager) session.getAttribute("manager");

        try {
            System.out.println("Trying to add Customer");
            Customer customer = (Customer) session.getAttribute("Customer");
            manager.updateCustomer(customer.getUserID(), firstName, lastName, password, email, phone, dob, streetNumber, streetName, postcode, state, suburb, country, true, customer.getCustomerID(), cardNumber, cardExpirationTemp, cardPin, cardName);
            customer = manager.findCustomer(customer.getUserID());
            session.setAttribute("Customer", customer);
            request.getRequestDispatcher("main.jsp").include(request, response);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
            System.out.println("nullptr exception");
        }
        catch (SQLException ex) {
            System.out.println("sql exception");
            ex.printStackTrace();
        }
    }

}