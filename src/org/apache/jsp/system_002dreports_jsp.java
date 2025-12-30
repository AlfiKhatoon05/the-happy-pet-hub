package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class system_002dreports_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>System Reports - Admin Panel</title>\n");
      out.write("    <style>\n");
      out.write("        * { margin: 0; padding: 0; box-sizing: border-box; }\n");
      out.write("        body { font-family: Arial, sans-serif; background: #f5f5f5; }\n");
      out.write("        .header { background: linear-gradient(135deg, #FF9800, #F57C00); color: white; padding: 20px; }\n");
      out.write("        .header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }\n");
      out.write("        .welcome { font-size: 1.5em; font-weight: bold; }\n");
      out.write("        .nav-links { display: flex; gap: 20px; }\n");
      out.write("        .nav-links a { color: white; text-decoration: none; padding: 8px 15px; border-radius: 20px; transition: background 0.3s ease; }\n");
      out.write("        .nav-links a:hover { background: rgba(255,255,255,0.2); }\n");
      out.write("        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }\n");
      out.write("        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }\n");
      out.write("        .page-title { color: #333; font-size: 2em; }\n");
      out.write("        .back-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }\n");
      out.write("        .back-btn:hover { background: #5a6268; }\n");
      out.write("        .stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-bottom: 30px; }\n");
      out.write("        .stat-card { background: white; border-radius: 10px; padding: 25px; text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.1); }\n");
      out.write("        .stat-number { font-size: 2.5em; font-weight: bold; color: #FF9800; margin-bottom: 10px; }\n");
      out.write("        .stat-label { color: #666; font-size: 1.1em; }\n");
      out.write("        .reports-section { background: white; border-radius: 15px; padding: 30px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }\n");
      out.write("        .section-title { color: #333; margin-bottom: 20px; font-size: 1.5em; border-bottom: 2px solid #FF9800; padding-bottom: 10px; }\n");
      out.write("        .report-item { margin-bottom: 20px; padding: 15px; background: #f8f9fa; border-radius: 8px; }\n");
      out.write("        .report-label { font-weight: bold; color: #555; margin-bottom: 5px; }\n");
      out.write("        .report-value { color: #333; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">📊 System Reports</div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"AdminLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">System Reports & Analytics</h1>\n");
      out.write("            <a href=\"admin-dashboard.jsp\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"stats-grid\">\n");
      out.write("            ");

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Total Users
                    String usersSQL = "SELECT COUNT(*) as count FROM user202";
                    pstmt = conn.prepareStatement(usersSQL);
                    rs = pstmt.executeQuery();
                    int totalUsers = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Doctors
                    String doctorsSQL = "SELECT COUNT(*) as count FROM doctors";
                    pstmt = conn.prepareStatement(doctorsSQL);
                    rs = pstmt.executeQuery();
                    int totalDoctors = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Pets
                    String petsSQL = "SELECT COUNT(*) as count FROM petsnew";
                    pstmt = conn.prepareStatement(petsSQL);
                    rs = pstmt.executeQuery();
                    int totalPets = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Appointments
                    String appointmentsSQL = "SELECT COUNT(*) as count FROM appointmentsnew";
                    pstmt = conn.prepareStatement(appointmentsSQL);
                    rs = pstmt.executeQuery();
                    int totalAppointments = rs.next() ? rs.getInt("count") : 0;
            
      out.write("\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalUsers );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Users</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalDoctors );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Doctors</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalPets );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Pets</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalAppointments );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Appointments</div>\n");
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
      out.write("        <div class=\"reports-section\">\n");
      out.write("            <h2 class=\"section-title\">System Overview</h2>\n");
      out.write("            ");

                conn = null; pstmt = null; rs = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Appointment Status Breakdown
                    String statusSQL = "SELECT status, COUNT(*) as count FROM appointmentsnew GROUP BY status";
                    pstmt = conn.prepareStatement(statusSQL);
                    rs = pstmt.executeQuery();
            
      out.write("\n");
      out.write("            <div class=\"report-item\">\n");
      out.write("                <div class=\"report-label\">Appointment Status Breakdown:</div>\n");
      out.write("                ");

                    while (rs.next()) {
                
      out.write("\n");
      out.write("                    <div class=\"report-value\">- ");
      out.print( rs.getString("status") );
      out.write(':');
      out.write(' ');
      out.print( rs.getInt("count") );
      out.write(" appointments</div>\n");
      out.write("                ");

                    }
                    rs.close(); pstmt.close();
                    
                    // Recent Registrations (last 7 days)
                    String recentSQL = "SELECT COUNT(*) as count FROM user202 WHERE created_date >= SYSDATE - 7";
                    pstmt = conn.prepareStatement(recentSQL);
                    rs = pstmt.executeQuery();
                    int recentUsers = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"report-item\">\n");
      out.write("                <div class=\"report-label\">New Users (Last 7 days):</div>\n");
      out.write("                <div class=\"report-value\">");
      out.print( recentUsers );
      out.write(" new registrations</div>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"report-item\">\n");
      out.write("                <div class=\"report-label\">Doctor Approval Status:</div>\n");
      out.write("                ");

                    // Doctor approval stats
                    String doctorStatsSQL = "SELECT is_approved, COUNT(*) as count FROM doctors GROUP BY is_approved";
                    pstmt = conn.prepareStatement(doctorStatsSQL);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()) {
                        int status = rs.getInt("is_approved");
                        String statusText = status == 0 ? "Pending" : (status == 1 ? "Approved" : "Rejected");
                
      out.write("\n");
      out.write("                    <div class=\"report-value\">- ");
      out.print( statusText );
      out.write(':');
      out.write(' ');
      out.print( rs.getInt("count") );
      out.write(" doctors</div>\n");
      out.write("                ");

                    }
                
      out.write("\n");
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
