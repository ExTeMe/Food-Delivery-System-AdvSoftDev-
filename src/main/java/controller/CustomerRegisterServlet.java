package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DBManager;
import model.Customer;

public class CustomerRegisterServlet extends HttpServlet{

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Customer customer = null;

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String dob = request.getParameter("dob"); 

        String cardNumber = request.getParameter("cardNumber");
        String cardExpiration = request.getParameter("cardExpiration");
        // change cardPin to int
        int temp = 0;
        String cardPin = request.getParameter("cardPin");
        String cardName = request.getParameter("cardName");

        System.out.println(email + cardNumber);
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        try {
            System.out.println("Trying to generate ID");
            manager.addUser(firstName, lastName, email, phone, password, dob, address, cardNumber, cardExpiration, temp, cardName);
            System.out.println("Customer entered Successful");
        }
        catch (NullPointerException ex) {
//               System.out.println(ex.getMessage() == null ? "Insert customer failed; NULLEXCEPTION" : "NULLEXCEPTION UNKNOWN REASON");
//               request.getRequestDispatcher("customerRegister.jsp").include(request, response);
        }
        
    }

}