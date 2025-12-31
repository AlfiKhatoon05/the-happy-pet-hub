<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    // Check if admin is logged in
    String adminFullName = (String) session.getAttribute("adminFullName");
    Integer adminId = (Integer) session.getAttribute("adminId");
    if (adminFullName == null || adminId == null) {
        response.sendRedirect("admin-login.jsp?error=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Management - Admin Panel</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #FF9800, #F57C00); color: white; padding: 20px; }
        .header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
        .welcome { font-size: 1.5em; font-weight: bold; }
        .nav-links { display: flex; gap: 20px; }
        .nav-links a { color: white; text-decoration: none; padding: 8px 15px; border-radius: 20px; transition: background 0.3s ease; }
        .nav-links a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .page-title { color: #333; font-size: 2em; }
        .back-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }
        .back-btn:hover { background: #5a6268; }
        
        .stats-cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }
        .stat-card { background: white; border-radius: 10px; padding: 25px; text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.1); }
        .stat-number { font-size: 2.5em; font-weight: bold; color: #FF9800; margin-bottom: 10px; }
        .stat-label { color: #666; font-size: 1.1em; }
        
        .users-table { width: 100%; background: white; border-radius: 15px; padding: 25px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        .users-table table { width: 100%; border-collapse: collapse; }
        .users-table th, .users-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        .users-table th { background: #f8f9fa; font-weight: bold; color: #333; }
        .users-table tr:hover { background: #f8f9fa; }
        
        .action-buttons { display: flex; gap: 8px; }
        .action-btn { padding: 6px 12px; text-decoration: none; border-radius: 5px; font-size: 12px; font-weight: bold; transition: all 0.3s ease; }
        .edit-btn { background: #2196F3; color: white; }
        .edit-btn:hover { background: #1976D2; }
        .delete-btn { background: #f44336; color: white; }
        .delete-btn:hover { background: #d32f2f; }
        
        .pets-count { background: #e3f2fd; color: #1976D2; padding: 4px 8px; border-radius: 10px; font-size: 12px; font-weight: bold; }
        
        .no-users { text-align: center; padding: 50px; color: #666; }
        .message { padding: 15px; margin-bottom: 20px; border-radius: 8px; text-align: center; font-size: 16px; }
        .error { background: #ffebee; color: #c62828; border: 1px solid #ffcdd2; }
        .success { background: #e8f5e8; color: #2e7d32; border: 1px solid #c8e6c9; }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">👥 User Management</div>
            <div class="nav-links">
                <a href="admin-dashboard.jsp">Dashboard</a>
                <a href="AdminLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">User Management</h1>
            <a href="admin-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
        
        <%-- Display messages --%>
        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error != null) {
        %>
            <div class="message error">
                <%= error %>
            </div>
        <%
            }
            if (success != null) {
        %>
            <div class="message success">
                <%= success %>
            </div>
        <%
            }
        %>
        
        <div class="stats-cards">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                int totalUsers = 0;
                int totalPets = 0;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String usersSQL = "SELECT COUNT(*) as count FROM user202";
                    pstmt = conn.prepareStatement(usersSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) totalUsers = rs.getInt("count");
                    rs.close(); pstmt.close();
                    
                    String petsSQL = "SELECT COUNT(*) as count FROM petsnew";
                    pstmt = conn.prepareStatement(petsSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) totalPets = rs.getInt("count");
            %>
            <div class="stat-card">
                <div class="stat-number"><%= totalUsers %></div>
                <div class="stat-label">Total Users</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= totalPets %></div>
                <div class="stat-label">Total Pets</div>
            </div>
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception e) {}
                    try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                    try { if (conn != null) conn.close(); } catch (Exception e) {}
                }
            %>
        </div>
        
        <div class="users-table">
            <%
                conn = null; pstmt = null; rs = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT u.*, (SELECT COUNT(*) FROM petsnew p WHERE p.user_id = u.user_id) as pet_count " +
                                "FROM user202 u ORDER BY u.created_date DESC";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
            %>
                        <table>
                            <thead>
                                <tr>
                                    <th>User ID</th>
                                    <th>Username</th>
                                    <th>Full Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Pets</th>
                                    <th>Registered Date</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
            <%
                        do {
                            int userId = rs.getInt("user_id");
                            String fullName = rs.getString("full_name");
            %>
                            <tr>
                                <td>#<%= userId %></td>
                                <td><%= rs.getString("username") %></td>
                                <td><%= fullName %></td>
                                <td><%= rs.getString("email") %></td>
                                <td><%= rs.getString("phone") != null ? rs.getString("phone") : "N/A" %></td>
                                <td><span class="pets-count"><%= rs.getInt("pet_count") %> pets</span></td>
                                <td><%= rs.getDate("created_date") %></td>
                                <td>
                                    <div class="action-buttons">
                                        <!-- Edit Button - Simple version -->
                                        <a href="edit-user.jsp?userId=<%= userId %>" class="action-btn edit-btn">Edit</a>
                                        
                                        <!-- Delete Button - Actually Works -->
                                        <a href="DeleteUserServletnew?userId=<%= userId %>" 
                                           class="action-btn delete-btn"
                                           onclick="return confirm('Are you sure you want to delete <%= fullName %>?')">
                                            Delete
                                        </a>
                                    </div>
                                </td>
                            </tr>
            <%
                        } while (rs.next());
            %>
                            </tbody>
                        </table>
            <%
                    } else {
            %>
                        <div class="no-users">
                            <h3>No users found</h3>
                            <p>No users have registered in the system yet.</p>
                        </div>
            <%
                    }
                } catch (Exception e) {
            %>
                    <div class="message error">Error loading users: <%= e.getMessage() %></div>
            <%
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception e) {}
                    try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                    try { if (conn != null) conn.close(); } catch (Exception e) {}
                }
            %>
        </div>
    </div>
</body>
</html>