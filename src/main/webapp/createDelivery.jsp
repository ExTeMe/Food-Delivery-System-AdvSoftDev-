<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/createOrder.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <title>Place Delivery</title>
    </head>
    <body>
        <header>
            <%
                // for testing
                session.setAttribute("user", new User(989898, "Minh Quan", "Tran"));

                User user = (User) session.getAttribute("user");
            %>

            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp">Home</a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <span>Hello, <%= user.getFname()%></span>
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
            <form action="create-delivery" method="post">
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
                    <li>
                        <input type="radio" id="dinein" name="order-type" value="dinein" required>
                        <label for="dinein">Dine-in</label>
                    </li>
                </ul>
                <hr />
                <div class="delivery-input">
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
                    <label for="instructions">Instructions</label>
                    <textarea id="instructions" name="instructions" maxlength="100" rows="4"></textarea>
                    <hr />
                </div>
                <button type="submit">Continue</button>
            </form>
        </main>

        <script>
            const orderType = document.querySelector("#order-type");
            const delivery = document.querySelector("#delivery");
            const deliveryInput = document.querySelector(".delivery-input");

            orderType.addEventListener("click", () => {
                if (delivery.checked){
                    deliveryInput.style.display = "flex";
                } else {
                    deliveryInput.style.display = "none";
                }
            })
        </script>
    </body>
</html>
