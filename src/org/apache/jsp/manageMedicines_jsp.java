package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class manageMedicines_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Manage Medicines</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h2>Manage Medicines</h2>\n");
      out.write("\n");
      out.write("    <!-- Add New Medicine -->\n");
      out.write("    <h3>Add New Medicine</h3>\n");
      out.write("    <form action=\"AddMedicineServlet\" method=\"post\">\n");
      out.write("        Name: <input type=\"text\" name=\"name\" required><br><br>\n");
      out.write("        Type: <input type=\"text\" name=\"type\" required><br><br>\n");
      out.write("        Expiry Date: <input type=\"date\" name=\"expiry_date\" required><br><br>\n");
      out.write("        <input type=\"submit\" value=\"Add Medicine\">\n");
      out.write("    </form>\n");
      out.write("    <hr>\n");
      out.write("\n");
      out.write("    <!-- Display Existing Medicines -->\n");
      out.write("    <h3>Existing Medicines</h3>\n");
      out.write("    <table border=\"1\" cellpadding=\"10\">\n");
      out.write("        <tr>\n");
      out.write("            <th>ID</th>\n");
      out.write("            <th>Name</th>\n");
      out.write("            <th>Type</th>\n");
      out.write("            <th>Expiry Date</th>\n");
      out.write("            <th>Actions</th>\n");
      out.write("        </tr>\n");
      out.write("\n");
      out.write("        ");

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");

                String sql = "SELECT * FROM medicines";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    Date expiry = rs.getDate("expiry_date");
        
      out.write("\n");
      out.write("        <tr>\n");
      out.write("            <td>");
      out.print( id );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( name );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( type );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( expiry );
      out.write("</td>\n");
      out.write("            <td>\n");
      out.write("                <a href=\"EditMedicineServlet?id=");
      out.print(id);
      out.write("\">Edit</a> |\n");
      out.write("                <a href=\"DeleteMedicineServlet?id=");
      out.print(id);
      out.write("\">Delete</a>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        ");

                }
            } catch(Exception e) {
                out.println("<tr><td colspan='5'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(stmt != null) stmt.close(); } catch(Exception e) {}
                try { if(conn != null) conn.close(); } catch(Exception e) {}
            }
        
      out.write("\n");
      out.write("    </table>\n");
      out.write("    <br>\n");
      out.write("    <a href=\"adminDashboard.jsp\">Back to Dashboard</a>\n");
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
