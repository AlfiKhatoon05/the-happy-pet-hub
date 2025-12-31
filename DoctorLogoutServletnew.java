
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DoctorLogoutServletnew extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        response.sendRedirect("doctor-login.jsp?success=Logged out successfully");
    }
}