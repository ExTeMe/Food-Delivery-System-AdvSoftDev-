<%-- 
    Document   : shipping
    Created on : 14 May 2022, 6:50:41 pm
    Author     : someo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/placeOrder.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Place Delivery</title>
    </head>
    <body>
        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="ShowProductsController"><h3>Home</h3></a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <a><i class="fa fa-user-circle"></i> Hello, Minh Quan Tran</a>
                        <div class="user-menu">
                            <a class="header-button" href="edituser.jsp">Edit Account</a>
                            <a class="header-button" href="ViewOrdersController">My Orders</a>
                            <a class="header-button" href="logout.jsp">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header-outline"></div>
        </header>
        <main>
            <form action="ShippingController" method="post">
                <h1>Delivery details</h1>
                <hr />
                <label>Order type</label>
                <ul id="order-type">
                    <li>
                        <input type="radio" id="delivery" name="order-type" value="delivery" required>
                        <label for="delivery">Delivery</label>
                    </li>
                    <li>
                        <input type="radio" id="pickup" name="order-type" value="pickup" required>
                        <label for="pickup">Pickup</label>
                    </li>
                </ul>
                <hr />
                <label>Delivery Address</label>
                <div id="address" class="address">
                    <label for="street">Street</label>
                    <input type="text" id="street" name="street" required>
                    <label for="suburb">Suburb</label>
                    <input type="text" id="suburb" name="suburb" required>
                    <label for="state">State</label>
                    <input type="text" id="state" name="state" required>
                    <label for="postal">Postal Code</label>
                    <input type="text" id="postal" name="postal" required>
                </div>
                <hr />
                <button type="submit">Continue</button>
            </form>
        </main>
    </body>
</html>
