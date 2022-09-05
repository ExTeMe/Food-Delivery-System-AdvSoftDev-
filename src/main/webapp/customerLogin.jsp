<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Login</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <body>
        <h1>Customer Login</h1>
        <br/>
        <form action="StaffLoginServlet" method="post">
            <table>
                <tr>
                    <td><label>email:</label></td>
                    <td><input type="text" name="email" required="true"></td>
                </tr> 
                <tr>
                    <td><label>password:</label></td>
                    <td><input type="text" name="password" required="true"></td>
                </tr> 
            </table> 
            <div class="center">
                <input type ="submit" value="Login">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>