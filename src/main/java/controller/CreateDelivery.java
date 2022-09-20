package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Order;
import model.Delivery;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/CreateDelivery", value = "/create-delivery")
public class CreateDelivery extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        test(request, response);

        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String orderType = request.getParameter("order-type");

        Order order = (Order) session.getAttribute("order");
        order.setOrderType(orderType);

        if (orderType.equals("delivery")) {
            String street = request.getParameter("street");
            String suburb = request.getParameter("suburb");
            String state = request.getParameter("state");
            String postal = request.getParameter("postal");
            String instructions = request.getParameter("instructions");
            double fee = 55.55;

            Delivery delivery = new Delivery(order.getOrderID(), street, suburb, state, postal, fee, instructions);

            if (manager.getDelivery(order) != null) {
                manager.updateDelivery(delivery);
            } else {
                manager.createDelivery(delivery);
            }

        }

        request.getRequestDispatcher("deliveryStatus.jsp").include(request, response);
    }

    private void test(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);

            Order order = new Order(101010, 202020);
            session.setAttribute("order", order);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}
