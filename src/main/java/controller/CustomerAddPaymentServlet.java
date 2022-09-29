package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

@WebServlet(name = "CustomerAddPaymentServlet", value = "/CustomerAddPaymentServlet")

public class CustomerAddPaymentServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String cardNumber = request.getParameter("cardNumber");
        String cardExpiration = request.getParameter("cardExpiration");
        // change cardPin to int
        int cardPin = 0;
        String cardPinTemp = request.getParameter("cardPin");
        String cardName = request.getParameter("cardName");

        try{
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
            System.out.println("Trying to add Payment details ");
            manager.addPaymentDetails(customer.getEmail(), cardNumber, cardExpiration, cardPin, cardName);
        //    request.getRequestDispatcher("customerAddPayment.jsp").include(request, response);
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