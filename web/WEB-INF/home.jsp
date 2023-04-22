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
        <title>HOME nVentory | Home</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Account Inventory</h1>
        <h3>Welcome, ${firstName} ${lastName}</h2>
        <form action="home" method="post">
            <h4>Items</h4>
            <table border="1">
                <tr>
                    <th style="padding: 3px">Category</th>
                    <th style="padding: 3px">Name</th>
                    <th style="padding: 3px">Price</th>
                </tr>
                <c:choose>
                    <c:when test="${empty items}">
                        <p style="color: lightcoral;
                        border: 2px solid black;
                        padding: 2px;
                        display: inline-block">The list is empty</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td style="padding: 3px">${item.category.categoryName}</td>
                                <td style="padding: 3px">${item.itemName}</td>
                                <td style="padding: 3px">${item.price}</td>
                                <td>
                                    <form method="post" action="home">
                                        <input type="hidden" name="itemId" value="${item.itemId}">
                                        <input type="submit" name="action" value="Edit">
                                    </form>
                                </td>
                                <td>
                                    <form method="post" action="home">
                                        <input type="hidden" name="itemId" value="${item.itemId}">
                                        <input type="submit" name="action" value="Delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table><br>
            <input type="hidden" name="firstName" value="${firstName}">
            <input type="hidden" name="lastName" vaue="${lastName}">
            <input type="submit" name="action" value="Add Item"><br><br>
            <input type="submit" name="action" value="Settings">
            <input type="submit" name="action" value="Logout">
        </form>
    </body>
</html>
