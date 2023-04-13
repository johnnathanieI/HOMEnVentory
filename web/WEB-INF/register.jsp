<%-- 
    Document   : register
    Created on : 13-Apr-2023, 3:21:21 AM
    Author     : johnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Registration</h2>
        <form action="register" method="post">
            First Name: <input type="text" name="firstName" value="${firstName}"><br>
            Last Name: <input type="text" name="lastName" value="${lastName}"><br>
            Email: <input type="text" name="email" value="${email}"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" name="action" value="Register">
            <input type="submit" name="action" value="Return to Login">
        </form>
    </body>
</html>
