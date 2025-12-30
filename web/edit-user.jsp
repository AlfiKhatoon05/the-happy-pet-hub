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
    
    String userId = request.getParameter("userId");
    if (userId == null) {
        response.sendRedirect("user-management.jsp?error=User ID required");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit User - Admin Panel</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        h1 { color: #333; margin-bottom: 20px; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #555; font-weight: bold; }
        input { width: 100%; padding: 10px; border: 2px solid #ddd; border-radius: 5px; font-size: 16px; }
        input:focus { border-color: #2196F3; outline: none; }
        .btn { padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; font-size: 16px; margin-right: 10px; }
        .update-btn { background: #4CAF50; color: white; }
        .update-btn:hover { background: #45a049; }
        .cancel-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }
        .cancel-btn:hover { background: #5a6268; }
        .message { padding: 10px; margin-bottom: 15px; border-radius: 5px; text-align: center; }
        .error { background: #ffebee; color: #c62828; }
        .success { background: #e8f5e8; color: #2e7d32; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit User</h1>
        
        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error != null) {
        %>
            <div class="message error"><%= error %></div>
        <%
            }
            if (success != null) {
        %>
            <div class="message success"><%= success %></div>
        <%
            }
        %>
        
        <%
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                
                String sql = "SELECT * FROM user202 WHERE user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(userId));
                rs = pstmt.executeQuery();
                
                if (rs.next()) {
        %>
                    <form action="UpdateUserServletnew" method="POST">
                        <input type="hidden" name="userId" value="<%= userId %>">
                        
                        <div class="form-group">
                            <label>Username:</label>
                            <input type="text" name="username" value="<%= rs.getString("username") %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Full Name:</label>
                            <input type="text" name="fullName" value="<%= rs.getString("full_name") %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="email" value="<%= rs.getString("email") %>" required>
                        </div>
                        
                        <div class="form-group">
                            <label>Phone:</label>
                            <input type="tel" name="phone" value="<%= rs.getString("phone") != null ? rs.getString("phone") : "" %>">
                        </div>
                        
                        <div class="form-group">
                            <label>Address:</label>
                            <input type="text" name="address" value="<%= rs.getString("address") != null ? rs.getString("address") : "" %>">
                        </div>
                        
                        <div style="text-align: center; margin-top: 20px;">
                            <button type="submit" class="btn update-btn">Update User</button>
                            <a href="user-management.jsp" class="cancel-btn">Cancel</a>
                        </div>
                    </form>
        <%
                } else {
        %>
                    <div class="message error">User not found!</div>
                    <div style="text-align: center; margin-top: 20px;">
                        <a href="user-management.jsp" class="cancel-btn">Back to User Management</a>
                    </div>
        <%
                }
            } catch (Exception e) {
        %>
                <div class="message error">Error: <%= e.getMessage() %></div>
        <%
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) {}
                try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }
        %>
    </div>
</body>
</html>