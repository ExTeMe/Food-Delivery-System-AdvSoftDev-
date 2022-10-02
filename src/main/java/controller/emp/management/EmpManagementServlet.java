package controller.emp.management;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import service.RestaurantEmpService;
import service.impl.RestaurantEmpServiceImpl;

import java.io.IOException;
import java.util.*;

/**
 * Handling employee-related business servlet
 * @author Hao Zeng
 * @version 1.0
 */
@WebServlet("/empManage/*")
public class EmpManagementServlet extends HttpServlet{

    private RestaurantEmpService restaurantEmpService = new RestaurantEmpServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // Get the request path first, and then remove the root path of the project will get the servlet path correctly.
        String servletPath = request.getRequestURI().substring(request.getContextPath().length());
        // Use template method pattern to reduce the number of classes
        if (servletPath.equals("/empManage/editPrivilege"))
            doEditPrivilege(request, response);
        else if (servletPath.equals("/empManage/editPosition"))
            doEditPosition(request, response);
        else if (servletPath.equals("/empManage/showEmp"))
            doList(request, response);
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) {
        List<Staff> staffs = restaurantEmpService.getStaffListByPrivilegeRange();
        request.setAttribute("StoreEmps", staffs);
        try {
            request.getRequestDispatcher("/empManagement.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doEditPosition(HttpServletRequest request, HttpServletResponse response) {
    }

    private void doEditPrivilege(HttpServletRequest request, HttpServletResponse response) {
    }
}
