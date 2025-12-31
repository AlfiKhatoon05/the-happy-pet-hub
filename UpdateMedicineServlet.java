import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateMedicineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String expiry_date = request.getParameter("expiry_date");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String sql = "UPDATE medicines SET name=?, type=?, expiry_date=TO_DATE(?, 'YYYY-MM-DD') WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, expiry_date);
            pstmt.setInt(4, Integer.parseInt(id));

            pstmt.executeUpdate();
            response.sendRedirect("manageMedicines.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(conn != null) conn.close(); } catch(Exception e) {}
        }
    }
}
