<%-- 
    Document   : home
    Created on : 12-Apr-2023, 5:46:13 AM
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
        <h1>Welcome, ${email}</h1>
        <form action="home" method="post">
            <a href="login">Logout</a>
        </form>
    </body>
</html>
