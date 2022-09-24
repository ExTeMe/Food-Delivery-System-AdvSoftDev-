<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/23/2022
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>

<% if (manageMode) { %>
<div class="dropdown">
    <% RCategories categories;
        if (session.getAttribute("categories") == null) {
            categories = new RCategories();
            session.setAttribute("categories", categories);
        } else {
            categories = (RCategories) session.getAttribute("categories");
        }%>
    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        <%-- For demonstration purposes since not interacting with the DBs --%>
        <% Restaurant res;
            if (session.getAttribute("restaurant") == null) {
                res = new Restaurant(4, categories.getCategories().get(0),
                        "Obama Fried Chicken", 5, "Bal", 6578, "NSW", "Jask",
                        "Australia", true, 88389491139L, "Robbert", 737487, 335433333333L);
                session.setAttribute("restaurant", res);
            } else {
                res = (Restaurant) session.getAttribute("restaurant");
            }%>
        <%= res.getCategory().getrCatName() %>
    </button>
    <ul class="dropdown-menu">
        <% for (RCategory category : categories.getCategories()) { %>
        <li>
            <a class="dropdown-item <%= (category.getRCat_ID() == res.getCategory().getRCat_ID()) ? "disabled" : "" %>"
               href="update-category?resID=<%=res.getRestaurantID()%>&cat=<%=category.getRCat_ID()%>"><%=category.getrCatName()%></a>
        </li>
        <% } %>
    </ul>
</div>
<% } %>