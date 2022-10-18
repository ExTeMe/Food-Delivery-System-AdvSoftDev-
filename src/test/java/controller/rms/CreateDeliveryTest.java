package controller.rms;

import static org.mockito.Mockito.*;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import model.Order;
import model.Delivery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import controller.CreateDelivery;
import dao.DBManager;

public class CreateDeliveryTest extends Mockito {
    private CreateDelivery servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private DBManager manager;
    private Order order;

    @Before
    public void setUp() {
        servlet = new CreateDelivery();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        manager = mock(DBManager.class);
        order = new Order(101010, 989898, 303030, null, 0, "Submitted", 0, null, null);
    }

    public void checkSession(int order, int street, int suburb, int state, int postal) {
        verify(session, times(order)).setAttribute("orderErr", "Missing or wrong order");
        verify(session, times(street)).setAttribute("streetErr", "Invalid street");
        verify(session, times(suburb)).setAttribute("suburbErr", "Invalid suburb");
        verify(session, times(state)).setAttribute("stateErr", "Invalid State, use state code with capital letters");
        verify(session, times(postal)).setAttribute("postalErr", "Invalid Postal Code, 4 digits");
    }

    // Test for Correct Information (valid orderID, state, postal code)
    @Test
    public void testCorrectInformation() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher(
                "get-delivery?orderID=" + order.getOrderID())).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        verify(manager, times(1)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("get-delivery?orderID=" + order.getOrderID());
        checkSession(0, 0, 0, 0, 0);
    }

    // Test for invalid orderID (null)
    @Test
    public void testNullOrderID() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(null);
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid orderID (6 letters)
    @Test
    public void testLetterOrderID() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn("abcdef");
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid orderID (order is not found in database)
    @Test
    public void testNotFoundOrder() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(null);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid street (empty string)
    @Test
    public void testEmptyStreet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 1, 0, 0, 0);
    }

    // Test for invalid street (null)
    @Test
    public void testNullStreet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn(null);
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 1, 0, 0, 0);
    }

    // Test for invalid suburb (empty string)
    @Test
    public void testEmptySuburb() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 1, 0, 0);
    }

    // Test for invalid street (null)
    @Test
    public void testNullSuburb() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn(null);
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 1, 0, 0);
    }

    // Test for invalid state (4 lowercase letters)
    @Test
    public void testLowerCaseState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("ansl");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (6 uppercase letters)
    @Test
    public void testExceedLimitState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("AAAAAA");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (empty string "")
    @Test
    public void testEmptyState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("");
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (null)
    @Test
    public void testNullState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn(null);
        when(request.getParameter("postal")).thenReturn("2113");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid postal (4 letters)
    @Test
    public void testLetterPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("abcd");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (6 numbers)
    @Test
    public void testExceedLimitPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("123456");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (empty string)
    @Test
    public void testEmptyPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn("");
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (null)
    @Test
    public void testNullPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        when(request.getParameter("order-type")).thenReturn("Delivery");
        when(request.getParameter("orderID")).thenReturn(Integer.toString(order.getOrderID()));
        when(manager.getOrder(order.getOrderID())).thenReturn(order);
        when(request.getParameter("street")).thenReturn("9 Delhi Road");
        when(request.getParameter("suburb")).thenReturn("North Ryde");
        when(request.getParameter("state")).thenReturn("NSW");
        when(request.getParameter("postal")).thenReturn(null);
        when(request.getParameter("instructions")).thenReturn("Testing");
        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        Delivery delivery = new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                "Testing");

        when(manager.getDelivery(order.getOrderID())).thenReturn(null);
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request).getRequestDispatcher("createDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }
}
