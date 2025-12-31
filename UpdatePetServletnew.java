import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/UpdatePetServletnew")
public class UpdatePetServletnew extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        // Check if user is logged in
        if (userId == null) {
            response.sendRedirect("user-login.jsp?error=Please login first");
            return;
        }
        
        // Get form parameters
        String petIdStr = request.getParameter("pet_id");
        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        String ageStr = request.getParameter("age");
        String weightStr = request.getParameter("weight");
        String color = request.getParameter("color");
        String medicalHistory = request.getParameter("medical_history");
        
        // Validate required fields
        if (petIdStr == null || petIdStr.trim().isEmpty() || 
            name == null || name.trim().isEmpty() || 
            species == null || species.trim().isEmpty()) {
            response.sendRedirect("edit-pet.jsp?id=" + petIdStr + "&error=Name and Species are required");
            return;
        }
        
        int petId = 0;
        int age = 0;
        double weight = 0.0;
        
        try {
            petId = Integer.parseInt(petIdStr);
            
            if (ageStr != null && !ageStr.trim().isEmpty()) {
                age = Integer.parseInt(ageStr);
                if (age < 0 || age > 50) {
                    response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Age must be between 0 and 50");
                    return;
                }
            }
            
            if (weightStr != null && !weightStr.trim().isEmpty()) {
                weight = Double.parseDouble(weightStr);
                if (weight < 0 || weight > 200) {
                    response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Weight must be between 0 and 200 kg");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("edit-pet.jsp?id=" + petIdStr + "&error=Invalid number format");
            return;
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Connect to database
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
            
            // First, verify that the pet belongs to the logged-in user
            String verifySql = "SELECT pet_id FROM petsnew WHERE pet_id = ? AND user_id = ?";
            pstmt = conn.prepareStatement(verifySql);
            pstmt.setInt(1, petId);
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (!rs.next()) {
                response.sendRedirect("pets-management.jsp?error=Pet not found or access denied");
                return;
            }
            rs.close();
            pstmt.close();
            
            // Update pet information - WITHOUT UPDATED_AT column
            String updateSql = "UPDATE petsnew SET " +
                              "name = ?, " +
                              "species = ?, " +
                              "breed = ?, " +
                              "age = ?, " +
                              "weight = ?, " +
                              "color = ?, " +
                              "medical_history = ? " +
                              "WHERE pet_id = ? AND user_id = ?";
            
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, name.trim());
            pstmt.setString(2, species);
            pstmt.setString(3, (breed != null && !breed.trim().isEmpty()) ? breed.trim() : null);
            pstmt.setInt(4, age);
            pstmt.setDouble(5, weight);
            pstmt.setString(6, (color != null && !color.trim().isEmpty()) ? color.trim() : null);
            pstmt.setString(7, (medicalHistory != null && !medicalHistory.trim().isEmpty()) ? medicalHistory.trim() : null);
            pstmt.setInt(8, petId);
            pstmt.setInt(9, userId);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Successful update
                response.sendRedirect("pets-management.jsp?success=Pet updated successfully!");
            } else {
                response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Failed to update pet");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Database driver not found");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("edit-pet.jsp?id=" + petId + "&error=Unexpected error: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect to doPost
        doPost(request, response);
    }
}