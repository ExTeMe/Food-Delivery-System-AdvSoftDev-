package controller.emp.management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.*;
/*
@WebServlet("/EmpRemove")
public class EmpRemoveServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List staffs = (LinkedList)this.getServletContext().getAttribute("staffs");
        int staffId = Integer.parseInt(request.getParameter("id"));
        for(int i = 0; i < staffs.size(); i++)
            if(staffId == ((Staff)staffs.get(i)).getStaffID())
                staffs.remove(i);
        request.setAttribute("success", "success");
        request.getRequestDispatcher("/empManagement.jsp").forward(request, response);
    }
}
*/