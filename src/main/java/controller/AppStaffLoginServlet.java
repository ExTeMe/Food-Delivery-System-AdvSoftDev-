package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AppStaffServlet", value = "/appstaff")
public class AppStaffLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This will be created in the future for appstaff + appadmins to use to access
        // It will not be accessible from the common app through any buttons
        // appstaffs or appadmins will have to manually type the URL /appstaff to keep it confidential
        // Once login, another servlet will be called and verify the info, if valid, it will redirect them to the
        // index.jsp page and be able to see hidden features.
    }
}
