<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
    <h2>Edit Product</h2>
    <form action="UpdateProductServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
        Name: <input type="text" name="name" value="<%= request.getAttribute("name") %>" required><br><br>
        Price: <input type="number" step="0.01" name="price" value="<%= request.getAttribute("price") %>" required><br><br>
        Description: <input type="text" name="description" value="<%= request.getAttribute("description") %>" required><br><br>
        <input type="submit" value="Update Product">
    </form>
    <br>
    <a href="manageProducts.jsp">Back to Products</a>
</body>
</html>
