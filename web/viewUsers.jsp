<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Users</title>
</head>
<body>
    <h2>Registered Users</h2>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Status</th>
            <th>Action</th>
        </tr>

        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

                String sql = "SELECT * FROM users";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String status = rs.getString("status");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= email %></td>
            <td><%= status %></td>
            <td>
                <% if("Pending".equalsIgnoreCase(status)) { %>
                    <a href="ApproveUserServlet?id=<%=id%>">Approve</a>
                <% } else { %>
                    Approved
                <% } %>
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
