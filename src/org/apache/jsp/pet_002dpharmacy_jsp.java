package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pet_002dpharmacy_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Pet Pharmacy - Pet Management</title>\n");
      out.write("    <style>\n");
      out.write("        * { margin: 0; padding: 0; box-sizing: border-box; }\n");
      out.write("        body { font-family: Arial, sans-serif; background: #f5f5f5; }\n");
      out.write("        .header { background: linear-gradient(135deg, #2196F3, #1976D2); color: white; padding: 20px; }\n");
      out.write("        .header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }\n");
      out.write("        .welcome { font-size: 1.5em; font-weight: bold; }\n");
      out.write("        .nav-links { display: flex; gap: 20px; }\n");
      out.write("        .nav-links a { color: white; text-decoration: none; padding: 8px 15px; border-radius: 20px; transition: background 0.3s ease; }\n");
      out.write("        .nav-links a:hover { background: rgba(255,255,255,0.2); }\n");
      out.write("        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }\n");
      out.write("        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }\n");
      out.write("        .page-title { color: #333; font-size: 2em; }\n");
      out.write("        .back-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }\n");
      out.write("        .back-btn:hover { background: #5a6268; }\n");
      out.write("        \n");
      out.write("        .pharmacy-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 25px; }\n");
      out.write("        .medicine-card { background: white; border-radius: 15px; padding: 20px; text-align: center; box-shadow: 0 5px 15px rgba(0,0,0,0.1); transition: transform 0.3s ease; border-left: 4px solid #2196F3; }\n");
      out.write("        .medicine-card:hover { transform: translateY(-5px); }\n");
      out.write("        .medicine-icon { font-size: 3em; margin-bottom: 15px; }\n");
      out.write("        .medicine-name { color: #333; font-size: 1.2em; font-weight: bold; margin-bottom: 10px; }\n");
      out.write("        .medicine-price { color: #2196F3; font-size: 1.3em; font-weight: bold; margin-bottom: 10px; }\n");
      out.write("        .medicine-description { color: #666; margin-bottom: 10px; line-height: 1.4; font-size: 0.9em; }\n");
      out.write("        .prescription-badge { background: #ff9800; color: white; padding: 3px 8px; border-radius: 10px; font-size: 0.8em; margin-bottom: 10px; display: inline-block; }\n");
      out.write("        .buy-btn { background: #2196F3; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; transition: background 0.3s ease; width: 100%; }\n");
      out.write("        .buy-btn:hover { background: #1976D2; }\n");
      out.write("        .buy-btn.prescription { background: #ff9800; }\n");
      out.write("        .buy-btn.prescription:hover { background: #f57c00; }\n");
      out.write("        \n");
      out.write("        .warning-box { background: #fff3cd; border: 1px solid #ffeaa7; border-radius: 10px; padding: 15px; margin-bottom: 20px; text-align: center; color: #856404; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">💊 Pet Pharmacy</div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"user-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"pet-products.jsp\">Pet Store</a>\n");
      out.write("                <a href=\"UserLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">Pet Pharmacy</h1>\n");
      out.write("            <a href=\"user-dashboard.jsp\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"warning-box\">\n");
      out.write("            ⚠️ <strong>Important:</strong> Some medications require veterinary prescription. Please consult your veterinarian before purchasing.\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"pharmacy-grid\">\n");
      out.write("            <!-- Flea Treatment -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">🐛</div>\n");
      out.write("                <div class=\"medicine-name\">Flea & Tick Treatment</div>\n");
      out.write("                <div class=\"medicine-price\">₹599</div>\n");
      out.write("                <div class=\"medicine-description\">Monthly prevention for fleas and ticks</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"buyMedicine('Flea & Tick Treatment', 599, false)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Dewormer -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">🪱</div>\n");
      out.write("                <div class=\"medicine-name\">Deworming Tablets</div>\n");
      out.write("                <div class=\"medicine-price\">₹349</div>\n");
      out.write("                <div class=\"medicine-description\">Broad-spectrum dewormer for dogs and cats</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"buyMedicine('Deworming Tablets', 349, false)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Pain Relief -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">😣</div>\n");
      out.write("                <div class=\"prescription-badge\">PRESCRIPTION REQUIRED</div>\n");
      out.write("                <div class=\"medicine-name\">Pain Relief Medication</div>\n");
      out.write("                <div class=\"medicine-price\">₹899</div>\n");
      out.write("                <div class=\"medicine-description\">For post-surgery or injury pain management</div>\n");
      out.write("                <button class=\"buy-btn prescription\" onclick=\"buyMedicine('Pain Relief Medication', 899, true)\">Requires Prescription</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Antibiotics -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">🦠</div>\n");
      out.write("                <div class=\"prescription-badge\">PRESCRIPTION REQUIRED</div>\n");
      out.write("                <div class=\"medicine-name\">Antibiotics</div>\n");
      out.write("                <div class=\"medicine-price\">₹749</div>\n");
      out.write("                <div class=\"medicine-description\">For bacterial infections - various types available</div>\n");
      out.write("                <button class=\"buy-btn prescription\" onclick=\"buyMedicine('Antibiotics', 749, true)\">Requires Prescription</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Vitamins -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">💊</div>\n");
      out.write("                <div class=\"medicine-name\">Multivitamin Supplements</div>\n");
      out.write("                <div class=\"medicine-price\">₹449</div>\n");
      out.write("                <div class=\"medicine-description\">Daily vitamins for overall pet health</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"buyMedicine('Multivitamin Supplements', 449, false)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Skin Care -->\n");
      out.write("            <div class=\"medicine-card\">\n");
      out.write("                <div class=\"medicine-icon\">🐾</div>\n");
      out.write("                <div class=\"medicine-name\">Skin & Coat Supplement</div>\n");
      out.write("                <div class=\"medicine-price\">₹599</div>\n");
      out.write("                <div class=\"medicine-description\">Promotes healthy skin and shiny coat</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"buyMedicine('Skin & Coat Supplement', 599, false)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function buyMedicine(medicineName, price, requiresPrescription) {\n");
      out.write("            if (requiresPrescription) {\n");
      out.write("                alert('⚠️ PRESCRIPTION REQUIRED\\n\\n' + medicineName + ' requires veterinary prescription.\\nPlease consult your veterinarian and upload prescription to purchase.');\n");
      out.write("            } else {\n");
      out.write("                alert('Added to Cart: ' + medicineName + '\\nPrice: ₹' + price + '\\n\\nIn a real system, this would proceed to checkout with prescription verification if needed.');\n");
      out.write("            }\n");
      out.write("        }\n");
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
