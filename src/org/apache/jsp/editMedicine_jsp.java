package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class editMedicine_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Edit Medicine</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background: #f4f6f8;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            height: 100vh;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("        .form-container {\n");
      out.write("            background-color: #ffffff;\n");
      out.write("            padding: 30px 40px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            box-shadow: 0 4px 8px rgba(0,0,0,0.1);\n");
      out.write("            width: 350px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        h2 {\n");
      out.write("            color: #2575fc;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        input[type=\"text\"],\n");
      out.write("        input[type=\"date\"] {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 10px 8px;\n");
      out.write("            margin: 10px 0;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 6px;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("        input[type=\"submit\"] {\n");
      out.write("            background-color: #2575fc;\n");
      out.write("            color: white;\n");
      out.write("            padding: 12px 20px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 6px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            width: 100%;\n");
      out.write("            font-size: 16px;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        input[type=\"submit\"]:hover {\n");
      out.write("            background-color: #1a5edb;\n");
      out.write("        }\n");
      out.write("        a {\n");
      out.write("            text-decoration: none;\n");
      out.write("            color: #2575fc;\n");
      out.write("            display: inline-block;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            transition: color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        a:hover {\n");
      out.write("            color: #1a5edb;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"form-container\">\n");
      out.write("        <h2>Edit Medicine</h2>\n");
      out.write("        <form action=\"UpdateMedicineServlet\" method=\"post\">\n");
      out.write("            <input type=\"hidden\" name=\"id\" value=\"");
      out.print( request.getAttribute("id") );
      out.write("\">\n");
      out.write("            <input type=\"text\" name=\"name\" value=\"");
      out.print( request.getAttribute("name") );
      out.write("\" placeholder=\"Medicine Name\" required>\n");
      out.write("            \n");
      out.write("            <input type=\"text\" name=\"type\" value=\"");
      out.print( request.getAttribute("type") );
      out.write("\" placeholder=\"Type\" required>\n");
      out.write("            \n");
      out.write("            <input type=\"date\" name=\"expiry_date\" value=\"");
      out.print( request.getAttribute("expiry_date") );
      out.write("\" required>\n");
      out.write("            \n");
      out.write("            <input type=\"submit\" value=\"Update Medicine\">\n");
      out.write("        </form>\n");
      out.write("        <a href=\"manageMedicines.jsp\">Back to Medicines</a>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("            \n");
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
