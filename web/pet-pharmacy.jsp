<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Check if user is logged in
    String fullName = (String) session.getAttribute("fullName");
    Integer userId = (Integer) session.getAttribute("userId");
    if (fullName == null || userId == null) {
        response.sendRedirect("user-login.jsp?error=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pet Pharmacy - Pet Management</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #2196F3, #1976D2); color: white; padding: 20px; }
        .header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
        .welcome { font-size: 1.5em; font-weight: bold; }
        .nav-links { display: flex; gap: 20px; }
        .nav-links a { color: white; text-decoration: none; padding: 8px 15px; border-radius: 20px; transition: background 0.3s ease; }
        .nav-links a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .page-title { color: #333; font-size: 2em; }
        .back-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }
        .back-btn:hover { background: #5a6268; }
        
        .pharmacy-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 25px; }
        .medicine-card { background: white; border-radius: 15px; padding: 20px; text-align: center; box-shadow: 0 5px 15px rgba(0,0,0,0.1); transition: transform 0.3s ease; border-left: 4px solid #2196F3; }
        .medicine-card:hover { transform: translateY(-5px); }
        .medicine-icon { font-size: 3em; margin-bottom: 15px; }
        .medicine-name { color: #333; font-size: 1.2em; font-weight: bold; margin-bottom: 10px; }
        .medicine-price { color: #2196F3; font-size: 1.3em; font-weight: bold; margin-bottom: 10px; }
        .medicine-description { color: #666; margin-bottom: 10px; line-height: 1.4; font-size: 0.9em; }
        .prescription-badge { background: #ff9800; color: white; padding: 3px 8px; border-radius: 10px; font-size: 0.8em; margin-bottom: 10px; display: inline-block; }
        .buy-btn { background: #2196F3; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; transition: background 0.3s ease; width: 100%; }
        .buy-btn:hover { background: #1976D2; }
        .buy-btn.prescription { background: #ff9800; }
        .buy-btn.prescription:hover { background: #f57c00; }
        
        .warning-box { background: #fff3cd; border: 1px solid #ffeaa7; border-radius: 10px; padding: 15px; margin-bottom: 20px; text-align: center; color: #856404; }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">💊 Pet Pharmacy</div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pet-products.jsp">Pet Store</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">Pet Pharmacy</h1>
            <a href="user-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
        
        <div class="warning-box">
            ⚠️ <strong>Important:</strong> Some medications require veterinary prescription. Please consult your veterinarian before purchasing.
        </div>
        
        <div class="pharmacy-grid">
            <!-- Flea Treatment -->
            <div class="medicine-card">
                <div class="medicine-icon">🐛</div>
                <div class="medicine-name">Flea & Tick Treatment</div>
                <div class="medicine-price">₹599</div>
                <div class="medicine-description">Monthly prevention for fleas and ticks</div>
                <button class="buy-btn" onclick="buyMedicine('Flea & Tick Treatment', 599, false)">Add to Cart</button>
            </div>
            
            <!-- Dewormer -->
            <div class="medicine-card">
                <div class="medicine-icon">🪱</div>
                <div class="medicine-name">Deworming Tablets</div>
                <div class="medicine-price">₹349</div>
                <div class="medicine-description">Broad-spectrum dewormer for dogs and cats</div>
                <button class="buy-btn" onclick="buyMedicine('Deworming Tablets', 349, false)">Add to Cart</button>
            </div>
            
            <!-- Pain Relief -->
            <div class="medicine-card">
                <div class="medicine-icon">😣</div>
                <div class="prescription-badge">PRESCRIPTION REQUIRED</div>
                <div class="medicine-name">Pain Relief Medication</div>
                <div class="medicine-price">₹899</div>
                <div class="medicine-description">For post-surgery or injury pain management</div>
                <button class="buy-btn prescription" onclick="buyMedicine('Pain Relief Medication', 899, true)">Requires Prescription</button>
            </div>
            
            <!-- Antibiotics -->
            <div class="medicine-card">
                <div class="medicine-icon">🦠</div>
                <div class="prescription-badge">PRESCRIPTION REQUIRED</div>
                <div class="medicine-name">Antibiotics</div>
                <div class="medicine-price">₹749</div>
                <div class="medicine-description">For bacterial infections - various types available</div>
                <button class="buy-btn prescription" onclick="buyMedicine('Antibiotics', 749, true)">Requires Prescription</button>
            </div>
            
            <!-- Vitamins -->
            <div class="medicine-card">
                <div class="medicine-icon">💊</div>
                <div class="medicine-name">Multivitamin Supplements</div>
                <div class="medicine-price">₹449</div>
                <div class="medicine-description">Daily vitamins for overall pet health</div>
                <button class="buy-btn" onclick="buyMedicine('Multivitamin Supplements', 449, false)">Add to Cart</button>
            </div>
            
            <!-- Skin Care -->
            <div class="medicine-card">
                <div class="medicine-icon">🐾</div>
                <div class="medicine-name">Skin & Coat Supplement</div>
                <div class="medicine-price">₹599</div>
                <div class="medicine-description">Promotes healthy skin and shiny coat</div>
                <button class="buy-btn" onclick="buyMedicine('Skin & Coat Supplement', 599, false)">Add to Cart</button>
            </div>
        </div>
    </div>

    <script>
        function buyMedicine(medicineName, price, requiresPrescription) {
            if (requiresPrescription) {
                alert('⚠️ PRESCRIPTION REQUIRED\n\n' + medicineName + ' requires veterinary prescription.\nPlease consult your veterinarian and upload prescription to purchase.');
            } else {
                alert('Added to Cart: ' + medicineName + '\nPrice: ₹' + price + '\n\nIn a real system, this would proceed to checkout with prescription verification if needed.');
            }
        }
    </script>
</body>
</html>