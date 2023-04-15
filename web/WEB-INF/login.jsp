<%-- 
    Document   : login
    Created on : 12-Apr-2023, 5:45:28 AM
    Author     : johnn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Login</h2>
        
        <c:if test="${messageBoolean}">
            <p style="color: lightcoral;
                border: 2px solid black;
                padding: 2px;
                display: inline-block">${message}</p>
        </c:if>
        <form action="login" method="post">
            Email: <input type="text" name="email" value="${email}"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" name="action" value="Sign in"> <input type="submit" name="action" value="Register">
        </form>
    </body>
</html>
