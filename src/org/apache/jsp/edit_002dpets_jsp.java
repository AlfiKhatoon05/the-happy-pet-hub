package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class edit_002dpets_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    // Get pet ID from request parameter
    String petIdParam = request.getParameter("id");
    if (petIdParam == null || petIdParam.trim().isEmpty()) {
        response.sendRedirect("pets-management.jsp?error=Pet ID is required");
        return;
    }

    int petId = Integer.parseInt(petIdParam);
    
    // Variables to store pet data
    String name = "";
    String species = "";
    String breed = "";
    int age = 0;
    double weight = 0.0;
    String color = "";
    String medicalHistory = "";
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
        
        // Get pet details
        String sql = "SELECT * FROM petsnew WHERE pet_id = ? AND user_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, petId);
        pstmt.setInt(2, userId);
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            name = rs.getString("name");
            species = rs.getString("species");
            breed = rs.getString("breed") != null ? rs.getString("breed") : "";
            age = rs.getInt("age");
            weight = rs.getDouble("weight");
            color = rs.getString("color") != null ? rs.getString("color") : "";
            medicalHistory = rs.getString("medical_history") != null ? rs.getString("medical_history") : "";
        } else {
            response.sendRedirect("pets-management.jsp?error=Pet not found or access denied");
            return;
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("pets-management.jsp?error=Error loading pet details");
        return;
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Edit Pet - Pet Management</title>\n");
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
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            width: 100%;\n");
      out.write("            max-width: 800px;\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 15px 35px rgba(0,0,0,0.2);\n");
      out.write("            overflow: hidden;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header {\n");
      out.write("            background: linear-gradient(135deg, #4CAF50, #45a049);\n");
      out.write("            color: white;\n");
      out.write("            padding: 25px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header h1 {\n");
      out.write("            font-size: 2em;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .header p {\n");
      out.write("            opacity: 0.9;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-container {\n");
      out.write("            padding: 40px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .back-link {\n");
      out.write("            display: inline-flex;\n");
      out.write("            align-items: center;\n");
      out.write("            gap: 8px;\n");
      out.write("            color: #4CAF50;\n");
      out.write("            text-decoration: none;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .back-link:hover {\n");
      out.write("            color: #45a049;\n");
      out.write("            transform: translateX(-5px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-group {\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-row {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 25px;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        label {\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("            color: #333;\n");
      out.write("            font-weight: 600;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .required::after {\n");
      out.write("            content: \" *\";\n");
      out.write("            color: #f44336;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-control {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 14px 18px;\n");
      out.write("            border: 2px solid #e0e0e0;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("            background: #f8f9fa;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-control:focus {\n");
      out.write("            outline: none;\n");
      out.write("            border-color: #4CAF50;\n");
      out.write("            background: white;\n");
      out.write("            box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        select.form-control {\n");
      out.write("            appearance: none;\n");
      out.write("            background-image: url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='%23333' viewBox='0 0 16 16'%3E%3Cpath d='M8 11L3 6h10l-5 5z'/%3E%3C/svg%3E\");\n");
      out.write("            background-repeat: no-repeat;\n");
      out.write("            background-position: right 18px center;\n");
      out.write("            background-size: 16px;\n");
      out.write("            padding-right: 50px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .textarea-container textarea {\n");
      out.write("            min-height: 120px;\n");
      out.write("            resize: vertical;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-actions {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: flex-end;\n");
      out.write("            gap: 15px;\n");
      out.write("            margin-top: 30px;\n");
      out.write("            padding-top: 25px;\n");
      out.write("            border-top: 2px solid #f0f0f0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn {\n");
      out.write("            padding: 14px 35px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("            display: inline-flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 8px;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-primary {\n");
      out.write("            background: linear-gradient(135deg, #4CAF50, #45a049);\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-primary:hover {\n");
      out.write("            background: linear-gradient(135deg, #45a049, #3d8b40);\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("            box-shadow: 0 5px 15px rgba(76, 175, 80, 0.3);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary {\n");
      out.write("            background: #6c757d;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary:hover {\n");
      out.write("            background: #5a6268;\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-cancel {\n");
      out.write("            background: #f44336;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-cancel:hover {\n");
      out.write("            background: #d32f2f;\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .message {\n");
      out.write("            padding: 15px;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 16px;\n");
      out.write("            animation: slideIn 0.5s ease;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        @keyframes slideIn {\n");
      out.write("            from {\n");
      out.write("                opacity: 0;\n");
      out.write("                transform: translateY(-20px);\n");
      out.write("            }\n");
      out.write("            to {\n");
      out.write("                opacity: 1;\n");
      out.write("                transform: translateY(0);\n");
      out.write("            }\n");
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
      out.write("        .icon {\n");
      out.write("            font-size: 1.2em;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        @media (max-width: 768px) {\n");
      out.write("            .form-row {\n");
      out.write("                grid-template-columns: 1fr;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .form-container {\n");
      out.write("                padding: 25px;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .form-actions {\n");
      out.write("                flex-direction: column;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .btn {\n");
      out.write("                width: 100%;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <h1><i class=\"fas fa-paw icon\"></i> Edit Pet Information</h1>\n");
      out.write("            <p>Update your pet's details below</p>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"form-container\">\n");
      out.write("            <a href=\"pets-management.jsp\" class=\"back-link\">\n");
      out.write("                <i class=\"fas fa-arrow-left\"></i> Back to My Pets\n");
      out.write("            </a>\n");
      out.write("            \n");
      out.write("            ");
      out.write("\n");
      out.write("            ");

                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            
      out.write("\n");
      out.write("                <div class=\"message error\">\n");
      out.write("                    <i class=\"fas fa-exclamation-circle\"></i> ");
      out.print( error );
      out.write("\n");
      out.write("                </div>\n");
      out.write("            ");

                }
                if (success != null) {
            
      out.write("\n");
      out.write("                <div class=\"message success\">\n");
      out.write("                    <i class=\"fas fa-check-circle\"></i> ");
      out.print( success );
      out.write("\n");
      out.write("                </div>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            \n");
      out.write("            <form action=\"UpdatePetServletnew\" method=\"POST\">\n");
      out.write("                <input type=\"hidden\" name=\"pet_id\" value=\"");
      out.print( petId );
      out.write("\">\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"name\" class=\"required\">Pet Name</label>\n");
      out.write("                        <input type=\"text\" \n");
      out.write("                               id=\"name\" \n");
      out.write("                               name=\"name\" \n");
      out.write("                               class=\"form-control\" \n");
      out.write("                               value=\"");
      out.print( name );
      out.write("\" \n");
      out.write("                               required\n");
      out.write("                               placeholder=\"Enter pet name\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"species\" class=\"required\">Species</label>\n");
      out.write("                        <select id=\"species\" name=\"species\" class=\"form-control\" required>\n");
      out.write("                            <option value=\"\">Select Species</option>\n");
      out.write("                            <option value=\"Dog\" ");
      out.print( species.equals("Dog") ? "selected" : "" );
      out.write(">Dog 🐕</option>\n");
      out.write("                            <option value=\"Cat\" ");
      out.print( species.equals("Cat") ? "selected" : "" );
      out.write(">Cat 🐈</option>\n");
      out.write("                            <option value=\"Bird\" ");
      out.print( species.equals("Bird") ? "selected" : "" );
      out.write(">Bird 🐦</option>\n");
      out.write("                            <option value=\"Rabbit\" ");
      out.print( species.equals("Rabbit") ? "selected" : "" );
      out.write(">Rabbit 🐇</option>\n");
      out.write("                            <option value=\"Fish\" ");
      out.print( species.equals("Fish") ? "selected" : "" );
      out.write(">Fish 🐠</option>\n");
      out.write("                            <option value=\"Hamster\" ");
      out.print( species.equals("Hamster") ? "selected" : "" );
      out.write(">Hamster 🐹</option>\n");
      out.write("                            <option value=\"Other\" ");
      out.print( species.equals("Other") ? "selected" : "" );
      out.write(">Other 🐾</option>\n");
      out.write("                        </select>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"breed\">Breed</label>\n");
      out.write("                        <input type=\"text\" \n");
      out.write("                               id=\"breed\" \n");
      out.write("                               name=\"breed\" \n");
      out.write("                               class=\"form-control\" \n");
      out.write("                               value=\"");
      out.print( breed );
      out.write("\"\n");
      out.write("                               placeholder=\"e.g., Golden Retriever, Persian\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"age\">Age (years)</label>\n");
      out.write("                        <input type=\"number\" \n");
      out.write("                               id=\"age\" \n");
      out.write("                               name=\"age\" \n");
      out.write("                               class=\"form-control\" \n");
      out.write("                               value=\"");
      out.print( age );
      out.write("\"\n");
      out.write("                               min=\"0\" \n");
      out.write("                               max=\"50\"\n");
      out.write("                               placeholder=\"Enter age in years\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-row\">\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"weight\">Weight (kg)</label>\n");
      out.write("                        <input type=\"number\" \n");
      out.write("                               id=\"weight\" \n");
      out.write("                               name=\"weight\" \n");
      out.write("                               class=\"form-control\" \n");
      out.write("                               value=\"");
      out.print( weight );
      out.write("\"\n");
      out.write("                               min=\"0\" \n");
      out.write("                               max=\"200\"\n");
      out.write("                               step=\"0.1\"\n");
      out.write("                               placeholder=\"Enter weight in kilograms\">\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"color\">Color</label>\n");
      out.write("                        <input type=\"text\" \n");
      out.write("                               id=\"color\" \n");
      out.write("                               name=\"color\" \n");
      out.write("                               class=\"form-control\" \n");
      out.write("                               value=\"");
      out.print( color );
      out.write("\"\n");
      out.write("                               placeholder=\"e.g., Brown, White, Black\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"medical_history\">Medical History</label>\n");
      out.write("                    <textarea id=\"medical_history\" \n");
      out.write("                              name=\"medical_history\" \n");
      out.write("                              class=\"form-control\"\n");
      out.write("                              placeholder=\"Enter any medical conditions, allergies, or special care requirements\">");
      out.print( medicalHistory );
      out.write("</textarea>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"form-actions\">\n");
      out.write("                    <a href=\"pets-management.jsp\" class=\"btn btn-cancel\">\n");
      out.write("                        <i class=\"fas fa-times\"></i> Cancel\n");
      out.write("                    </a>\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\">\n");
      out.write("                        <i class=\"fas fa-save\"></i> Update Pet\n");
      out.write("                    </button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <script>\n");
      out.write("        // Form validation\n");
      out.write("        document.querySelector('form').addEventListener('submit', function(e) {\n");
      out.write("            const name = document.getElementById('name').value.trim();\n");
      out.write("            const species = document.getElementById('species').value;\n");
      out.write("            \n");
      out.write("            if (!name) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                alert('Please enter pet name');\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            if (!species) {\n");
      out.write("                e.preventDefault();\n");
      out.write("                alert('Please select species');\n");
      out.write("                return;\n");
      out.write("            }\n");
      out.write("        });\n");
      out.write("        \n");
      out.write("        // Real-time validation feedback\n");
      out.write("        const inputs = document.querySelectorAll('.form-control');\n");
      out.write("        inputs.forEach(input => {\n");
      out.write("            input.addEventListener('blur', function() {\n");
      out.write("                if (this.value.trim()) {\n");
      out.write("                    this.classList.add('filled');\n");
      out.write("                    this.classList.remove('error');\n");
      out.write("                } else if (this.hasAttribute('required')) {\n");
      out.write("                    this.classList.add('error');\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("            \n");
      out.write("            input.addEventListener('input', function() {\n");
      out.write("                if (this.value.trim()) {\n");
      out.write("                    this.classList.remove('error');\n");
      out.write("                }\n");
      out.write("            });\n");
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
