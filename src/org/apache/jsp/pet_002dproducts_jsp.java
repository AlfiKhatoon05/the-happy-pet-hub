package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class pet_002dproducts_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Pet Products - Pet Management</title>\n");
      out.write("    <style>\n");
      out.write("        * { margin: 0; padding: 0; box-sizing: border-box; }\n");
      out.write("        body { font-family: Arial, sans-serif; background: #f5f5f5; }\n");
      out.write("        .header { background: linear-gradient(135deg, #4CAF50, #45a049); color: white; padding: 20px; }\n");
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
      out.write("        .products-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 25px; }\n");
      out.write("        .product-card { background: white; border-radius: 15px; padding: 20px; text-align: center; box-shadow: 0 5px 15px rgba(0,0,0,0.1); transition: transform 0.3s ease; }\n");
      out.write("        .product-card:hover { transform: translateY(-5px); }\n");
      out.write("        .product-image { width: 100%; height: 150px; background: #f0f0f0; border-radius: 10px; margin-bottom: 15px; display: flex; align-items: center; justify-content: center; font-size: 3em; }\n");
      out.write("        .product-name { color: #333; font-size: 1.2em; font-weight: bold; margin-bottom: 10px; }\n");
      out.write("        .product-price { color: #4CAF50; font-size: 1.3em; font-weight: bold; margin-bottom: 15px; }\n");
      out.write("        .product-description { color: #666; margin-bottom: 15px; line-height: 1.4; }\n");
      out.write("        .buy-btn { background: #4CAF50; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; transition: background 0.3s ease; }\n");
      out.write("        .buy-btn:hover { background: #45a049; }\n");
      out.write("        \n");
      out.write("        .categories { display: flex; gap: 10px; margin-bottom: 20px; flex-wrap: wrap; }\n");
      out.write("        .category-btn { background: #e0e0e0; border: none; padding: 8px 15px; border-radius: 20px; cursor: pointer; transition: background 0.3s ease; }\n");
      out.write("        .category-btn.active { background: #4CAF50; color: white; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"header\">\n");
      out.write("        <div class=\"header-content\">\n");
      out.write("            <div class=\"welcome\">🛍️ Pet Store</div>\n");
      out.write("            <div class=\"nav-links\">\n");
      out.write("                <a href=\"user-dashboard.jsp\">Dashboard</a>\n");
      out.write("                <a href=\"pet-pharmacy.jsp\">Pet Pharmacy</a>\n");
      out.write("                <a href=\"UserLogoutServletnew\">Logout</a>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"page-header\">\n");
      out.write("            <h1 class=\"page-title\">Pet Products Store</h1>\n");
      out.write("            <a href=\"user-dashboard.jsp\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"categories\">\n");
      out.write("            <button class=\"category-btn active\" onclick=\"filterProducts('all')\">All Products</button>\n");
      out.write("            <button class=\"category-btn\" onclick=\"filterProducts('food')\">Pet Food</button>\n");
      out.write("            <button class=\"category-btn\" onclick=\"filterProducts('toys')\">Toys</button>\n");
      out.write("            <button class=\"category-btn\" onclick=\"filterProducts('accessories')\">Accessories</button>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"products-grid\">\n");
      out.write("            <!-- Dog Food -->\n");
      out.write("            <div class=\"product-card\" data-category=\"food\">\n");
      out.write("                <div class=\"product-image\">🐕</div>\n");
      out.write("                <div class=\"product-name\">Premium Dog Food</div>\n");
      out.write("                <div class=\"product-price\">₹899</div>\n");
      out.write("                <div class=\"product-description\">High-quality nutrition for adult dogs</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Premium Dog Food', 899)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Cat Food -->\n");
      out.write("            <div class=\"product-card\" data-category=\"food\">\n");
      out.write("                <div class=\"product-image\">🐈</div>\n");
      out.write("                <div class=\"product-name\">Gourmet Cat Food</div>\n");
      out.write("                <div class=\"product-price\">₹799</div>\n");
      out.write("                <div class=\"product-description\">Delicious and nutritious cat food</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Gourmet Cat Food', 799)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Dog Toy -->\n");
      out.write("            <div class=\"product-card\" data-category=\"toys\">\n");
      out.write("                <div class=\"product-image\">🎾</div>\n");
      out.write("                <div class=\"product-name\">Chew Toy</div>\n");
      out.write("                <div class=\"product-price\">₹299</div>\n");
      out.write("                <div class=\"product-description\">Durable chew toy for dogs</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Chew Toy', 299)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Cat Toy -->\n");
      out.write("            <div class=\"product-card\" data-category=\"toys\">\n");
      out.write("                <div class=\"product-image\">🧶</div>\n");
      out.write("                <div class=\"product-name\">Feather Wand</div>\n");
      out.write("                <div class=\"product-price\">₹199</div>\n");
      out.write("                <div class=\"product-description\">Interactive toy for cats</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Feather Wand', 199)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Dog Collar -->\n");
      out.write("            <div class=\"product-card\" data-category=\"accessories\">\n");
      out.write("                <div class=\"product-image\">⛓️</div>\n");
      out.write("                <div class=\"product-name\">Leather Collar</div>\n");
      out.write("                <div class=\"product-price\">₹499</div>\n");
      out.write("                <div class=\"product-description\">Comfortable leather dog collar</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Leather Collar', 499)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <!-- Pet Bed -->\n");
      out.write("            <div class=\"product-card\" data-category=\"accessories\">\n");
      out.write("                <div class=\"product-image\">🛏️</div>\n");
      out.write("                <div class=\"product-name\">Comfy Pet Bed</div>\n");
      out.write("                <div class=\"product-price\">₹1299</div>\n");
      out.write("                <div class=\"product-description\">Soft and warm pet bed</div>\n");
      out.write("                <button class=\"buy-btn\" onclick=\"addToCart('Comfy Pet Bed', 1299)\">Add to Cart</button>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function filterProducts(category) {\n");
      out.write("            const products = document.querySelectorAll('.product-card');\n");
      out.write("            const buttons = document.querySelectorAll('.category-btn');\n");
      out.write("            \n");
      out.write("            // Update active button\n");
      out.write("            buttons.forEach(btn => btn.classList.remove('active'));\n");
      out.write("            event.target.classList.add('active');\n");
      out.write("            \n");
      out.write("            // Filter products\n");
      out.write("            products.forEach(product => {\n");
      out.write("                if (category === 'all' || product.getAttribute('data-category') === category) {\n");
      out.write("                    product.style.display = 'block';\n");
      out.write("                } else {\n");
      out.write("                    product.style.display = 'none';\n");
      out.write("                }\n");
      out.write("            });\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        function addToCart(productName, price) {\n");
      out.write("            alert('Added to Cart: ' + productName + '\\nPrice: ₹' + price + '\\n\\nIn a real system, this would add to shopping cart and proceed to checkout.');\n");
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
