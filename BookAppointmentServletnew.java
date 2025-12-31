import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BookAppointmentServletnew extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("user-login.jsp?error=Please login first");
            return;
        }

        String petIdStr = request.getParameter("petId");
        String doctorIdStr = request.getParameter("doctorId");
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String reason = request.getParameter("reason");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // INSERT appointment (NO PAYMENT DATA)
            String sql =
                "INSERT INTO appointmentsnew (" +
                "appointment_id, user_id, pet_id, doctor_id, " +
                "appointment_date, appointment_time, reason, appointment_status" +
                ") VALUES (" +
                "appointments_seq.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?" +
                ")";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, Integer.parseInt(petIdStr));
            pstmt.setInt(3, Integer.parseInt(doctorIdStr));
            pstmt.setString(4, appointmentDate);
            pstmt.setString(5, appointmentTime);
            pstmt.setString(6, reason);
            pstmt.setString(7, "SCHEDULED");

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                response.sendRedirect(
                    "book-appointment.jsp?success=" +
                    java.net.URLEncoder.encode(
                        "Appointment booked successfully!", "UTF-8"
                    )
                );
            } else {
                response.sendRedirect(
                    "book-appointment.jsp?error=Failed to book appointment"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(
                "book-appointment.jsp?error=Database error"
            );
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
