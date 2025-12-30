package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class doctor_002dapproval_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Doctor Approval - Admin Panel</title>\n");
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
      out.write("        .page-header {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .page-title {\n");
      out.write("            color: #333;\n");
      out.write("            font-size: 2em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctors-list {\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction: column;\n");
      out.write("            gap: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 25px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("            border-left: 5px solid #FF9800;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-header {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            border-bottom: 2px solid #f0f0f0;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-name {\n");
      out.write("            color: #333;\n");
      out.write("            font-size: 1.4em;\n");
      out.write("            font-weight: bold;\n");
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
      out.write("        .status-approved {\n");
      out.write("            background: #d4edda;\n");
      out.write("            color: #155724;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .status-rejected {\n");
      out.write("            background: #f8d7da;\n");
      out.write("            color: #721c24;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .doctor-details {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n");
      out.write("            gap: 15px;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-item {\n");
      out.write("            padding: 8px 0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #555;\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-value {\n");
      out.write("            color: #333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-buttons {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 10px;\n");
      out.write("            margin-top: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            padding: 8px 20px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .approve-btn {\n");
      out.write("            background: #28a745;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .approve-btn:hover {\n");
      out.write("            background: #218838;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .reject-btn {\n");
      out.write("            background: #dc3545;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .reject-btn:hover {\n");
      out.write("            background: #c82333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .back-btn {\n");
      out.write("            background: #6c757d;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .back-btn:hover {\n");
      out.write("            background: #5a6268;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .no-doctors {\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 50px;\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .no-doctors h3 {\n");
      out.write("            color: #666;\n");
      out.write("            margin-bottom: 20px;\n");
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
      out.write("        \n");
      out.write("        .tabs {\n");
      out.write("            display: flex;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-bottom: 2px solid #ddd;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .tab {\n");
      out.write("            padding: 12px 25px;\n");
      out.write("            background: #f8f9fa;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("            margin-right: 5px;\n");
      out.write("            border-radius: 5px 5px 0 0;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .tab.active {\n");
      out.write("            background: #FF9800;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .tab-content {\n");
      out.write("            display: none;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .tab-content.active {\n");
      out.write("            display: block;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">\n");
      out.write("                👨‍⚕️ Doctor Approval\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"admin-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"AdminLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">Doctor Management</h1>\n");
      out.write("            <a href=\"admin-dashboard.jsp\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
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
      out.write("        <div class=\"tabs\">\n");
      out.write("            <button class=\"tab active\" onclick=\"showTab('pending')\">Pending Approval</button>\n");
      out.write("            <button class=\"tab\" onclick=\"showTab('approved')\">Approved Doctors</button>\n");
      out.write("            <button class=\"tab\" onclick=\"showTab('all')\">All Doctors</button>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- Pending Doctors Tab -->\n");
      out.write("        <div id=\"pending-tab\" class=\"tab-content active\">\n");
      out.write("            <div class=\"doctors-list\">\n");
      out.write("                ");

                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get pending doctors
                        String sql = "SELECT * FROM doctors WHERE is_approved = 0 ORDER BY created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasPending = false;
                        
                        while (rs.next()) {
                            hasPending = true;
                
      out.write("\n");
      out.write("                            <div class=\"doctor-card\">\n");
      out.write("                                <div class=\"doctor-header\">\n");
      out.write("                                    <div class=\"doctor-name\">Dr. ");
      out.print( rs.getString("full_name") );
      out.write("</div>\n");
      out.write("                                    <span class=\"status-badge status-pending\">PENDING</span>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                <div class=\"doctor-details\">\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Username:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("username") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Email:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("email") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Specialization:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("specialization") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Phone:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("phone") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">License Number:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("license_number") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Registered Date:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getDate("created_date") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                <div class=\"action-buttons\">\n");
      out.write("                                    <form action=\"ApproveDoctorServletnew\" method=\"POST\" style=\"display: inline;\">\n");
      out.write("                                        <input type=\"hidden\" name=\"doctorId\" value=\"");
      out.print( rs.getInt("doctor_id") );
      out.write("\">\n");
      out.write("                                        <button type=\"submit\" class=\"btn approve-btn\">Approve</button>\n");
      out.write("                                    </form>\n");
      out.write("                                    <form action=\"RejectDoctorServletnew\" method=\"POST\" style=\"display: inline;\">\n");
      out.write("                                        <input type=\"hidden\" name=\"doctorId\" value=\"");
      out.print( rs.getInt("doctor_id") );
      out.write("\">\n");
      out.write("                                        <button type=\"submit\" class=\"btn reject-btn\" onclick=\"return confirm('Are you sure you want to reject this doctor?')\">Reject</button>\n");
      out.write("                                    </form>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                        if (!hasPending) {
                
      out.write("\n");
      out.write("                            <div class=\"no-doctors\">\n");
      out.write("                                <h3>No pending doctors for approval</h3>\n");
      out.write("                                <p>All doctor registrations have been processed.</p>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                
      out.write("\n");
      out.write("                        <div class=\"message error\">\n");
      out.write("                            Error loading pending doctors: ");
      out.print( e.getMessage() );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                ");

                    } finally {
                        try { if (rs != null) rs.close(); } catch (Exception e) {}
                        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                        try { if (conn != null) conn.close(); } catch (Exception e) {}
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- Approved Doctors Tab -->\n");
      out.write("        <div id=\"approved-tab\" class=\"tab-content\">\n");
      out.write("            <div class=\"doctors-list\">\n");
      out.write("                ");

                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get approved doctors
                        String sql = "SELECT * FROM doctors WHERE is_approved = 1 ORDER BY created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasApproved = false;
                        
                        while (rs.next()) {
                            hasApproved = true;
                
      out.write("\n");
      out.write("                            <div class=\"doctor-card\">\n");
      out.write("                                <div class=\"doctor-header\">\n");
      out.write("                                    <div class=\"doctor-name\">Dr. ");
      out.print( rs.getString("full_name") );
      out.write("</div>\n");
      out.write("                                    <span class=\"status-badge status-approved\">APPROVED</span>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                <div class=\"doctor-details\">\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Username:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("username") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Email:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("email") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Specialization:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("specialization") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Phone:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("phone") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">License Number:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("license_number") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Approved Date:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getDate("created_date") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                        if (!hasApproved) {
                
      out.write("\n");
      out.write("                            <div class=\"no-doctors\">\n");
      out.write("                                <h3>No approved doctors</h3>\n");
      out.write("                                <p>No doctors have been approved yet.</p>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                
      out.write("\n");
      out.write("                        <div class=\"message error\">\n");
      out.write("                            Error loading approved doctors: ");
      out.print( e.getMessage() );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                ");

                    } finally {
                        try { if (rs != null) rs.close(); } catch (Exception e) {}
                        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                        try { if (conn != null) conn.close(); } catch (Exception e) {}
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- All Doctors Tab -->\n");
      out.write("        <div id=\"all-tab\" class=\"tab-content\">\n");
      out.write("            <div class=\"doctors-list\">\n");
      out.write("                ");

                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get all doctors
                        String sql = "SELECT * FROM doctors ORDER BY is_approved, created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasDoctors = false;
                        
                        while (rs.next()) {
                            hasDoctors = true;
                            int approvalStatus = rs.getInt("is_approved");
                            String statusClass = "";
                            String statusText = "";
                            
                            if (approvalStatus == 0) {
                                statusClass = "status-pending";
                                statusText = "PENDING";
                            } else if (approvalStatus == 1) {
                                statusClass = "status-approved";
                                statusText = "APPROVED";
                            } else {
                                statusClass = "status-rejected";
                                statusText = "REJECTED";
                            }
                
      out.write("\n");
      out.write("                            <div class=\"doctor-card\">\n");
      out.write("                                <div class=\"doctor-header\">\n");
      out.write("                                    <div class=\"doctor-name\">Dr. ");
      out.print( rs.getString("full_name") );
      out.write("</div>\n");
      out.write("                                    <span class=\"status-badge ");
      out.print( statusClass );
      out.write('"');
      out.write('>');
      out.print( statusText );
      out.write("</span>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                <div class=\"doctor-details\">\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Username:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("username") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Email:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("email") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Specialization:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("specialization") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Phone:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("phone") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">License Number:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getString("license_number") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Status:</span>\n");
      out.write("                                        <span class=\"detail-value ");
      out.print( statusClass );
      out.write("\" style=\"padding: 4px 8px; border-radius: 10px;\">");
      out.print( statusText );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"detail-item\">\n");
      out.write("                                        <span class=\"detail-label\">Registered Date:</span>\n");
      out.write("                                        <span class=\"detail-value\">");
      out.print( rs.getDate("created_date") );
      out.write("</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                \n");
      out.write("                                ");
 if (approvalStatus == 0) { 
      out.write("\n");
      out.write("                                    <div class=\"action-buttons\">\n");
      out.write("                                        <form action=\"ApproveDoctorServletnew\" method=\"POST\" style=\"display: inline;\">\n");
      out.write("                                            <input type=\"hidden\" name=\"doctorId\" value=\"");
      out.print( rs.getInt("doctor_id") );
      out.write("\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn approve-btn\">Approve</button>\n");
      out.write("                                        </form>\n");
      out.write("                                        <form action=\"RejectDoctorServletnew\" method=\"POST\" style=\"display: inline;\">\n");
      out.write("                                            <input type=\"hidden\" name=\"doctorId\" value=\"");
      out.print( rs.getInt("doctor_id") );
      out.write("\">\n");
      out.write("                                            <button type=\"submit\" class=\"btn reject-btn\" onclick=\"return confirm('Are you sure you want to reject this doctor?')\">Reject</button>\n");
      out.write("                                        </form>\n");
      out.write("                                    </div>\n");
      out.write("                                ");
 } 
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                        if (!hasDoctors) {
                
      out.write("\n");
      out.write("                            <div class=\"no-doctors\">\n");
      out.write("                                <h3>No doctors found</h3>\n");
      out.write("                                <p>No doctors have registered in the system yet.</p>\n");
      out.write("                            </div>\n");
      out.write("                ");

                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                
      out.write("\n");
      out.write("                        <div class=\"message error\">\n");
      out.write("                            Error loading all doctors: ");
      out.print( e.getMessage() );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                ");

                    } finally {
                        try { if (rs != null) rs.close(); } catch (Exception e) {}
                        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                        try { if (conn != null) conn.close(); } catch (Exception e) {}
                    }
                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function showTab(tabName) {\n");
      out.write("            // Hide all tabs\n");
      out.write("            document.querySelectorAll('.tab-content').forEach(tab => {\n");
      out.write("                tab.classList.remove('active');\n");
      out.write("            });\n");
      out.write("            \n");
      out.write("            // Remove active class from all tabs\n");
      out.write("            document.querySelectorAll('.tab').forEach(tab => {\n");
      out.write("                tab.classList.remove('active');\n");
      out.write("            });\n");
      out.write("            \n");
      out.write("            // Show selected tab and set active\n");
      out.write("            document.getElementById(tabName + '-tab').classList.add('active');\n");
      out.write("            event.target.classList.add('active');\n");
      out.write("        }\n");
      out.write("    </script>\n");
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
