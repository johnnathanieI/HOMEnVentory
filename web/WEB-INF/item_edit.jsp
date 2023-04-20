<%-- 
    Document   : item_edit
    Created on : 20-Apr-2023, 4:07:55 AM
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
        <h2>Edit Item</h2>
        <c:if test="${messageBoolean}">
            <p style="color: lightcoral;
                border: 2px solid black;
                padding: 2px;
                display: inline-block">${message}</p>
        </c:if>
        <form method="post" action="edit">
            <table border="1">
                <tr>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <tr>
                    <td>
                        <select name="category">
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="textbox" name="itemName" value="${item.itemName}">
                    </td>
                    <td>
                        <input type="textbox" name="price" value="${item.price}">
                    </td>
                </tr>
            </table><br>
            <input type="hidden" name="itemId" value="${item.itemId}">
            <input type="hidden" name="owner" value="${item.owner.email}">
            <input type="hidden" name="role" value="user">
            <input type="hidden" name="type" value="item">
            <input type="submit" name="action" value="Return">
            <input type="submit" name="action" value="Finish">
        </form>
    </body>
</html>
