package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public final class appointment_002dmanagement_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");

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
      out.write("    <title>Appointment Management - Admin Panel</title>\n");
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
      out.write("        .appointments-table { width: 100%; background: white; border-radius: 15px; padding: 25px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }\n");
      out.write("        .appointments-table table { width: 100%; border-collapse: collapse; }\n");
      out.write("        .appointments-table th, .appointments-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }\n");
      out.write("        .appointments-table th { background: #f8f9fa; font-weight: bold; color: #333; }\n");
      out.write("        .appointments-table tr:hover { background: #f8f9fa; }\n");
      out.write("        .status-badge { padding: 6px 12px; border-radius: 20px; font-size: 12px; font-weight: bold; }\n");
      out.write("        .status-pending { background: #fff3cd; color: #856404; }\n");
      out.write("        .status-confirmed { background: #d1ecf1; color: #0c5460; }\n");
      out.write("        .status-completed { background: #d4edda; color: #155724; }\n");
      out.write("        .status-cancelled { background: #f8d7da; color: #721c24; }\n");
      out.write("        .stats-cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }\n");
      out.write("        .stat-card { background: white; border-radius: 10px; padding: 25px; text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.1); }\n");
      out.write("        .stat-number { font-size: 2.5em; font-weight: bold; color: #FF9800; margin-bottom: 10px; }\n");
      out.write("        .stat-label { color: #666; font-size: 1.1em; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">📅 Appointment Management</div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"AdminLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">All Appointments</h1>\n");
      out.write("            <a href=\"admin-dashboard.jsp\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"stats-cards\">\n");
      out.write("            ");

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                int totalAppointments = 0;
                int todayAppointments = 0;
                int pendingAppointments = 0;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Total appointments
                    String totalSQL = "SELECT COUNT(*) as count FROM appointmentsnew";
                    pstmt = conn.prepareStatement(totalSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) totalAppointments = rs.getInt("count");
                    rs.close(); pstmt.close();
                    
                    // Today's appointments
                    String todaySQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE TRUNC(appointment_date) = TRUNC(SYSDATE)";
                    pstmt = conn.prepareStatement(todaySQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) todayAppointments = rs.getInt("count");
                    rs.close(); pstmt.close();
                    
                    // Pending appointments
                    String pendingSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE status = 'PENDING'";
                    pstmt = conn.prepareStatement(pendingSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) pendingAppointments = rs.getInt("count");
            
      out.write("\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalAppointments );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Total Appointments</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( todayAppointments );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Today's Appointments</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( pendingAppointments );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Pending Approval</div>\n");
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
      out.write("        <div class=\"appointments-table\">\n");
      out.write("            ");

                conn = null; pstmt = null; rs = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT a.*, p.name as pet_name, p.species, u.full_name as owner_name, " +
                                "d.full_name as doctor_name, d.specialization " +
                                "FROM appointmentsnew a " +
                                "JOIN petsnew p ON a.pet_id = p.pet_id " +
                                "JOIN user202 u ON p.user_id = u.user_id " +
                                "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
            
      out.write("\n");
      out.write("                        <table>\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>ID</th>\n");
      out.write("                                    <th>Pet & Owner</th>\n");
      out.write("                                    <th>Doctor</th>\n");
      out.write("                                    <th>Date & Time</th>\n");
      out.write("                                    <th>Reason</th>\n");
      out.write("                                    <th>Status</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("            ");

                        do {
                            String status = rs.getString("status");
                            String statusClass = "status-" + status.toLowerCase();
                            java.util.Date appDate = rs.getDate("appointment_date");
                            String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(appDate);
            
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>#");
      out.print( rs.getInt("appointment_id") );
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <strong>");
      out.print( rs.getString("pet_name") );
      out.write("</strong><br>\n");
      out.write("                                    (");
      out.print( rs.getString("species") );
      out.write(")<br>\n");
      out.write("                                    Owner: ");
      out.print( rs.getString("owner_name") );
      out.write("\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    Dr. ");
      out.print( rs.getString("doctor_name") );
      out.write("<br>\n");
      out.write("                                    ");
      out.print( rs.getString("specialization") );
      out.write("\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    ");
      out.print( formattedDate );
      out.write("<br>\n");
      out.write("                                    ");
      out.print( rs.getString("appointment_time") );
      out.write("\n");
      out.write("                                </td>\n");
      out.write("                                <td>");
      out.print( rs.getString("reason") );
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <span class=\"status-badge ");
      out.print( statusClass );
      out.write("\">\n");
      out.write("                                        ");
      out.print( status );
      out.write("\n");
      out.write("                                    </span>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("            ");

                        } while (rs.next());
            
      out.write("\n");
      out.write("                            </tbody>\n");
      out.write("                        </table>\n");
      out.write("            ");

                    } else {
            
      out.write("\n");
      out.write("                        <div style=\"text-align: center; padding: 40px; color: #666;\">\n");
      out.write("                            <h3>No appointments found</h3>\n");
      out.write("                            <p>No appointments have been booked in the system yet.</p>\n");
      out.write("                        </div>\n");
      out.write("            ");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
            
      out.write("\n");
      out.write("                    <div style=\"background: #ffebee; color: #c62828; padding: 15px; border-radius: 8px; text-align: center;\">\n");
      out.write("                        Error loading appointments: ");
      out.print( e.getMessage() );
      out.write("\n");
      out.write("                    </div>\n");
      out.write("            ");

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
