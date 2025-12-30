

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CompleteAppointmentServletnew extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get doctor ID from session
        HttpSession session = request.getSession();
        Integer doctorId = (Integer) session.getAttribute("doctorId");
        
        if (doctorId == null) {
            response.sendRedirect("doctor-login.jsp?error=Please login first");
            return;
        }

        // Get appointment ID from request
        String appointmentIdStr = request.getParameter("id");
        
        if (appointmentIdStr == null || appointmentIdStr.isEmpty()) {
            response.sendRedirect("doctor-dashboard.jsp?error=Invalid appointment ID");
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

            // First check if the appointment belongs to the doctor
            String checkSql = "SELECT appointment_id FROM appointmentsnew WHERE appointment_id = ? AND doctor_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, Integer.parseInt(appointmentIdStr));
            pstmt.setInt(2, doctorId);
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                response.sendRedirect("doctor-dashboard.jsp?error=Appointment not found or access denied");
                return;
            }
            
            // Close the first statement
            pstmt.close();

            // Update appointment status to COMPLETED
            String updateSql = "UPDATE appointmentsnew SET status = 'COMPLETED' WHERE appointment_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setInt(1, Integer.parseInt(appointmentIdStr));

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("doctor-dashboard.jsp?success=Appointment marked as completed!");
            } else {
                response.sendRedirect("doctor-dashboard.jsp?error=Failed to complete appointment");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("doctor-dashboard.jsp?error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}