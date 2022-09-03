<%-- 
    Document   : delivery
    Created on : 14 May 2022, 12:32:19 pm
    Author     : Minh Quan Tran
--%>
<%@page import="uts.isd.model.Customer"%>
<%@page import="uts.isd.model.Shipping"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/delivery.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Delivery - IoTBay</title>
    </head>
    <body>
        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="ShowProductsController"><h3>IoTBay</h3></a>
                </div>
                
                <%
                    Customer customer = (Customer) session.getAttribute("customer");
                    Shipping shipping = (Shipping) session.getAttribute("viewedShipping");
                %>

                <div class="header-end">
                    <div class="user-info header-button">
                        <a><i class="fa fa-user-circle"></i> Hello, <%=customer.getCusFName()%></a>
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
            <% if (shipping == null){ %>
            <h1>No delivery found!</h1>
            <% } else { %>
            <h1>Order no <%=shipping.getOrderID()%></h1>
            <div class="delivery">
                <h3>Delivery no <%=shipping.getTrackingID()%> - Estimated delivery date <%=shipping.getDeliveryDate()%></h3>
                <ul class="status">
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                        </div>
                        <b>Order received</b>
                        <p><%=shipping.getReceivedDate()%></p>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png" <% if (!shipping.getOrderStatus().equals("Despatched") && !shipping.getOrderStatus().equals("Delivered")) {%>class="hidden"'<%} %>/>
                        </div>
                        <b>Despatched</b>
                        <p><%=shipping.getDespatchDate()%></p>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png" <% if (!shipping.getOrderStatus().equals("Delivered")) {%>class="hidden"'<%} %>/>
                        </div>
                        <b>Delivered</b>
                        <p><%=shipping.getDeliveryDate()%></p>
                    </li>
                </ul>
                <p>Shipment methods: <%=shipping.getCarrierName()%></p>  
                <p>Street: <%=shipping.getAddressStreet()%></p>
                <p>City: <%=shipping.getAddressCity()%></p>
                <p>State: <%=shipping.getAddressState()%></p>
                <p>Country: <%=shipping.getAddressCountry()%></p>
                <p>Postal: <%=shipping.getAddressPostal()%></p>
                <p>Order Status: <%=shipping.getOrderStatus()%></p>
                <hr />
                <div class="actions">
                    <form action="ShippingController" method="post">
                        <input type="hidden" name="order-to-update" value="<%=shipping.getOrderID()%>" />
                        <button type="submit" name="action" class="update-button" value="update">Update</button>
                        <button type="submit" name="action" class="delete-button" value="delete">Delete</button>
                    </form>
                </div>
            </div>
            <% } %>
        </main>
    </body>
</html>
