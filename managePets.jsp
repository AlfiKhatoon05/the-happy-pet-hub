<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Pets</title>
</head>
<body>
    <h2>Manage Pets</h2>

    <!-- Form to Add Pet -->
    <h3>Add New Pet</h3>
    <form action="AddPetServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Category: <input type="text" name="category" required><br><br>
        Age: <input type="number" name="age" required><br><br>
        <input type="submit" value="Add Pet">
    </form>
    <hr>

    <!-- Display All Pets -->
    <h3>Existing Pets</h3>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Age</th>
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

                String sql = "SELECT * FROM pets";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    int age = rs.getInt("age");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= category %></td>
            <td><%= age %></td>
            <td>
                <a href="EditPetServlet?id=<%=id%>">Edit</a> |
                <a href="DeletePetServlet?id=<%=id%>">Delete</a>
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
