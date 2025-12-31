
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class CancelAppointmentServletnew extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user ID from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("user-login.jsp?error=Please login first");
            return;
        }

        // Get appointment ID from request
        String appointmentIdStr = request.getParameter("id");
        
        if (appointmentIdStr == null || appointmentIdStr.isEmpty()) {
            response.sendRedirect("view-appointments.jsp?error=Invalid appointment ID");
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

            // First check if the appointment belongs to the user
            String checkSql = "SELECT a.appointment_id " +
                             "FROM appointmentsnew a " +
                             "JOIN petsnew p ON a.pet_id = p.pet_id " +
                             "WHERE a.appointment_id = ? AND p.user_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, Integer.parseInt(appointmentIdStr));
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                response.sendRedirect("view-appointments.jsp?error=Appointment not found or access denied");
                return;
            }
            
            // Close the first statement
            pstmt.close();

            // Update appointment status to CANCELLED
            String updateSql = "UPDATE appointmentsnew SET status = 'CANCELLED' WHERE appointment_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setInt(1, Integer.parseInt(appointmentIdStr));

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("view-appointments.jsp?success=Appointment cancelled successfully!");
            } else {
                response.sendRedirect("view-appointments.jsp?error=Failed to cancel appointment");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view-appointments.jsp?error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}