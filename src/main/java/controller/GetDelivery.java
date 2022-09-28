package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.*;
import model.Order;
import model.Delivery;
import java.sql.Connection;

@WebServlet(name = "controller/GetDelivery", value = "/get-delivery")
public class GetDelivery extends HttpServlet {
    protected void processResquest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            createManager(request, response);
        }
        DBManager manager = (DBManager) session.getAttribute("manager");

        int orderID = Integer.parseInt(request.getParameter("orderID"));
        boolean API = Boolean.parseBoolean(request.getParameter("API"));

        if (API) {
            Order order = manager.getOrder(orderID);
            Delivery delivery = manager.getDelivery(orderID);
            Data data = new Data(order, delivery);

            String json = new Gson().toJson(data);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } else {
            request.setAttribute("orderID", orderID);
            request.getRequestDispatcher("deliveryStatus.jsp").include(request, response);
        }
    }

    private class Data {
        private String status;
        private String type;
        private String street;
        private String suburb;
        private String state;
        private String postal;

        private Data(Order order, Delivery delivery) {
            this.status = order.getStatus();
            this.type = order.getOrderType();
            this.street = delivery.getDeliveryStreet();
            this.suburb = delivery.getDeliverySuburb();
            this.state = delivery.getDeliveryState();
            this.postal = delivery.getDeliveryPostal();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processResquest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processResquest(request, response);
    }

    private void createManager(HttpServletRequest request, HttpServletResponse response) {
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
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}