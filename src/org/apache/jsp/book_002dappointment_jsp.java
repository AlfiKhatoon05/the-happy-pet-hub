package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class book_002dappointment_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String fullName = (String) session.getAttribute("fullName");
    Integer userId = (Integer) session.getAttribute("userId");

    if (fullName == null || userId == null) {
        response.sendRedirect("user-login.jsp?error=Please login first");
        return;
    }

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Book Appointment - Pet Management</title>\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);\n");
      out.write("            min-height: 100vh;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 1100px;\n");
      out.write("            margin: auto;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .header {\n");
      out.write("            background: linear-gradient(135deg, #4CAF50, #45a049);\n");
      out.write("            color: white;\n");
      out.write("            padding: 25px;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .header-content {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-links a {\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            margin-left: 15px;\n");
      out.write("            padding: 8px 18px;\n");
      out.write("            background: rgba(255,255,255,0.2);\n");
      out.write("            border-radius: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .nav-links a:hover {\n");
      out.write("            background: rgba(255,255,255,0.35);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-card, .doctors-section {\n");
      out.write("            background: white;\n");
      out.write("            padding: 30px;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            box-shadow: 0 10px 25px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .main-content {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: 1fr 1fr;\n");
      out.write("            gap: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2, h3 {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-bottom: 3px solid #4CAF50;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-group {\n");
      out.write("            margin-bottom: 18px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-weight: 600;\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 6px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input, select, textarea {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            border: 2px solid #e1e5ee;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input:focus, select:focus, textarea:focus {\n");
      out.write("            border-color: #4CAF50;\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-row {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn {\n");
      out.write("            padding: 12px 28px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            border: none;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-primary {\n");
      out.write("            background: #4CAF50;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-secondary {\n");
      out.write("            background: #6c757d;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-actions {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 25px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .doctor-card {\n");
      out.write("            background: #f9f9f9;\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("            padding: 15px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .doctor-name {\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 18px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message {\n");
      out.write("            padding: 12px;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            border-radius: 6px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            background: #ffebee;\n");
      out.write("            color: #c62828;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .success {\n");
      out.write("            background: #e8f5e8;\n");
      out.write("            color: #2e7d32;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media(max-width:900px) {\n");
      out.write("            .main-content {\n");
      out.write("                grid-template-columns: 1fr;\n");
      out.write("            }\n");
      out.write("            .form-row {\n");
      out.write("                flex-direction: column;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <h2>Book Appointment</h2>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"user-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"pets-management.jsp\">My Pets</a>\n");
      out.write("                <a href=\"view-appointments.jsp\">My Appointments</a>\n");
      out.write("                <a href=\"UserLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"main-content\">\n");
      out.write("\n");
      out.write("        <!-- BOOK APPOINTMENT FORM -->\n");
      out.write("        <div class=\"form-card\">\n");
      out.write("            <h2>New Appointment</h2>\n");
      out.write("\n");
      out.write("            ");

                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            
      out.write("\n");
      out.write("                <div class=\"message error\">");
      out.print( error );
      out.write("</div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            ");
 if (success != null) { 
      out.write("\n");
      out.write("                <div class=\"message success\">");
      out.print( success );
      out.write("</div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("            <form action=\"BookAppointmentServletnew\" method=\"POST\">\n");
      out.write("\n");
      out.write("                <!-- PET -->\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Select Pet *</label>\n");
      out.write("                    <select name=\"petId\" required>\n");
      out.write("                        <option value=\"\">Select Your Pet</option>\n");
      out.write("                        ");

                            Connection conn = null;
                            PreparedStatement ps = null;
                            ResultSet rs = null;
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                conn = DriverManager.getConnection(
                                    "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                                ps = conn.prepareStatement(
                                    "SELECT pet_id, name, species FROM petsnew WHERE user_id=?");
                                ps.setInt(1, userId);
                                rs = ps.executeQuery();

                                while (rs.next()) {
                        
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( rs.getInt("pet_id") );
      out.write("\">\n");
      out.write("                                ");
      out.print( rs.getString("name") );
      out.write(' ');
      out.write('(');
      out.print( rs.getString("species") );
      out.write(")\n");
      out.write("                            </option>\n");
      out.write("                        ");

                                }
                            } finally {
                                if (rs != null) rs.close();
                                if (ps != null) ps.close();
                                if (conn != null) conn.close();
                            }
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!-- DOCTOR -->\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Select Doctor *</label>\n");
      out.write("                    <select name=\"doctorId\" required>\n");
      out.write("                        <option value=\"\">Select Doctor</option>\n");
      out.write("                        ");

                            Connection conn2 = null;
                            PreparedStatement ps2 = null;
                            ResultSet rs2 = null;
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                conn2 = DriverManager.getConnection(
                                    "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                                ps2 = conn2.prepareStatement(
                                    "SELECT doctor_id, full_name, specialization FROM doctors WHERE is_approved=1");
                                rs2 = ps2.executeQuery();

                                while (rs2.next()) {
                        
      out.write("\n");
      out.write("                            <option value=\"");
      out.print( rs2.getInt("doctor_id") );
      out.write("\">\n");
      out.write("                                Dr. ");
      out.print( rs2.getString("full_name") );
      out.write(" - ");
      out.print( rs2.getString("specialization") );
      out.write("\n");
      out.write("                            </option>\n");
      out.write("                        ");

                                }
                            } finally {
                                if (rs2 != null) rs2.close();
                                if (ps2 != null) ps2.close();
                                if (conn2 != null) conn2.close();
                            }
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!-- DATE & TIME -->\n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Date *</label>\n");
      out.write("                        <input type=\"date\" name=\"appointmentDate\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label>Time *</label>\n");
      out.write("                        <select name=\"appointmentTime\" required>\n");
      out.write("                            <option value=\"\">Select Time</option>\n");
      out.write("                            <option>09:00 AM</option>\n");
      out.write("                            <option>10:00 AM</option>\n");
      out.write("                            <option>11:00 AM</option>\n");
      out.write("                            <option>12:00 PM</option>\n");
      out.write("                            <option>02:00 PM</option>\n");
      out.write("                            <option>03:00 PM</option>\n");
      out.write("                            <option>04:00 PM</option>\n");
      out.write("                            <option>05:00 PM</option>\n");
      out.write("                        </select>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <!-- REASON -->\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Reason *</label>\n");
      out.write("                    <textarea name=\"reason\" rows=\"3\" required></textarea>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"form-actions\">\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\">Book Appointment</button>\n");
      out.write("                    <a href=\"user-dashboard.jsp\" class=\"btn btn-secondary\">Cancel</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- DOCTORS LIST -->\n");
      out.write("        <div class=\"doctors-section\">\n");
      out.write("            <h3>Available Doctors</h3>\n");
      out.write("            ");

                Connection conn3 = null;
                PreparedStatement ps3 = null;
                ResultSet rs3 = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn3 = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                    ps3 = conn3.prepareStatement(
                        "SELECT full_name, specialization FROM doctors WHERE is_approved=1");
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {
            
      out.write("\n");
      out.write("                <div class=\"doctor-card\">\n");
      out.write("                    <div class=\"doctor-name\">Dr. ");
      out.print( rs3.getString("full_name") );
      out.write("</div>\n");
      out.write("                    <div>Specialization: ");
      out.print( rs3.getString("specialization") );
      out.write("</div>\n");
      out.write("                </div>\n");
      out.write("            ");

                    }
                } finally {
                    if (rs3 != null) rs3.close();
                    if (ps3 != null) ps3.close();
                    if (conn3 != null) conn3.close();
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    document.querySelector(\"input[type='date']\").min =\n");
      out.write("        new Date().toISOString().split(\"T\")[0];\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
