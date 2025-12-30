
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserLogoutServletnew  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the session and invalidate it
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Redirect to login page with success message
        response.sendRedirect("user-login.jsp?success=Logged out successfully");
    }
}