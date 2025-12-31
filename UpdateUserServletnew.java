

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateUserServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if admin is logged in
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect("admin-login.jsp?error=Please login first");
            return;
        }

        // Get form parameters
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Update user in database
            String updateSql = "UPDATE user202 SET username = ?, full_name = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, username);
            pstmt.setString(2, fullName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, address);
            pstmt.setInt(6, Integer.parseInt(userId));

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("user-management.jsp?success=User updated successfully!");
            } else {
                response.sendRedirect("edit-user.jsp?userId=" + userId + "&error=Failed to update user");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("edit-user.jsp?userId=" + userId + "&error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}