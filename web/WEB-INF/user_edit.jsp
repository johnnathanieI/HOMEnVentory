<%-- 
    Document   : admin_edit
    Created on : 14-Apr-2023, 2:28:41 AM
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
        <h1>Edit User</h1>
        <form method="post" action="edit">
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
                        <input type="hidden" name="email" value="${user.email}">
                        ${user.email}
                    </td>
                    <td>
                        <input type="checkbox" name="active" value="checked" 
                               <c:if test="${user.active}">checked</c:if>>
                        <input type="hidden" name="active" value="unchecked">
                    </td>
                    <td><input type="textbox" name="firstName" value="${user.firstName}"></td>
                    <td><input type="textbox" name="lastName" value="${user.lastName}"></td>
                    <td><input type="textbox" name="password" value="${user.password}"></td>
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
