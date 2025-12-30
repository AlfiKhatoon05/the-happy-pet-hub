package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class admin_002ddashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');

    // Check if admin is logged in
    String adminFullName = (String) session.getAttribute("adminFullName");
    Integer adminId = (Integer) session.getAttribute("adminId");
    if (adminFullName == null || adminId == null) {
        response.sendRedirect("admin-login.jsp?error=Please login first");
        return;
    }
    
    // Initialize variables
    int usersCount = 0;
    int doctorsCount = 0;
    int pendingCount = 0;
    int petsCount = 0;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Admin Dashboard - Pet Management</title>\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background: #f5f5f5;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header {\n");
      out.write("            background: linear-gradient(135deg, #FF9800, #F57C00);\n");
      out.write("            color: white;\n");
      out.write("            padding: 20px;\n");
      out.write("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header-content {\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: 0 auto;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .welcome {\n");
      out.write("            font-size: 1.5em;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .nav-links {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .nav-links a {\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            padding: 8px 15px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .nav-links a:hover {\n");
      out.write("            background: rgba(255,255,255,0.2);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: 30px auto;\n");
      out.write("            padding: 0 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .stats-cards {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n");
      out.write("            gap: 20px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .stat-card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            padding: 25px;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0 3px 10px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .stat-number {\n");
      out.write("            font-size: 2.5em;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #FF9800;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .stat-label {\n");
      out.write("            color: #666;\n");
      out.write("            font-size: 1.1em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .admin-sections {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));\n");
      out.write("            gap: 25px;\n");
      out.write("            margin-top: 30px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .section-card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 30px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .section-card h3 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            font-size: 1.4em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .section-card p {\n");
      out.write("            color: #666;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            line-height: 1.5;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            display: inline-block;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            background: #FF9800;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn:hover {\n");
      out.write("            background: #F57C00;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pending-alert {\n");
      out.write("            background: #ffebee;\n");
      out.write("            color: #c62828;\n");
      out.write("            padding: 10px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin-top: 10px;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .message {\n");
      out.write("            padding: 15px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .error {\n");
      out.write("            background: #ffebee;\n");
      out.write("            color: #c62828;\n");
      out.write("            border: 1px solid #ffcdd2;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .success {\n");
      out.write("            background: #e8f5e8;\n");
      out.write("            color: #2e7d32;\n");
      out.write("            border: 1px solid #c8e6c9;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">\n");
      out.write("                ⚙️ Admin Dashboard - Welcome, ");
      out.print( adminFullName );
      out.write("!\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"AdminLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");

            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error != null) {
        
      out.write("\n");
      out.write("            <div class=\"message error\">\n");
      out.write("                ");
      out.print( error );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        ");

            }
            if (success != null) {
        
      out.write("\n");
      out.write("            <div class=\"message success\">\n");
      out.write("                ");
      out.print( success );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        <div class=\"stats-cards\">\n");
      out.write("            ");

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Count total users
                    String usersSQL = "SELECT COUNT(*) as count FROM user202";
                    pstmt = conn.prepareStatement(usersSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) usersCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count total doctors
                    String doctorsSQL = "SELECT COUNT(*) as count FROM doctors";
                    pstmt = conn.prepareStatement(doctorsSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) doctorsCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count pending doctors
                    String pendingSQL = "SELECT COUNT(*) as count FROM doctors WHERE is_approved = 0";
                    pstmt = conn.prepareStatement(pendingSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) pendingCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count total pets
                    String petsSQL = "SELECT COUNT(*) as count FROM petsnew";
                    pstmt = conn.prepareStatement(petsSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) petsCount = rs.getInt("count");
            
      out.write("\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( usersCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Users</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( doctorsCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Doctors</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( pendingCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Pending Approvals</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( petsCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Pets</div>\n");
      out.write("            </div>\n");
      out.write("            ");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception e) {}
                    try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                    try { if (conn != null) conn.close(); } catch (Exception e) {}
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"admin-sections\">\n");
      out.write("            <div class=\"section-card\">\n");
      out.write("                <h3>👨‍⚕️ Doctor Management</h3>\n");
      out.write("                <p>Approve or reject doctor registrations and manage doctor accounts</p>\n");
      out.write("                <a href=\"doctor-approval.jsp\" class=\"btn\">Manage Doctors</a>\n");
      out.write("                ");

                    if (pendingCount > 0) {
                
      out.write("\n");
      out.write("                    <div class=\"pending-alert\">\n");
      out.write("                        ⚠️ ");
      out.print( pendingCount );
      out.write(" doctors pending approval\n");
      out.write("                    </div>\n");
      out.write("                ");

                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"section-card\">\n");
      out.write("                <h3>👥 User Management</h3>\n");
      out.write("                <p>View all registered users, their pets, and manage user accounts</p>\n");
      out.write("                <a href=\"user-management.jsp\" class=\"btn\">Manage Users</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"section-card\">\n");
      out.write("                <h3>📊 System Reports</h3>\n");
      out.write("                <p>View system statistics, appointment reports, and generate analytics</p>\n");
      out.write("                <a href=\"system-reports.jsp\" class=\"btn\">View Reports</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"section-card\">\n");
      out.write("                <h3>📅 Appointments</h3>\n");
      out.write("                <p>Monitor all appointments across the system and view booking trends</p>\n");
      out.write("                <a href=\"appointment-management.jsp\" class=\"btn\">View Appointments</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
