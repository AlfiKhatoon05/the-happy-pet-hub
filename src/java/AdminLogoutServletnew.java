

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminLogoutServletnew extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        response.sendRedirect("admin-login.jsp?success=Logged out successfully");
    }
}