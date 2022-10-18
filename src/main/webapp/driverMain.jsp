<%@page import="model.User"%>
<%@page import="model.DeliveryDriver"%>
<%@page import="dao.DBManager"%>
<%@page import="model.Order"%>
<%@page import="model.Delivery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/driverMain.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <script src="./js/deliveryStatus.js" defer></script>
        <script src="./js/driverMain.js" defer></script>
        <title>Driver Main</title>
    </head>
    <body>
        <%
            // for testing
            session.setAttribute("user", new User(989898, "Minh Quan", "Tran"));

            User user = (User) session.getAttribute("user");
            DBManager manager = (DBManager) session.getAttribute("manager");
            DeliveryDriver driver = (DeliveryDriver) manager.getDriver(user);

            Order order = (Order) session.getAttribute("order");
            Delivery delivery = (Delivery) manager.getDelivery(order.getOrderID());
        %>
        <input id="orderID" value="<%= order.getOrderID() %>" type="hidden"/>
        
        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp"><h3>Home</h3></a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <% if (driver != null) { %> 
                            <span>Hello, <%= driver.getFname()%></span>
                            <div class="user-menu">
                                <a class="header-button" href="">View Account Details</a>
                                <a class="header-button" href="">View Orders</a>
                                <a class="header-button" href="">Logout</a>
                            </div>
                        <% } else { %>
                            <a href="customerLogin.jsp">Login</a>
                            <span>&nbsp;/&nbsp;</span>
                            <a href="customerRegister.jsp">Register</a>
                        <% } %>
                    </div>
                </div>
            </div>

            <div class="header-outline"></div>
        </header>
                        
        <main>
            <div class="section">
                <h3>Driver Details</h3>
                <table>
                    <tr>
                        <td>Full name:</td>
                        <td><%= driver.getFname() + " " + driver.getLname() %></td>
                    </tr>
                    <tr>
                        <td>Driver ID:</td>
                        <td><%= driver.getDriverID() %></td>
                    </tr>
                    <tr>
                        <td>User ID:</td>
                        <td><%= driver.getUserID() %></td>
                    </tr>
                    <tr>
                        <td>Number Plate:</td>
                        <td><%= driver.getNumberPlate() %></td>
                    </tr>
                    <tr>
                        <td>Vehicle Description:</td>
                        <td><%= driver.getVehicleDescription() %></td>
                    </tr>
                    <tr>
                        <td>Rating:</td>
                        <td><%= driver.getRating() %></td>
                    </tr>
                    <tr>
                        <td>Bank Account Name:</td>
                        <td><%= driver.getdAccountName() %></td>
                    </tr>
                    <tr>
                        <td>Bank State Branch (BSB):</td>
                        <td><%= driver.getdBSB() %></td>
                    </tr>
                    <tr>
                        <td>Bank Account Number:</td>
                        <td><%= driver.getdAccountNumber() %></td>
                    </tr>
                </table>
            </div>

            <div class="section">
                <h3>Current Delivery</h3>
                <h1>Delivery no <%= delivery.getDeliveryID() %></h1>
                <div class="delivery">
                    <ul class="status">
                        <li>
                            <div class="check-icon">
                                <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                            </div>
                            <b>Order received</b>
                        </li>
                        <li>
                            <div class="check-icon">
                                <img 
                                    src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                    id="prepared-img"
                                />
                            </div>
                            <b>Prepared</b>
                        </li>
                        <li>
                            <div class="check-icon">
                                <img 
                                    src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                    id="delivered-img"
                                />
                            </div>
                            <b>Delivered</b>
                        </li>
                    </ul>
                    <hr />
                    <table>
                        <tr>
                            <td>Order Status:</td>
                            <td id="order-status"></td>
                        </tr>
                        <tr>
                            <td>Order type:</td>
                            <td id="order-type"></td>
                        </tr>
                        <tr>
                            <td>Street:</td>
                            <td id="order-street"></td>
                        </tr>
                        <tr>
                            <td>Suburb:</td>
                            <td id="order-suburb"></td>
                        </tr>
                        <tr>
                            <td>State:</td>
                            <td id="order-state"></td>
                        </tr>
                        <tr>
                            <td>Postal:</td>
                            <td id="order-postal"></td>
                        </tr>
                    </table>
                    <hr />
                    <div class="actions">
                        <form action="create-delivery" method="post">
                            <button type="submit" name="action" class="update-button" value="update">Update</button>
                            <button type="submit" name="action" class="delete-button" value="delete">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
