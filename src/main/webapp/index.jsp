
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="controller.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">
        <title>Main Menu</title>
    </head>

    <%-- Used to check if the appstaff has entered manageMode or not --%>
    <% Boolean manageMode = false;
       if (session.getAttribute("manageMode") == null) {
           session.setAttribute("manageMode", false);
           // Initially manageMode is null and not initialized
       }
       else {
           manageMode = (Boolean) session.getAttribute("manageMode");
       } %>

    <body class="overflow-hidden bg-light">

        <%--NavBar--%>
        <nav class="navbar navbar-expand-lg bg-light mb-3 card">
            <div class="container">
                <a class="h1 navbar-brand text-dark ms-2 mt-2" href="#">FDS</a>
                <div class="float-end me-2">
                    <%-- If not in manage mode, show the register/login etc... buttons, else only show management related buttons --%>
                    <% if (!manageMode) { %>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerLogin.jsp">Customer Login</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerRegister.jsp">Customer Register</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffLogin.jsp">Staff Login</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffRegister.jsp">Staff Register</a>
                    <% } else { %>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="all-category">Manage Category</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="all-restaurant">Manage Restaurant</a>
                        <%-- this button placement needs to be placed in individual restaurants page --%>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="empManagement.jsp">Emp Management</a>
                    <% } %>
                    <%--Temporary - AppStaff login is not made yet--%>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="manage-mode">
                            <%= (manageMode) ? "Exit Mode" : "Manage Mode"%>
                        </a>
                </div>
                <form action="find-res" method="post" class="searchf d-flex me-5" role="search">
                    <input class="searchbar form-control me-1" type="search" placeholder="Search" aria-label="Search">
                    <button class="search btn btn-outline-success" type="submit">Search</button>
                    <input type="hidden" name="view" value="user">
                </form>
            </div>
        </nav>

        <%--Restaurants--%>
        <section class="container text-center">
            <div class="res row align-items-center justify-content-center">
                <%-- Ignore the unchecked cast, restaurants will only ever be ArrayList<Restaurants> or null --%>
                <% ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants");
                for (Restaurant res : restaurants) { %>
                    <div class="col-lg-4 mt-5">
                        <div class="bs">
                            <img src="images/<%=res.getImageReference()%>" alt=<%=res.getImageReference()%>>
                            <div class="flex-column">
                                <a><%=res.getRestaurantName()%></a>
                            </div>
                        </div>
                    </div>
                <% } %>
            </div>
        </section>

    </body>

</html>