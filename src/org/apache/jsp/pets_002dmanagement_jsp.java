package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class pets_002dmanagement_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>My Pets - Pet Management</title>\n");
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
      out.write("        .btn {\n");
      out.write("            padding: 12px 25px;\n");
      out.write("            background: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn:hover {\n");
      out.write("            background: #45a049;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pets-grid {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));\n");
      out.write("            gap: 25px;\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-card {\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            padding: 25px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("            transition: transform 0.3s ease;\n");
      out.write("            border-left: 5px solid #4CAF50;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-card:hover {\n");
      out.write("            transform: translateY(-5px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-name {\n");
      out.write("            color: #333;\n");
      out.write("            font-size: 1.4em;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            border-bottom: 2px solid #f0f0f0;\n");
      out.write("            padding-bottom: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-details {\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-detail {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("            padding: 5px 0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #555;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-value {\n");
      out.write("            color: #333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .pet-actions {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 10px;\n");
      out.write("            margin-top: 15px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-btn {\n");
      out.write("            padding: 8px 15px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .edit-btn {\n");
      out.write("            background: #2196F3;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .edit-btn:hover {\n");
      out.write("            background: #1976D2;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .delete-btn {\n");
      out.write("            background: #f44336;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .delete-btn:hover {\n");
      out.write("            background: #d32f2f;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .no-pets {\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 50px;\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .no-pets h3 {\n");
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
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">\n");
      out.write("                🐾 My Pets\n");
      out.write("            </div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"user-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"pets-add.jsp\">Add Pet</a>\n");
      out.write("                <a href=\"UserLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">My Pets</h1>\n");
      out.write("            <a href=\"pets-add.jsp\" class=\"btn\">+ Add New Pet</a>\n");
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
      out.write("        <div class=\"pets-grid\">\n");
      out.write("            ");

                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT * FROM petsnew WHERE user_id = ? ORDER BY pet_id DESC";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, userId);
                    rs = pstmt.executeQuery();
                    
                    boolean hasPets = false;
                    
                    while (rs.next()) {
                        hasPets = true;
            
      out.write("\n");
      out.write("                        <div class=\"pet-card\">\n");
      out.write("                            <div class=\"pet-name\">\n");
      out.write("                                ");
      out.print( rs.getString("name") );
      out.write("\n");
      out.write("                                <span style=\"float: right;\">\n");
      out.write("                                    ");
 
                                        String species = rs.getString("species");
                                        if ("Dog".equals(species)) out.print("🐕");
                                        else if ("Cat".equals(species)) out.print("🐈");
                                        else if ("Bird".equals(species)) out.print("🐦");
                                        else if ("Rabbit".equals(species)) out.print("🐇");
                                        else out.print("🐾");
                                    
      out.write("\n");
      out.write("                                </span>\n");
      out.write("                            </div>\n");
      out.write("                            \n");
      out.write("                            <div class=\"pet-details\">\n");
      out.write("                                <div class=\"pet-detail\">\n");
      out.write("                                    <span class=\"detail-label\">Species:</span>\n");
      out.write("                                    <span class=\"detail-value\">");
      out.print( rs.getString("species") );
      out.write("</span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"pet-detail\">\n");
      out.write("                                    <span class=\"detail-label\">Breed:</span>\n");
      out.write("                                    <span class=\"detail-value\">");
      out.print( rs.getString("breed") != null ? rs.getString("breed") : "Not specified" );
      out.write("</span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"pet-detail\">\n");
      out.write("                                    <span class=\"detail-label\">Age:</span>\n");
      out.write("                                    <span class=\"detail-value\">\n");
      out.write("                                        ");
      out.print( rs.getInt("age") > 0 ? rs.getInt("age") + " years" : "Not specified" );
      out.write("\n");
      out.write("                                    </span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"pet-detail\">\n");
      out.write("                                    <span class=\"detail-label\">Weight:</span>\n");
      out.write("                                    <span class=\"detail-value\">\n");
      out.write("                                        ");
      out.print( rs.getDouble("weight") > 0 ? rs.getDouble("weight") + " kg" : "Not specified" );
      out.write("\n");
      out.write("                                    </span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"pet-detail\">\n");
      out.write("                                    <span class=\"detail-label\">Color:</span>\n");
      out.write("                                    <span class=\"detail-value\">");
      out.print( rs.getString("color") != null ? rs.getString("color") : "Not specified" );
      out.write("</span>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            \n");
      out.write("                            <div class=\"pet-actions\">\n");
      out.write("                                <a href=\"edit-pets.jsp?id=");
      out.print( rs.getInt("pet_id") );
      out.write("\" class=\"action-btn edit-btn\">Edit</a>\n");
      out.write("                                <a href=\"DeletePetServletnew?id=");
      out.print( rs.getInt("pet_id") );
      out.write("\" class=\"action-btn delete-btn\" \n");
      out.write("                                   onclick=\"return confirm('Are you sure you want to delete ");
      out.print( rs.getString("name") );
      out.write("?')\">Delete</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("            ");

                    }
                    
                    if (!hasPets) {
            
      out.write("\n");
      out.write("                        <div class=\"no-pets\">\n");
      out.write("                            <h3>No pets found</h3>\n");
      out.write("                            <p>You haven't added any pets yet.</p>\n");
      out.write("                            <a href=\"pets-add.jsp\" class=\"btn\" style=\"margin-top: 15px;\">Add Your First Pet</a>\n");
      out.write("                        </div>\n");
      out.write("            ");

                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
            
      out.write("\n");
      out.write("                    <div class=\"message error\">\n");
      out.write("                        Error loading pets: ");
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
