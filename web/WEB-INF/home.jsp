<%-- 
    Document   : home
    Created on : 12-Apr-2023, 5:46:13 AM
    Author     : johnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Account Inventory</h1>
        <h3>Welcome, ${email}</h2>
        <form action="home" method="post">
            <input type="submit" name="action" value="Logout">
        </form>
    </body>
</html>
