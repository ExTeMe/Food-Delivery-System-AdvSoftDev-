package controller;

// import controller.Validator; // Validator implementation in the future
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.*;
import model.*;

@WebServlet(name = "controller/ModifyCategoryServlet", value = "/modify-category-details")
public class ModifyCategoryServlet extends HttpServlet {

    private static int id = 2;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String catName = request.getParameter("name");
        String catDesc = request.getParameter("description");
        String catID = request.getParameter("id");

        RCategories rCategories = (RCategories) session.getAttribute("categories");

        String url;

        if (catID.equals("")) {
            rCategories.addCategory(new RCategory(++id, catName, catDesc));
            url = "modifyCat.jsp";
            session.setAttribute("ModifySuccess", "Successfully Added Category!");
        }
        else {
            for (RCategory category : rCategories.getCategories()) {
                if (category.getRCat_ID() == Integer.parseInt(catID)) {
                    category.setrCatName(catName);
                    category.setrCatDescription(catDesc);
                }
            }
            url = "modifyCat.jsp?cat=" + catID;
            session.setAttribute("ModifySuccess", "Successfully Edited Category!");
        }

        request.getRequestDispatcher(url).include(request, response);
    }

}
