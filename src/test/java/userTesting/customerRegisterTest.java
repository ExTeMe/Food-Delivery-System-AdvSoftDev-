package userTesting;

import org.junit.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DBConnector;
import dao.DBManager;

public class customerRegisterTest {

    @Test
    public void testRegister() throws Exception {

        HttpSession session = request.getSession();

        DBConnector db;
        DBManager manager;
        Connection conn;
        
        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
    
            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
    
        } catch (Exception e) {
            e.printStackTrace();
        }

        manager = (DBManager) session.getAttribute("manager");

        try {
            manager.addUser("firstName", "lastName", "password", "email", 1234567890, "1234-12-12", 123, "streetName", 1234, "state", "suburb", "country", true);
            assertEquals("firstName", manager.findUser("email", "password").getFname(), "Test first name recorded Correctly");
            assertEquals("lastName", manager.findUser("email", "password").getLname(), "Test last name recorded correctly");
            assertEquals("email", manager.findUser("email", "password").getEmail(), "Testing email recorded correctly");
            assertEquals(1234567890, manager.findUser("email", "password").getPhoneNo(), "Testing phone number recorded correctly");
        }
        catch (NullPointerException ex) {

        }
        catch (SQLException ex) {

        }



    }
 /*  
    @Test
    public void testCorrectCredentials() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        DBManager manager = mock(DBManager.class);
        AppStaff appStaff = manager.appStaffLogin("benzchua31@gmail.com", "ro0T!ro0T!");

        when(req.getSession()).thenReturn(mock(HttpSession.class));
        when(req.getSession().getAttribute("DBManager")).thenReturn(manager);
        when(req.getParameter("email")).thenReturn("benzchua31@gmail.com");
        when(req.getParameter("pass")).thenReturn("ro0T!ro0T!");
        when(req.getRequestDispatcher("index")).thenReturn(mock(RequestDispatcher.class));
        when(manager.appStaffLogin("benzchua31@gmail.com", "ro0T!ro0T!"))
                .thenReturn(appStaff);

        new AppStaffLoginServletPublic().doPost(req, res);

        // verify that session.setAttribute for appStaff
        verify(req.getSession(), times(1)).setAttribute("appStaff", appStaff);

        // verify that getRequestDispatcher("index") is called once
        verify(req, times(1)).getRequestDispatcher("index");
    }
    */
    
}
