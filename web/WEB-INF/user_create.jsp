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
        <title>HOME nVentory</title>
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
                    <td>Email</td>
                    <td>Status</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Password</td>
                    <td>Role</td>
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
                        <select name="role">
                            <option value="1">system admin</option>
                            <option value="2" selected>regular user</option>
                        </select>
                    </td>
                </tr>
            </table><br>
            <input type="hidden" name="type" value="user">
            <input type="submit" name="action" value="Return">
        </form>
    </body>
</html>
