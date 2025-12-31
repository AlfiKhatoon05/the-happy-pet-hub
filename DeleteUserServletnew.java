import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteUserServletnew extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if admin is logged in
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect("admin-login.jsp?error=Please login first");
            return;
        }

        // Get user ID from request
        String userIdStr = request.getParameter("userId");
        
        if (userIdStr == null || userIdStr.isEmpty()) {
            response.sendRedirect("user-management.jsp?error=Invalid user ID");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Delete user from database
            String deleteSql = "DELETE FROM user202 WHERE user_id = ?";
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setInt(1, Integer.parseInt(userIdStr));

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("user-management.jsp?success=User deleted successfully!");
            } else {
                response.sendRedirect("user-management.jsp?error=Failed to delete user");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("user-management.jsp?error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}