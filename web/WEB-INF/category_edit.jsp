<%-- 
    Document   : category_edit
    Created on : 14-Apr-2023, 3:53:01 AM
    Author     : johnn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Category</h1>
        <form method="post" action="edit">
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>Category Name</td>
                </tr>
                <tr>
                    <td>${category.categoryId}</td>
                    <td>
                        <input type="hidden" name="id" value="${category.categoryId}">
                        <input type="textbox" name="categoryName" value="${category.categoryName}">
                    </td>
                </tr>
            </table><br>
            <input type="hidden" name="type" value="category">
            <input type="submit" name="action" value="Return">
        </form>
    </body>
</html>
