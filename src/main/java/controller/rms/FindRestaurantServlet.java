package controller.rms;

import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/FindRestaurantServlet", value = "/find-res")
public class FindRestaurantServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        String resName = request.getParameter("name");
        String resID = request.getParameter("id");

        ArrayList<Restaurant> restaurants;

        try {
            if (resName.equals("")) {
                restaurants = manager.findRestaurant(resID);
                session.setAttribute("restaurants", restaurants);
                request.getRequestDispatcher("modifyRes.jsp").include(request, response);
            }
            else {
                restaurants = manager.findRestaurant(resName);
                session.setAttribute("restaurants", restaurants);
                request.getRequestDispatcher("manageRes.jsp").include(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(GetAllCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("manageRes.jsp").include(request, response);
        }

    }

}
