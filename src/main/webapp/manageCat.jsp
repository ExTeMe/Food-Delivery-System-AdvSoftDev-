<%--
  Created by IntelliJ IDEA.
  User: Benz
  Date: 9/4/2022
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.*" %>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/manageCat.css">
    <title>Manage Category</title>
</head>

<body class="overflow-hidden bg-light">

    <div class="container-fluid text-center">

        <div class="me-3">
            <a href="modifyCat.jsp" class="float-start btn btn-light text-dark ms-2"> Add </a>
            <a href="index.jsp" class="float-end btn btn-light text-dark ms-2"> Exit </a>
        </div>

        <h1 class="h1 text-light mt-3 ms-5">Manage Category</h1>

        <% RCategories rcategories = (RCategories) session.getAttribute("categories");
           ArrayList<RCategory> listOfCategories = rcategories.getCategories();
        %>

        <table class="table table-light mt-4">
            <tr>
                <th class="text-dark text-center ">Name</th>
                <th class="text-dark text-center ">Description</th>
                <th class="text-dark text-center ">Options</th>
            </tr>

            <% for (RCategory category : listOfCategories) { %>
                <tr class="catrow">
                    <td class="text-dark text-center "><%=category.getrCatName()%></td>
                    <td class="text-dark text-center "><%=category.getrCatDescription()%></td>
                    <td class="text-dark text-center">
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="modifyCat.jsp?cat=<%=category.getRCat_ID()%>">Edit</a>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="delete-cat?cat=<%=category.getRCat_ID()%>">Delete</a>
                    </td>
                </tr>
            <% } %>
        </table>


    </div>

</body>
</html>
