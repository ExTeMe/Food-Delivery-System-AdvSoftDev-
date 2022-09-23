package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.RCategory;

import java.io.IOException;

@WebServlet(name = "DeleteCatServlet", value = "/delete-cat")
public class DeleteCatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        int cat = Integer.parseInt(request.getParameter("cat"));
//
//        RCategories categories = (RCategories) session.getAttribute("categories");
//
//        for (RCategory category : categories.getCategories()) {
//            if (category.getRCat_ID() == cat) {
//                categories.deleteCategory(category);
//                break;
//            }
//        }

        request.getRequestDispatcher("manageCat.jsp").include(request, response);

    }

}
