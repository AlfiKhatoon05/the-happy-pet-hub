import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           conn = DriverManager.getConnection(
                 "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");


            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            int row = pstmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (row > 0) {
                out.println("<h2>Registration successful!</h2>");
                out.println("<a href='login.jsp'>Login Now</a>");
            } else {
                out.println("<h2>Registration failed!</h2>");
                out.println("<a href='register.jsp'>Try Again</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
