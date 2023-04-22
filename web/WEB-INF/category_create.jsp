<%-- 
    Document   : category_create
    Created on : 14-Apr-2023, 3:09:17 AM
    Author     : johnn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory | Create Category</title>
    </head>
    <body>
        <h1>Create Category</h1>
        <c:if test="${messageBoolean}">
            <p style="color: lightcoral;
                border: 2px solid black;
                padding: 2px;
                display: inline-block">${message}</p>
        </c:if>
        <form method="post" action="create">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Category Name</th>
                </tr>
                <tr>
                    <td>
                        <input type="textbox" name="id">
                    </td>
                    <td>
                        <input type="textbox" name="categoryName">
                    </td>
                </tr>
            </table><br>
            <input type="hidden" name="role" value="admin">
            <input type="hidden" name="type" value="category">
            <input type="submit" name="action" value="Return">
            <input type="submit" name="action" value="Finish">
        </form>
    </body>
</html>
