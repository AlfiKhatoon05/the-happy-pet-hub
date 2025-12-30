

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AdminLoginServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Check admin credentials
            String loginSQL = "SELECT * FROM admins WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(loginSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Login successful - store admin data in session
                HttpSession session = request.getSession();
                session.setAttribute("adminId", rs.getInt("admin_id"));
                session.setAttribute("adminUsername", rs.getString("username"));
                session.setAttribute("adminEmail", rs.getString("email"));
                session.setAttribute("adminFullName", rs.getString("full_name"));
                session.setAttribute("userType", "admin");

                // Redirect to admin dashboard
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                // Login failed
                response.sendRedirect("admin-login.jsp?error=Invalid username or password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin-login.jsp?error=Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin-login.jsp?error=System error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}