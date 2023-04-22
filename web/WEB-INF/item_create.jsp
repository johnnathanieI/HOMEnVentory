<%-- 
    Document   : item_create
    Created on : 19-Apr-2023, 7:24:42 PM
    Author     : johnn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory | Add Item</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>
        <h2>Add Item</h2>
        <c:if test="${messageBoolean}">
            <p style="color: lightcoral;
                border: 2px solid black;
                padding: 2px;
                display: inline-block">${message}</p>
        </c:if>
        <form action="create" method="post">
            <table border="1">
                <tr>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <tr>
                    <td>
                        <select name="category">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.categoryId}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="textbox" name="itemName">
                    </td>
                    <td>
                        <input type="textbox" name="price">
                    </td>
                </tr>
            </table><br>
            
            <input type="hidden" name="role" value="user">
            <input type="hidden" name="type" value="item">
            <input type="submit" name="action" value="Return">
            <input type="submit" name="action" value="Finish">
        </form>
    </body>
</html>
