<%-- 
    Document   : admin
    Created on : 12-Apr-2023, 8:33:54 PM
    Author     : johnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory | Admin View</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Admin View</h2>
        <h3>Welcome, ${firstName}</h3>
        
        <form method="post" action="admin">
            <h4>Users</h4>
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>Status</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.email}</td>
                        <td><input type="checkbox" name="active" value="true"
                            <c:if test="${user.active}">checked</c:if> disabled></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.password}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.role.roleId == 1}">Admin</c:when>
                                <c:otherwise>Regular User</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form method="post" action="admin">
                                <input type="hidden" name="email" value="${user.email}">
                                <input type="submit" name="action" value="Edit User">
                            </form>
                        </td>
                        <td>
                            <form method="post" action="admin">
                                <input type="hidden" name="email" value="${user.email}">
                                <input type="submit" name="action" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table><br>
            <input type="submit" name="action" value="Create New User"><br><br>
            
            <h4>Categories</h4>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                <c:forEach items="${categories}" var="category">
                    <c:set var="categoryVal" value="${category.categoryId}"/>
                    <tr>
                        <td>${category.categoryId}</td>
                        <td>${category.categoryName}</td>
                        <td>
                            <form method="post" action="admin">
                                <input type="hidden" name="id" value="${category.categoryId}">
                                <input type="submit" name="action" value="Edit Category">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table><br>
            <input type="submit" name="action" value="Create New Category"><br><br>
            <input type="submit" name="action" value="Logout">
        </form>
    </body>
</html>
