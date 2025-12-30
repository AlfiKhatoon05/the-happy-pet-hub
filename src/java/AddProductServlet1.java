import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddProductServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

            String sql = "INSERT INTO products (id, name, price, description) VALUES (product_seq.NEXTVAL, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, Double.parseDouble(price));
            pstmt.setString(3, description);

            pstmt.executeUpdate();
            response.sendRedirect("manageProducts.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(conn != null) conn.close(); } catch(Exception e) {}
        }
    }
}

