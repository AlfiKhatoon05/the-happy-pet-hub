<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Medicines</title>
</head>
<body>
    <h2>Manage Medicines</h2>

    <!-- Add New Medicine -->
    <h3>Add New Medicine</h3>
    <form action="AddMedicineServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Type: <input type="text" name="type" required><br><br>
        Expiry Date: <input type="date" name="expiry_date" required><br><br>
        <input type="submit" value="Add Medicine">
    </form>
    <hr>

    <!-- Display Existing Medicines -->
    <h3>Existing Medicines</h3>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Expiry Date</th>
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

                String sql = "SELECT * FROM medicines";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    Date expiry = rs.getDate("expiry_date");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= type %></td>
            <td><%= expiry %></td>
            <td>
                <a href="EditMedicineServlet?id=<%=id%>">Edit</a> |
                <a href="DeleteMedicineServlet?id=<%=id%>">Delete</a>
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
