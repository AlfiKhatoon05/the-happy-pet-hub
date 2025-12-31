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
    <title>Pet Products - Pet Management</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #4CAF50, #45a049); color: white; padding: 20px; }
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
        
        .products-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 25px; }
        .product-card { background: white; border-radius: 15px; padding: 20px; text-align: center; box-shadow: 0 5px 15px rgba(0,0,0,0.1); transition: transform 0.3s ease; }
        .product-card:hover { transform: translateY(-5px); }
        .product-image { width: 100%; height: 150px; background: #f0f0f0; border-radius: 10px; margin-bottom: 15px; display: flex; align-items: center; justify-content: center; font-size: 3em; }
        .product-name { color: #333; font-size: 1.2em; font-weight: bold; margin-bottom: 10px; }
        .product-price { color: #4CAF50; font-size: 1.3em; font-weight: bold; margin-bottom: 15px; }
        .product-description { color: #666; margin-bottom: 15px; line-height: 1.4; }
        .buy-btn { background: #4CAF50; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; transition: background 0.3s ease; }
        .buy-btn:hover { background: #45a049; }
        
        .categories { display: flex; gap: 10px; margin-bottom: 20px; flex-wrap: wrap; }
        .category-btn { background: #e0e0e0; border: none; padding: 8px 15px; border-radius: 20px; cursor: pointer; transition: background 0.3s ease; }
        .category-btn.active { background: #4CAF50; color: white; }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">🛍️ Pet Store</div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pet-pharmacy.jsp">Pet Pharmacy</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">Pet Products Store</h1>
            <a href="user-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
        
        <div class="categories">
            <button class="category-btn active" onclick="filterProducts('all')">All Products</button>
            <button class="category-btn" onclick="filterProducts('food')">Pet Food</button>
            <button class="category-btn" onclick="filterProducts('toys')">Toys</button>
            <button class="category-btn" onclick="filterProducts('accessories')">Accessories</button>
        </div>
        
        <div class="products-grid">
            <!-- Dog Food -->
            <div class="product-card" data-category="food">
                <div class="product-image">🐕</div>
                <div class="product-name">Premium Dog Food</div>
                <div class="product-price">₹899</div>
                <div class="product-description">High-quality nutrition for adult dogs</div>
                <button class="buy-btn" onclick="addToCart('Premium Dog Food', 899)">Add to Cart</button>
            </div>
            
            <!-- Cat Food -->
            <div class="product-card" data-category="food">
                <div class="product-image">🐈</div>
                <div class="product-name">Gourmet Cat Food</div>
                <div class="product-price">₹799</div>
                <div class="product-description">Delicious and nutritious cat food</div>
                <button class="buy-btn" onclick="addToCart('Gourmet Cat Food', 799)">Add to Cart</button>
            </div>
            
            <!-- Dog Toy -->
            <div class="product-card" data-category="toys">
                <div class="product-image">🎾</div>
                <div class="product-name">Chew Toy</div>
                <div class="product-price">₹299</div>
                <div class="product-description">Durable chew toy for dogs</div>
                <button class="buy-btn" onclick="addToCart('Chew Toy', 299)">Add to Cart</button>
            </div>
            
            <!-- Cat Toy -->
            <div class="product-card" data-category="toys">
                <div class="product-image">🧶</div>
                <div class="product-name">Feather Wand</div>
                <div class="product-price">₹199</div>
                <div class="product-description">Interactive toy for cats</div>
                <button class="buy-btn" onclick="addToCart('Feather Wand', 199)">Add to Cart</button>
            </div>
            
            <!-- Dog Collar -->
            <div class="product-card" data-category="accessories">
                <div class="product-image">⛓️</div>
                <div class="product-name">Leather Collar</div>
                <div class="product-price">₹499</div>
                <div class="product-description">Comfortable leather dog collar</div>
                <button class="buy-btn" onclick="addToCart('Leather Collar', 499)">Add to Cart</button>
            </div>
            
            <!-- Pet Bed -->
            <div class="product-card" data-category="accessories">
                <div class="product-image">🛏️</div>
                <div class="product-name">Comfy Pet Bed</div>
                <div class="product-price">₹1299</div>
                <div class="product-description">Soft and warm pet bed</div>
                <button class="buy-btn" onclick="addToCart('Comfy Pet Bed', 1299)">Add to Cart</button>
            </div>
        </div>
    </div>

    <script>
        function filterProducts(category) {
            const products = document.querySelectorAll('.product-card');
            const buttons = document.querySelectorAll('.category-btn');
            
            // Update active button
            buttons.forEach(btn => btn.classList.remove('active'));
            event.target.classList.add('active');
            
            // Filter products
            products.forEach(product => {
                if (category === 'all' || product.getAttribute('data-category') === category) {
                    product.style.display = 'block';
                } else {
                    product.style.display = 'none';
                }
            });
        }
        
        function addToCart(productName, price) {
            alert('Added to Cart: ' + productName + '\nPrice: ₹' + price + '\n\nIn a real system, this would add to shopping cart and proceed to checkout.');
        }
    </script>
</body>
</html>