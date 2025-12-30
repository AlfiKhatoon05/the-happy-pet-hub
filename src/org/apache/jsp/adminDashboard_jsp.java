package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminDashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Admin Dashboard</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
      out.write("            background: #f4f6f8;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("        header {\n");
      out.write("            background-color: #2575fc;\n");
      out.write("            color: white;\n");
      out.write("            padding: 20px 0;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        nav {\n");
      out.write("            margin: 40px auto;\n");
      out.write("            max-width: 400px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        nav ul {\n");
      out.write("            list-style: none;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("        nav ul li {\n");
      out.write("            margin: 15px 0;\n");
      out.write("        }\n");
      out.write("        nav ul li a {\n");
      out.write("            text-decoration: none;\n");
      out.write("            background-color: #2575fc;\n");
      out.write("            color: white;\n");
      out.write("            padding: 12px 20px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            transition: background-color 0.3s ease, transform 0.2s ease;\n");
      out.write("            display: inline-block;\n");
      out.write("            width: 200px;\n");
      out.write("        }\n");
      out.write("        nav ul li a:hover {\n");
      out.write("            background-color: #1a5edb;\n");
      out.write("            transform: scale(1.05);\n");
      out.write("        }\n");
      out.write("        footer {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 50px;\n");
      out.write("            color: #777;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <h1>Welcome Admin</h1>\n");
      out.write("    </header>\n");
      out.write("    <nav>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"viewUsers.jsp\">View & Approve Users</a></li>\n");
      out.write("            <li><a href=\"manageProducts.jsp\">Manage Products</a></li>\n");
      out.write("            <li><a href=\"managePets.jsp\">Manage Pets</a></li>\n");
      out.write("            <li><a href=\"manageMedicines.jsp\">Manage Medicines</a></li>\n");
      out.write("            <li><a href=\"index.jsp\">Logout</a></li>\n");
      out.write("        </ul>\n");
      out.write("    </nav>\n");
      out.write("    <footer>\n");
      out.write("        &copy; 2025 Admin Panel\n");
      out.write("    </footer>\n");
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
