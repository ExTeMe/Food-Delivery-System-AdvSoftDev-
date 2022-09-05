package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/EmpEdit")
public class EmpEditServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List staffs = (LinkedList)this.getServletContext().getAttribute("staffs");
        int staffId = Integer.parseInt(request.getParameter("staffId"));
        for(int i = 0; i < staffs.size(); i++)
        {
            if(staffId == ((Staff)staffs.get(i)).getStaffID())
            {
                try{
                    ((Staff) staffs.get(i)).setPrivilege(Integer.parseInt(request.getParameter("privilege")));
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                    request.setAttribute("error", true);
                    request.getRequestDispatcher("/empManagement.jsp").forward(request, response);
                }
                ((Staff) staffs.get(i)).setPrivilege(Integer.parseInt(request.getParameter("privilege")));
                ((Staff) staffs.get(i)).setPosition(request.getParameter("position"));
            }
        }
        request.setAttribute("success", "success");
        request.getRequestDispatcher("/empManagement.jsp").forward(request, response);
    }
}
