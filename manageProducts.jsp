<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Products</title>
</head>
<body>
    <h2>Manage Products</h2>

    <!-- Form to Add Product -->
    <h3>Add New Product</h3>
    <form action="AddProductServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Price: <input type="number" step="0.01" name="price" required><br><br>
        Description: <input type="text" name="description" required><br><br>
        <input type="submit" value="Add Product">
    </form>
    <hr>

    <!-- Display All Products -->
    <h3>Existing Products</h3>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

                String sql = "SELECT * FROM products";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= price %></td>
            <td><%= description %></td>
            <td>
                <a href="EditProductServlet?id=<%=id%>">Edit</a> |
                <a href="DeleteProductServlet?id=<%=id%>">Delete</a>
            </td>
        </tr>
        <%
                }
            } catch(Exception e) {
                out.println("<tr><td colspan='5'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(stmt != null) stmt.close(); } catch(Exception e) {}
                try { if(conn != null) conn.close(); } catch(Exception e) {}
            }
        %>
    </table>

    <br>
    <a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
