<%@page import="model.Order"%>
<%@page import="model.Delivery"%>
<%@page import="dao.DBManager"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/deliveryStatus.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <title>Delivery Status</title>
    </head>
    <body>
        <%
            Order order = (Order) session.getAttribute("order");
            DBManager manager = (DBManager) session.getAttribute("manager");
            Delivery delivery = (Delivery) manager.getDelivery(order);

            User user = (User) session.getAttribute("user");
        %>

        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp"><h3>Home</h3></a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <h4>Hello, <%= user.getFname()%></h4>
                        <div class="user-menu">
                            <a class="header-button" href="">View Account Details</a>
                            <a class="header-button" href="">View Orders</a>
                            <a class="header-button" href="">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header-outline"></div>
        </header>
                        
        <main>
            <h1>Order no <%= order.getOrderID() %></h1>
            <div class="delivery">
                <ul class="status">
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                        </div>
                        <b>Order received</b>
                        <p>dd/mm/yyyy</p>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                        </div>
                        <b>Prepared</b>
                        <p>dd/mm/yyyy</p>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                        </div>
                        <b>Delivered</b>
                        <p>dd/mm/yyyy</p>
                    </li>
                </ul>
                <table>
                    <tr>
                        <td>Order type:</td>
                        <td><%= order.getOrderType() %></td>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td><%= delivery.getDeliveryStreet() %></td>
                    </tr>
                    <tr>
                        <td>Suburb:</td>
                        <td><%= delivery.getDeliverySuburb() %></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><%= delivery.getDeliveryState() %></td>
                    </tr>
                    <tr>
                        <td>Postal:</td>
                        <td><%= delivery.getDeliveryPostal() %></td>
                    </tr>
                    <tr>
                        <td>Order Status:</td>
                        <td><%= order.getStatus() %></td>
                    </tr>
                </table>
                <hr />
                <div class="actions">
                    <form action="create-delivery" method="post">
                        <input type="hidden" name="order-to-update" value="orderID" />
                        <button type="submit" name="action" class="update-button" value="update">Update</button>
                        <button type="submit" name="action" class="delete-button" value="delete">Delete</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
