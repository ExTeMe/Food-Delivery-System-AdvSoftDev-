package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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

        String path = "/get-delivery?orderID=" + order.getOrderID();

        Validator validator = new Validator();
        boolean passed = true;

        if (orderType.equals("delivery")) {
            String street = request.getParameter("street");
            String suburb = request.getParameter("suburb");
            String state = request.getParameter("state");
            if (!validator.validateState(state)) {
                path = "/createDelivery.jsp";
                session.setAttribute("stateErr", "Invalid State, use state code with capital letters");
                passed = false;
            }
            String postal = request.getParameter("postal");
            if (!validator.validatePostCode(postal)) {
                path = "/createDelivery.jsp";
                session.setAttribute("postalErr", "Invalid Postal Code");
                passed = false;
            }
            String instructions = request.getParameter("instructions");
            double fee = 55.55;

            if (passed) {
                session.removeAttribute("stateErr");
                session.removeAttribute("postalErr");
                Delivery delivery = new Delivery(order.getOrderID(), street, suburb, state, postal, fee, instructions);

                if (manager.getDelivery(order.getOrderID()) != null) {
                    manager.updateDelivery(delivery);
                } else {
                    manager.createDelivery(delivery);
                }
            }
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.forward(request, response);
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

            Order order = new Order(101010, 202020, "Delivering");
            session.setAttribute("order", order);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}
