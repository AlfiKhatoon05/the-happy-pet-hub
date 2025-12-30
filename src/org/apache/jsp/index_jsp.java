package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Pet Management System</title>\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n");
      out.write("            min-height: 100vh;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: 0 auto;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header {\n");
      out.write("            text-align: center;\n");
      out.write("            color: white;\n");
      out.write("            margin-bottom: 50px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header h1 {\n");
      out.write("            font-size: 3em;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header p {\n");
      out.write("            font-size: 1.2em;\n");
      out.write("            opacity: 0.9;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .login-cards {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 30px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 40px;\n");
      out.write("            width: 300px;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0 10px 30px rgba(0,0,0,0.2);\n");
      out.write("            transition: transform 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card:hover {\n");
      out.write("            transform: translateY(-10px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card h3 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            font-size: 1.5em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .card p {\n");
      out.write("            color: #666;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            line-height: 1.5;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            display: inline-block;\n");
      out.write("            padding: 12px 30px;\n");
      out.write("            background: #667eea;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 25px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn:hover {\n");
      out.write("            background: #764ba2;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .user-card { border-top: 5px solid #4CAF50; }\n");
      out.write("        .doctor-card { border-top: 5px solid #2196F3; }\n");
      out.write("        .admin-card { border-top: 5px solid #FF9800; }\n");
      out.write("        \n");
      out.write("        .features {\n");
      out.write("            margin-top: 80px;\n");
      out.write("            text-align: center;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .features h2 {\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            font-size: 2em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .feature-grid {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 20px;\n");
      out.write("            margin-top: 30px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .feature-item {\n");
      out.write("            background: rgba(255,255,255,0.1);\n");
      out.write("            padding: 20px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            backdrop-filter: blur(10px);\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <h1>🐾The Happy Pet Hub</h1>\n");
      out.write("            <p>Comprehensive Pet Health Management System</p>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"login-cards\">\n");
      out.write("            <div class=\"card user-card\">\n");
      out.write("                <h3>Pet Owner</h3>\n");
      out.write("                <p>Manage your pets, book appointments, and view medical records</p>\n");
      out.write("                <a href=\"user-login.jsp\" class=\"btn\">Login / Register</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card doctor-card\">\n");
      out.write("                <h3>Veterinarian</h3>\n");
      out.write("                <p>Manage appointments, update medical records, and set availability</p>\n");
      out.write("                <a href=\"doctor-login.jsp\" class=\"btn\">Login / Register</a>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div class=\"card admin-card\">\n");
      out.write("                <h3>Administrator</h3>\n");
      out.write("                <p>Manage system users, approve doctors, and view reports</p>\n");
      out.write("                <a href=\"admin-login.jsp\" class=\"btn\">Admin Login</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"features\">\n");
      out.write("            <h2>Why Choose Our System?</h2>\n");
      out.write("            <div class=\"feature-grid\">\n");
      out.write("                <div class=\"feature-item\">\n");
      out.write("                    <h4>📅 Easy Scheduling</h4>\n");
      out.write("                    <p>Book appointments with trusted veterinarians</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"feature-item\">\n");
      out.write("                    <h4>🏥 Medical Records</h4>\n");
      out.write("                    <p>Keep track of your pet's health history</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"feature-item\">\n");
      out.write("                    <h4>👨‍⚕️ Expert Care</h4>\n");
      out.write("                    <p>Connect with qualified veterinarians</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"feature-item\">\n");
      out.write("                    <h4>📱 24/7 Access</h4>\n");
      out.write("                    <p>Manage your pet's health anytime, anywhere</p>\n");
      out.write("                </div>\n");
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
