package controller.rms;

import controller.Validator;
import dao.ResDBManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller/rms/FindCategoryServlet", value = "/find-cat")
public class FindCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");

        String catName = request.getParameter("name");

        try {
            ArrayList<RCategory> rcategories = manager.findRCategories(catName);
            // Update rcategories to update the list view
            session.setAttribute("rcategories", rcategories);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        } catch (Exception e) {
            Logger.getLogger(GetAllCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher("manageCat.jsp").include(request, response);
        }
    }

}
