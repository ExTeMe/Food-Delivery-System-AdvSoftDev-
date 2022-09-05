<%@page import="model.Order"%>
<%@page import="model.Delivery"%>
<%@page import="dao.DBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/delivery.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Delivery</title>
    </head>
    <body>
        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="ShowProductsController"><h3>Home</h3></a>
                </div>
                
                <%
                    Order order = (Order) session.getAttribute("order");
                    DBManager manager = (DBManager) session.getAttribute("manager");
                    Delivery delivery = (Delivery) manager.getDelivery(order);
                %>

                <div class="header-end">
                    <div class="user-info header-button">
                        <a><i class="fa fa-user-circle"></i> Hello, Minh Quan Tran</a>
                        <div class="user-menu">
                            <a class="header-button" href="viewcustomer.jsp">View Account Details</a>
                            <a class="header-button" href="ViewOrdersController">View Orders</a>
                            <a class="header-button" href="ViewPaymentHistoryController">View Payment History</a>
                            <a class="header-button" href="logout.jsp">Logout</a>
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
                <p>Order type: <%= order.getOrderType() %></p>  
                <p>Street: <%= delivery.getDeliveryStreet() %></p>
                <p>Suburb: <%= delivery.getDeliverySuburb() %></p>
                <p>State: <%= delivery.getDeliveryState() %></p>
                <p>Postal: <%= delivery.getDeliveryPostal() %></p>
                <p>Order Status: <%= order.getStatus() %></p>
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
