<%-- 
    Document   : settings
    Created on : 12-Apr-2023, 7:40:32 PM
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
        <h2>Settings</h2>
        <h3>Welcome, ${firstName} ${lastName}</h3>
        <c:if test="${messageBoolean}">
            <p style="color: lightcoral;
                border: 2px solid black;
                padding: 2px;
                display: inline-block">${message}</p>
        </c:if>
        <form method="post" action="edit">
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="email" value="${email}">${email}
                    </td>
                    <td><input type="textbox" name="firstName" value="${firstName}"></td>
                    <td><input type="textbox" name="lastName" value="${lastName}"></td>
                    <td><input type="textbox" name="password" value="${password}"></td>
                </tr>
            </table><br>
            <input type="hidden" name="roleId" value="${roleId}">
            <input type="hidden" name="role" value="user">
            <input type="hidden" name="type" value="settings">
            <input type="submit" name="action" value="Deactivate"><br><br>
            <input type="submit" name="action" value="Return">
            <input type="submit" name="action" value="Apply">
        </form>
    </body>
</html>
