
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddPetServletnew extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user ID from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("user-login.jsp?error=Please login first");
            return;
        }

        // Get form parameters
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        String ageStr = request.getParameter("age");
        String weightStr = request.getParameter("weight");
        String color = request.getParameter("color");
        String medicalHistory = request.getParameter("medicalHistory");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            // Insert pet into petsnew table
            String sql = "INSERT INTO petsnew (pet_id, user_id, name, species, breed, age, weight, color, medical_history) " +
                        "VALUES (seq_pet_id.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, species);
            pstmt.setString(4, breed);
            
            // Handle age (can be null)
            if (ageStr != null && !ageStr.trim().isEmpty()) {
                pstmt.setInt(5, Integer.parseInt(ageStr));
            } else {
                pstmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            // Handle weight (can be null)
            if (weightStr != null && !weightStr.trim().isEmpty()) {
                pstmt.setDouble(6, Double.parseDouble(weightStr));
            } else {
                pstmt.setNull(6, java.sql.Types.DOUBLE);
            }
            
            pstmt.setString(7, color);
            pstmt.setString(8, medicalHistory);

            int row = pstmt.executeUpdate();

            if (row > 0) {
                response.sendRedirect("pets-management.jsp?success=Pet added successfully!");
            } else {
                response.sendRedirect("pets-add.jsp?error=Failed to add pet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("pets-add.jsp?error=Database error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}