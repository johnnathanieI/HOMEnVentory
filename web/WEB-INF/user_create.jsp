<%-- 
    Document   : admin_create
    Created on : 14-Apr-2023, 1:05:21 AM
    Author     : johnn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory | Create User</title>
    </head>
    <body>
        <h1>Create User</h1>
        <form method="post" action="create">
            <c:if test="${messageBoolean}">
                <p style="color: lightcoral;
                    border: 2px solid black;
                    padding: 2px;
                    display: inline-block">${message}</p>
            </c:if>
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>Status</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
                <tr>
                    <td>
                        <input type="textbox" name="email">
                    </td>
                    <td>
                        <input type="checkbox" name="active" value="checked">
                    </td>
                    <td><input type="textbox" name="firstName"></td>
                    <td><input type="textbox" name="lastName"></td>
                    <td><input type="textbox" name="password"></td>
                    <td>
                        <select name="roleId">
                            <option value="1">system admin</option>
                            <option value="2" selected>regular user</option>
                        </select>
                    </td>
                </tr>
            </table><br>
            <input type="hidden" name="role" value="admin">
            <input type="hidden" name="type" value="user">
            <input type="submit" name="action" value="Return">
            <input type="submit" name="action" value="Finish">
        </form>
    </body>
</html>
