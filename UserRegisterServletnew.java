

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UserRegisterServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                out.println("<script>alert('Passwords do not match!'); window.history.back();</script>");
                return;
            }

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Insert user
            String sql = "INSERT INTO user202 (user_id, username, password, email, full_name, phone, address) " +
                        "VALUES (seq_user_id.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, fullName);
            pstmt.setString(5, phone);
            pstmt.setString(6, address);

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("user-login.jsp?success=Registration successful! Please login.");
            } else {
                response.sendRedirect("user-register.jsp?error=Registration failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}