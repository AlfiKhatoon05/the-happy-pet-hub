
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ApproveDoctorServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if admin is logged in
        HttpSession session = request.getSession();
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId == null) {
            response.sendRedirect("admin-login.jsp?error=Please login first");
            return;
        }

        // Get doctor ID from request
        String doctorIdStr = request.getParameter("doctorId");
        
        if (doctorIdStr == null || doctorIdStr.isEmpty()) {
            response.sendRedirect("doctor-approval.jsp?error=Invalid doctor ID");
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

            // Update doctor status to APPROVED (1)
            String updateSql = "UPDATE doctors SET is_approved = 1 WHERE doctor_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setInt(1, Integer.parseInt(doctorIdStr));

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("doctor-approval.jsp?success=Doctor approved successfully!");
            } else {
                response.sendRedirect("doctor-approval.jsp?error=Failed to approve doctor");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("doctor-approval.jsp?error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}