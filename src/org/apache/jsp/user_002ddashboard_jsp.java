package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_002ddashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    // Check if user is logged in
    String fullName = (String) session.getAttribute("fullName");
    if (fullName == null) {
        response.sendRedirect("user-login.jsp?error=Please login first");
        return;
    }
    
    // Get user data from session
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
    String address = (String) session.getAttribute("address");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>User Dashboard - Pet Management</title>\n");
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
      out.write("            background: linear-gradient(135deg, #4CAF50, #45a049);\n");
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
      out.write("        .logout-btn {\n");
      out.write("            background: rgba(255,255,255,0.2);\n");
      out.write("            color: white;\n");
      out.write("            border: 2px solid white;\n");
      out.write("            padding: 8px 20px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .logout-btn:hover {\n");
      out.write("            background: white;\n");
      out.write("            color: #4CAF50;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: 30px auto;\n");
      out.write("            padding: 0 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .dashboard-cards {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 25px;\n");
      out.write("            margin-top: 30px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 30px;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("            transition: transform 0.3s ease;\n");
      out.write("            border-top: 5px solid #4CAF50;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card:hover {\n");
      out.write("            transform: translateY(-5px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card h3 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            font-size: 1.3em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card p {\n");
      out.write("            color: #666;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            line-height: 1.5;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card-btn {\n");
      out.write("            display: inline-block;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            background: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card-btn:hover {\n");
      out.write("            background: #45a049;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .user-info {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            padding: 25px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .user-info h2 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            border-bottom: 2px solid #4CAF50;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-grid {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n");
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
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">\n");
      out.write("                🐾 Welcome, ");
      out.print( fullName );
      out.write("!\n");
      out.write("            </div>\n");
      out.write("            <a href=\"UserLogoutServletnew\" class=\"logout-btn\">Logout</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"user-info\">\n");
      out.write("            <h2>Your Profile Information</h2>\n");
      out.write("            <div class=\"info-grid\">\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Username:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( username );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Email:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( email );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Phone:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( phone != null ? phone : "Not provided" );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Address:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( address != null ? address : "Not provided" );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"info-item\">\n");
      out.write("                    <span class=\"info-label\">Full Name:</span>\n");
      out.write("                    <span class=\"info-value\">");
      out.print( fullName );
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"dashboard-cards\">\n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>🐕 Manage Pets</h3>\n");
      out.write("                <p>Add, view, and manage your pets' information</p>\n");
      out.write("                <a href=\"pets-management.jsp\" class=\"card-btn\">Manage Pets</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>📅 Book Appointment</h3>\n");
      out.write("                <p>Schedule appointments with veterinarians</p>\n");
      out.write("                <a href=\"book-appointment.jsp\" class=\"card-btn\">Book Now</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>🏥 View Appointments</h3>\n");
      out.write("                <p>Check your upcoming and past appointments</p>\n");
      out.write("                <a href=\"view-appointments.jsp\" class=\"card-btn\">View Appointments</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>📋 Medical Records</h3>\n");
      out.write("                <p>Access your pets' medical history</p>\n");
      out.write("                <a href=\"medical-records.jsp\" class=\"card-btn\">View Records</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- NEW CARDS ADDED -->\n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>🛍️ Pet Store</h3>\n");
      out.write("                <p>Buy toys, food, and accessories for your pets</p>\n");
      out.write("                <a href=\"pet-products.jsp\" class=\"card-btn\">Shop Now</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card\">\n");
      out.write("                <h3>💊 Pet Pharmacy</h3>\n");
      out.write("                <p>Order medicines and health products</p>\n");
      out.write("                <a href=\"pet-pharmacy.jsp\" class=\"card-btn\">View Pharmacy</a>\n");
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
