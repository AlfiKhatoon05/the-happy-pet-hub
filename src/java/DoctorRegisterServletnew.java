

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DoctorRegisterServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String specialization = request.getParameter("specialization");
        String phone = request.getParameter("phone");
        String licenseNumber = request.getParameter("licenseNumber");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Validate passwords match
            if (!password.equals(confirmPassword)) {
                response.sendRedirect("doctor-register.jsp?error=Passwords do not match");
                return;
            }

            // Validate password length
            if (password.length() < 6) {
                response.sendRedirect("doctor-register.jsp?error=Password must be at least 6 characters long");
                return;
            }

            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Check if username or email already exists
            String checkSQL = "SELECT doctor_id FROM doctors WHERE username = ? OR email = ? OR license_number = ?";
            pstmt = conn.prepareStatement(checkSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, licenseNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                response.sendRedirect("doctor-register.jsp?error=Username, Email or License Number already exists");
                return;
            }

            // Close previous result set and statement
            rs.close();
            pstmt.close();

            // Insert new doctor (default status: 0 = Pending Approval)
            String insertSQL = "INSERT INTO doctors (doctor_id, username, password, email, full_name, specialization, phone, license_number, is_approved) " +
                             "VALUES (seq_doctor_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, 0)";
            
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, fullName);
            pstmt.setString(5, specialization);
            pstmt.setString(6, phone);
            pstmt.setString(7, licenseNumber);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("doctor-login.jsp?success=Registration submitted! Please wait for admin approval.");
            } else {
                response.sendRedirect("doctor-register.jsp?error=Registration failed. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("doctor-register.jsp?error=Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("doctor-register.jsp?error=System error: " + e.getMessage());
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