package controller.rms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.*;
import model.*;

@WebServlet(name = "controller/rms/ModifyCategoryServlet", value = "/modify-category-details")
public class ModifyCategoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();
        ResDBManager manager = (ResDBManager) session.getAttribute("ResDBManager");
        clear(session); // Used to clear error messages.

        String catName = request.getParameter("name");
        String catDesc = request.getParameter("description");
        String catID = request.getParameter("id");
        boolean hasError = false;

        if (!validator.validateName(catName)) {
            session.setAttribute("catName_Error", "Incorrect Name Format - Name must start with a capital letter");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("modifyCat.jsp").include(request, response);
        }

        // If ID is empty means we want to create a new record
        // ID is a hidden field which will have an ID if we are modifying an existing record
        if (catID.equals("")) {
            try {
                manager.createCategory(new RCategory(catName, catDesc));
                session.setAttribute("ModifySuccess", "Successfully Added Category!");
                request.getRequestDispatcher("modifyCat.jsp").include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyCat.jsp").include(request, response);
            }
        }
        else {
            try {
                manager.updateCategory(new RCategory(Integer.parseInt(catID), catName, catDesc));
                session.setAttribute("ModifySuccess", "Successfully Edited Category!");
                request.getRequestDispatcher("find-cat?id=" + catID).include(request, response);
            } catch (Exception e) {
                Logger.getLogger(ModifyCategoryServlet.class.getName()).log(Level.SEVERE, null, e);
                request.getRequestDispatcher("modifyCat.jsp").include(request, response);
            }
        }
    }

    private void clear(HttpSession session) {
        session.setAttribute("catName_Error", "");
        session.setAttribute("ModifySuccess", "");
    }

}
