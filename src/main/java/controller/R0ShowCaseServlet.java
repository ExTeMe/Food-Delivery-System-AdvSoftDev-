package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet(value = "/init", loadOnStartup = 1)
public class R0ShowCaseServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        List<Staff> staffs = new LinkedList<>();
        int id = 30, staffId = 1;
        char c = 'a';
        LocalDate localDate = LocalDate.of(1998, 10, 31);
        for(int i = 0; i < 20; i++)
        {
            staffs.add(new Staff(id++, String.valueOf(c), String.valueOf(c),"test", String.valueOf(c) + "@test.com",
                    new Random().nextInt(999999999-100000000) + 100000000, localDate, new Random().nextInt(10000-1) + 1, "??",
                    2154, "NSW", "none", "AU",
                    true, staffId++, 8, 0, "Newcomers"));
            c++;
        }
        this.getServletContext().setAttribute("staffs", staffs);
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.sendRedirect(request.getContextPath());
    }
}