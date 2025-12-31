<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
</head>
<body>
    <h2>All Products</h2>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
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
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getDouble("price") %></td>
            <td><%= rs.getString("description") %></td>
        </tr>
        <%
                }
            } catch(Exception e) {
                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(stmt != null) stmt.close(); } catch(Exception e) {}
                try { if(conn != null) conn.close(); } catch(Exception e) {}
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Back to Home</a>
</body>
</html>
