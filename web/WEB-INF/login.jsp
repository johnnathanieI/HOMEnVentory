<%-- 
    Document   : login
    Created on : 12-Apr-2023, 5:45:28 AM
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
        <h2>Login</h2>
        
        <form action="login" method="post">
            Email: <input type="text" name="email" value="${email}"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" name="action" value="Sign in"> <input type="submit" name="action" value="Register">
        </form>
    </body>
</html>
