package controller.rms;

// import controller.Validator; // Validator implementation in the future
 import java.io.IOException;

 import jakarta.servlet.ServletException;
 import jakarta.servlet.annotation.*;
 import jakarta.servlet.http.HttpServlet;
 import jakarta.servlet.http.HttpServletRequest;
 import jakarta.servlet.http.HttpServletResponse;
 import jakarta.servlet.http.HttpSession;
 import model.*;

 // Update what
 @WebServlet(name = "controller/rms/UpdateCategoryServlet", value = "/update-category")
 public class SetResCatServlet extends HttpServlet {

      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {

           HttpSession session = request.getSession();

           Restaurant res = (Restaurant) session.getAttribute("restaurant");
           RCategories categories = (RCategories) session.getAttribute("categories");

           // resID is useless here, since we aren't even interacting with the DBs
           int resID = Integer.parseInt(request.getParameter("resID"));
           int cat = Integer.parseInt(request.getParameter("cat"));

           if (res.getRestaurantID() == resID) {
               for (RCategory category : categories.getCategories()) {
                   if (category.getRCat_ID() == cat) {
                       res.setCategory(category);
                       break;
                   }
               }
           }

           request.getRequestDispatcher("index.jsp").include(request, response);

      }

 }