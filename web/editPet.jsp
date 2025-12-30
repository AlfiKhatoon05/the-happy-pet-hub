<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Pet</title>
</head>
<body>
    <h2>Edit Pet</h2>
    <form action="UpdatePetServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
        Name: <input type="text" name="name" value="<%= request.getAttribute("name") %>" required><br><br>
        Category: <input type="text" name="category" value="<%= request.getAttribute("category") %>" required><br><br>
        Age: <input type="number" name="age" value="<%= request.getAttribute("age") %>" required><br><br>
        <input type="submit" value="Update Pet">
    </form>
    <br>
    <a href="managePets.jsp">Back to Pets</a>
</body>
</html>
