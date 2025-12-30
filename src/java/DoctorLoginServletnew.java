
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DoctorLoginServletnew extends HttpServlet {
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

            // Check doctor credentials and approval status
            String loginSQL = "SELECT * FROM doctors WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(loginSQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int isApproved = rs.getInt("is_approved");
                
                // Check if doctor is approved
                if (isApproved == 1) {
                    // Login successful - store doctor data in session
                    HttpSession session = request.getSession();
                    session.setAttribute("doctorId", rs.getInt("doctor_id"));
                    session.setAttribute("doctorUsername", rs.getString("username"));
                    session.setAttribute("doctorEmail", rs.getString("email"));
                    session.setAttribute("doctorFullName", rs.getString("full_name"));
                    session.setAttribute("doctorSpecialization", rs.getString("specialization"));
                    session.setAttribute("doctorPhone", rs.getString("phone"));
                    session.setAttribute("doctorLicense", rs.getString("license_number"));
                    session.setAttribute("userType", "doctor");

                    // Redirect to doctor dashboard
                    response.sendRedirect("doctor-dashboard.jsp");
                } else if (isApproved == 0) {
                    // Doctor not approved yet
                    response.sendRedirect("doctor-login.jsp?error=Your account is pending admin approval. Please wait.");
                } else {
                    // Doctor rejected
                    response.sendRedirect("doctor-login.jsp?error=Your account has been rejected. Please contact admin.");
                }
            } else {
                // Login failed
                response.sendRedirect("doctor-login.jsp?error=Invalid username or password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("doctor-login.jsp?error=Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("doctor-login.jsp?error=System error: " + e.getMessage());
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