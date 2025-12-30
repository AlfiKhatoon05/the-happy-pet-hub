package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class doctor_002ddashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    // Check if doctor is logged in
    String doctorFullName = (String) session.getAttribute("doctorFullName");
    Integer doctorId = (Integer) session.getAttribute("doctorId");
    if (doctorFullName == null || doctorId == null) {
        response.sendRedirect("doctor-login.jsp?error=Please login first");
        return;
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Doctor Dashboard - Pet Management</title>\n");
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
      out.write("            background: linear-gradient(135deg, #2196F3, #1976D2);\n");
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
      out.write("            color: #2196F3;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .stat-label {\n");
      out.write("            color: #666;\n");
      out.write("            font-size: 1.1em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .appointments-section {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 30px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .section-header {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            border-bottom: 2px solid #f0f0f0;\n");
      out.write("            padding-bottom: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .section-title {\n");
      out.write("            color: #333;\n");
      out.write("            font-size: 1.8em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .appointments-table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: collapse;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .appointments-table th,\n");
      out.write("        .appointments-table td {\n");
      out.write("            padding: 12px 15px;\n");
      out.write("            text-align: left;\n");
      out.write("            border-bottom: 1px solid #ddd;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .appointments-table th {\n");
      out.write("            background: #f8f9fa;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .appointments-table tr:hover {\n");
      out.write("            background: #f8f9fa;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-badge {\n");
      out.write("            padding: 6px 12px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            font-size: 12px;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-pending {\n");
      out.write("            background: #fff3cd;\n");
      out.write("            color: #856404;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-confirmed {\n");
      out.write("            background: #d1ecf1;\n");
      out.write("            color: #0c5460;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-completed {\n");
      out.write("            background: #d4edda;\n");
      out.write("            color: #155724;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-cancelled {\n");
      out.write("            background: #f8d7da;\n");
      out.write("            color: #721c24;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-btn {\n");
      out.write("            padding: 6px 12px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            font-size: 12px;\n");
      out.write("            margin-right: 5px;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .confirm-btn {\n");
      out.write("            background: #28a745;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .confirm-btn:hover {\n");
      out.write("            background: #218838;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .complete-btn {\n");
      out.write("            background: #17a2b8;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .complete-btn:hover {\n");
      out.write("            background: #138496;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .no-appointments {\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 40px;\n");
      out.write("            color: #666;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-info {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 25px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-info h2 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            border-bottom: 2px solid #2196F3;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-grid {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-item {\n");
      out.write("            padding: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #555;\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-value {\n");
      out.write("            color: #333;\n");
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
      out.write("                👨‍⚕️ Welcome, Dr. ");
      out.print( doctorFullName );
      out.write("!\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"doctor-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"DoctorLogoutServletnew\">Logout</a>\n");
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
      out.write("        <div class=\"doctor-info\">\n");
      out.write("            <h2>Your Profile</h2>\n");
      out.write("            <div class=\"info-grid\">\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Full Name:</span>\n");
      out.write("                    <span class=\"info-value\">Dr. ");
      out.print( doctorFullName );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Specialization:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( session.getAttribute("doctorSpecialization") );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Email:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( session.getAttribute("doctorEmail") );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Phone:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( session.getAttribute("doctorPhone") );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">License Number:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( session.getAttribute("doctorLicense") );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
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
                    
                    // Count pending appointments
                    String pendingSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ? AND status = 'PENDING'";
                    pstmt = conn.prepareStatement(pendingSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int pendingCount = rs.next() ? rs.getInt("count") : 0;
                    rs.close();
                    pstmt.close();
                    
                    // Count confirmed appointments
                    String confirmedSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ? AND status = 'CONFIRMED'";
                    pstmt = conn.prepareStatement(confirmedSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int confirmedCount = rs.next() ? rs.getInt("count") : 0;
                    rs.close();
                    pstmt.close();
                    
                    // Count total appointments
                    String totalSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ?";
                    pstmt = conn.prepareStatement(totalSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int totalCount = rs.next() ? rs.getInt("count") : 0;
            
      out.write("\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( pendingCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Pending Appointments</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( confirmedCount );
      out.write("</div>\n");
      out.write("                <div class=\"stat-label\">Confirmed Appointments</div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"stat-card\">\n");
      out.write("                <div class=\"stat-number\">");
      out.print( totalCount );
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
      out.write("        <div class=\"appointments-section\">\n");
      out.write("            <div class=\"section-header\">\n");
      out.write("                <h2 class=\"section-title\">Your Appointments</h2>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            ");

                conn = null;
                pstmt = null;
                rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT a.*, p.name as pet_name, p.species, p.breed, u.full_name as owner_name " +
                                "FROM appointmentsnew a " +
                                "JOIN petsnew p ON a.pet_id = p.pet_id " +
                                "JOIN user202 u ON p.user_id = u.user_id " +
                                "WHERE a.doctor_id = ? " +
                                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
            
      out.write("\n");
      out.write("                        <table class=\"appointments-table\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>Appointment ID</th>\n");
      out.write("                                    <th>Pet & Owner</th>\n");
      out.write("                                    <th>Date & Time</th>\n");
      out.write("                                    <th>Reason</th>\n");
      out.write("                                    <th>Status</th>\n");
      out.write("                                    <th>Actions</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            <tbody>\n");
      out.write("            ");

                        do {
                            String status = rs.getString("status");
                            String statusClass = "status-" + status.toLowerCase();
                            java.util.Date appDate = rs.getDate("appointment_date");
                            String formattedDate = new java.text.SimpleDateFormat("MMM dd, yyyy").format(appDate);
            
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
      out.write(" - ");
      out.print( rs.getString("breed") != null ? rs.getString("breed") : "N/A" );
      out.write(")<br>\n");
      out.write("                                    Owner: ");
      out.print( rs.getString("owner_name") );
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
      out.write("                                <td>\n");
      out.write("                                    ");

                                        if ("PENDING".equals(status)) {
                                    
      out.write("\n");
      out.write("                                        <a href=\"ConfirmAppointmentServletnew?id=");
      out.print( rs.getInt("appointment_id") );
      out.write("\" \n");
      out.write("                                           class=\"action-btn confirm-btn\">Confirm</a>\n");
      out.write("                                    ");

                                        } else if ("CONFIRMED".equals(status)) {
                                    
      out.write("\n");
      out.write("                                        <a href=\"CompleteAppointmentServletnew?id=");
      out.print( rs.getInt("appointment_id") );
      out.write("\" \n");
      out.write("                                           class=\"action-btn complete-btn\">Complete</a>\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\n");
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
      out.write("                        <div class=\"no-appointments\">\n");
      out.write("                            <h3>No appointments found</h3>\n");
      out.write("                            <p>You don't have any appointments scheduled yet.</p>\n");
      out.write("                        </div>\n");
      out.write("            ");

                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
            
      out.write("\n");
      out.write("                    <div class=\"message error\">\n");
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
