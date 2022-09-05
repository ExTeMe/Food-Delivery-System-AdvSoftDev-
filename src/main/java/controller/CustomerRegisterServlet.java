package controller;

import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DBManager;
import model.Customer;

@WebServlet(name = "CustomerRegisterServlet", value = "/CustomerRegisterServlet")

public class CustomerRegisterServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Customer customer = null;

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
        
        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            System.out.println("Trying to add Customer");
            manager.testAdder(firstName, lastName);
            manager.addCustomer(firstName, lastName, password, email, phone, dob, streetNumber, streetName, postcode, state, suburb, country, true, cardNumber, cardExpiration, cardPin, cardName);
            System.out.println("Customer entered Successful");
        }
        catch (NullPointerException ex) {
            System.out.println("nullptr exception");
        }
        catch (SQLException ex) {
            System.out.println("sql exception");
        }
    }

}