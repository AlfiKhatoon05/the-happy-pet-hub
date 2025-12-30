package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pets_002dadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    Integer userId = (Integer) session.getAttribute("userId");
    if (fullName == null || userId == null) {
        response.sendRedirect("user-login.jsp?error=Please login first");
        return;
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Add New Pet - Pet Management</title>\n");
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
      out.write("            max-width: 800px;\n");
      out.write("            margin: 30px auto;\n");
      out.write("            padding: 0 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 40px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-card h2 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 2em;\n");
      out.write("            border-bottom: 3px solid #4CAF50;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group {\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group label {\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("            color: #555;\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group input, .form-group select, .form-group textarea {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            border: 2px solid #ddd;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            transition: border-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group input:focus, .form-group select:focus, .form-group textarea:focus {\n");
      out.write("            border-color: #4CAF50;\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-row {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-row .form-group {\n");
      out.write("            flex: 1;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            padding: 12px 30px;\n");
      out.write("            background: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("            margin-right: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn:hover {\n");
      out.write("            background: #45a049;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary {\n");
      out.write("            background: #6c757d;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary:hover {\n");
      out.write("            background: #5a6268;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-actions {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 30px;\n");
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
      out.write("                🐕 Add New Pet\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"user-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"pets-management.jsp\">My Pets</a>\n");
      out.write("                <a href=\"UserLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"form-card\">\n");
      out.write("            <h2>Add Your Pet</h2>\n");
      out.write("            \n");
      out.write("            ");
      out.write("\n");
      out.write("            ");

                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            
      out.write("\n");
      out.write("                <div class=\"message error\">\n");
      out.write("                    ");
      out.print( error );
      out.write("\n");
      out.write("                </div>\n");
      out.write("            ");

                }
                if (success != null) {
            
      out.write("\n");
      out.write("                <div class=\"message success\">\n");
      out.write("                    ");
      out.print( success );
      out.write("\n");
      out.write("                </div>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            \n");
      out.write("            <form action=\"AddPetServletnew\" method=\"POST\">\n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"name\">Pet Name *</label>\n");
      out.write("                        <input type=\"text\" id=\"name\" name=\"name\" required placeholder=\"Enter pet name\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"species\">Species *</label>\n");
      out.write("                        <select id=\"species\" name=\"species\" required>\n");
      out.write("                            <option value=\"\">Select Species</option>\n");
      out.write("                            <option value=\"Dog\">Dog</option>\n");
      out.write("                            <option value=\"Cat\">Cat</option>\n");
      out.write("                            <option value=\"Bird\">Bird</option>\n");
      out.write("                            <option value=\"Rabbit\">Rabbit</option>\n");
      out.write("                            <option value=\"Fish\">Fish</option>\n");
      out.write("                            <option value=\"Other\">Other</option>\n");
      out.write("                        </select>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"breed\">Breed</label>\n");
      out.write("                        <input type=\"text\" id=\"breed\" name=\"breed\" placeholder=\"Enter breed\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"age\">Age (years)</label>\n");
      out.write("                        <input type=\"number\" id=\"age\" name=\"age\" min=\"0\" max=\"50\" placeholder=\"Age in years\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"weight\">Weight (kg)</label>\n");
      out.write("                        <input type=\"number\" id=\"weight\" name=\"weight\" min=\"0\" step=\"0.1\" placeholder=\"Weight in kg\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"color\">Color</label>\n");
      out.write("                        <input type=\"text\" id=\"color\" name=\"color\" placeholder=\"Color\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"medicalHistory\">Medical History</label>\n");
      out.write("                    <textarea id=\"medicalHistory\" name=\"medicalHistory\" rows=\"4\" placeholder=\"Any existing medical conditions, allergies, or previous treatments\"></textarea>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-actions\">\n");
      out.write("                    <button type=\"submit\" class=\"btn\">Add Pet</button>\n");
      out.write("                    <a href=\"pets-management.jsp\" class=\"btn btn-secondary\">Cancel</a>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
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
