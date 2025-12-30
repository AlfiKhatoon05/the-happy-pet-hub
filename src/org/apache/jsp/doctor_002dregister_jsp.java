package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class doctor_002dregister_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Doctor Registration - Pet Management</title>\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #2196F3, #1976D2);\n");
      out.write("            min-height: 100vh;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            display: flex;\n");
      out.write("            max-width: 1000px;\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            overflow: hidden;\n");
      out.write("            box-shadow: 0 15px 30px rgba(0,0,0,0.2);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .welcome-section {\n");
      out.write("            flex: 1;\n");
      out.write("            background: linear-gradient(135deg, #2196F3, #1976D2);\n");
      out.write("            color: white;\n");
      out.write("            padding: 40px;\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction: column;\n");
      out.write("            justify-content: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .welcome-section h1 {\n");
      out.write("            font-size: 2.2em;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .welcome-section p {\n");
      out.write("            line-height: 1.6;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-section {\n");
      out.write("            flex: 1;\n");
      out.write("            padding: 40px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-section h2 {\n");
      out.write("            color: #333;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 1.8em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group {\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group label {\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("            color: #555;\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group input, .form-group select {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 10px;\n");
      out.write("            border: 2px solid #ddd;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            transition: border-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group input:focus, .form-group select:focus {\n");
      out.write("            border-color: #2196F3;\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            background: #2196F3;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("            margin-top: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn:hover {\n");
      out.write("            background: #1976D2;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .switch-form {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .switch-form a {\n");
      out.write("            color: #2196F3;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .switch-form a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .message {\n");
      out.write("            padding: 10px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 14px;\n");
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
      out.write("        .form-row {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-row .form-group {\n");
      out.write("            flex: 1;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .info-note {\n");
      out.write("            background: #e3f2fd;\n");
      out.write("            padding: 10px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            color: #1976D2;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"welcome-section\">\n");
      out.write("            <h1>Join as Veterinarian!</h1>\n");
      out.write("            <p>Register now to join our network of professional veterinarians and provide quality care to pets.</p>\n");
      out.write("            <p>✓ Manage your appointments</p>\n");
      out.write("            <p>✓ Access medical records</p>\n");
      out.write("            <p>✓ Set your availability</p>\n");
      out.write("            <p>✓ Grow your practice</p>\n");
      out.write("            <p>✓ 24/7 platform access</p>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"form-section\">\n");
      out.write("            <h2>Doctor Registration</h2>\n");
      out.write("            \n");
      out.write("            <div class=\"info-note\">\n");
      out.write("                ⓘ Your registration will be reviewed by admin before approval.\n");
      out.write("            </div>\n");
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
      out.write("            <form action=\"DoctorRegisterServletnew\" method=\"POST\">\n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"fullName\">Full Name *</label>\n");
      out.write("                        <input type=\"text\" id=\"fullName\" name=\"fullName\" required>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"username\">Username *</label>\n");
      out.write("                        <input type=\"text\" id=\"username\" name=\"username\" required>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"email\">Email Address *</label>\n");
      out.write("                    <input type=\"email\" id=\"email\" name=\"email\" required>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"password\">Password *</label>\n");
      out.write("                        <input type=\"password\" id=\"password\" name=\"password\" required>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"confirmPassword\">Confirm Password *</label>\n");
      out.write("                        <input type=\"password\" id=\"confirmPassword\" name=\"confirmPassword\" required>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"specialization\">Specialization *</label>\n");
      out.write("                    <select id=\"specialization\" name=\"specialization\" required>\n");
      out.write("                        <option value=\"\">Select Specialization</option>\n");
      out.write("                        <option value=\"General Veterinary\">General Veterinary</option>\n");
      out.write("                        <option value=\"Surgery\">Surgery</option>\n");
      out.write("                        <option value=\"Dermatology\">Dermatology</option>\n");
      out.write("                        <option value=\"Dentistry\">Dentistry</option>\n");
      out.write("                        <option value=\"Ophthalmology\">Ophthalmology</option>\n");
      out.write("                        <option value=\"Cardiology\">Cardiology</option>\n");
      out.write("                        <option value=\"Neurology\">Neurology</option>\n");
      out.write("                        <option value=\"Oncology\">Oncology</option>\n");
      out.write("                        <option value=\"Other\">Other</option>\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"phone\">Phone Number *</label>\n");
      out.write("                        <input type=\"tel\" id=\"phone\" name=\"phone\" required>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"licenseNumber\">License Number *</label>\n");
      out.write("                        <input type=\"text\" id=\"licenseNumber\" name=\"licenseNumber\" required>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <button type=\"submit\" class=\"btn\">Register as Doctor</button>\n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("            <div class=\"switch-form\">\n");
      out.write("                <p>Already have an account? <a href=\"doctor-login.jsp\">Login here</a></p>\n");
      out.write("                <p><a href=\"index.jsp\">← Back to Home</a></p>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        // Client-side password validation\n");
      out.write("        document.querySelector('form').addEventListener('submit', function(e) {\n");
      out.write("            const password = document.getElementById('password').value;\n");
      out.write("            const confirmPassword = document.getElementById('confirmPassword').value;\n");
      out.write("            \n");
      out.write("            if (password !== confirmPassword) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                alert('Passwords do not match!');\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            if (password.length < 6) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                alert('Password must be at least 6 characters long!');\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("        });\n");
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
