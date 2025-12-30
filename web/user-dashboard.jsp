<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Check if user is logged in
    String fullName = (String) session.getAttribute("fullName");
    if (fullName == null) {
        response.sendRedirect("user-login.jsp?error=Please login first");
        return;
    }
    
    // Get user data from session
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    String phone = (String) session.getAttribute("phone");
    String address = (String) session.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Dashboard - Pet Management</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
        }
        
        .header {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .header-content {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .welcome {
            font-size: 1.5em;
            font-weight: bold;
        }
        
        .logout-btn {
            background: rgba(255,255,255,0.2);
            color: white;
            border: 2px solid white;
            padding: 8px 20px;
            border-radius: 20px;
            text-decoration: none;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        
        .logout-btn:hover {
            background: white;
            color: #4CAF50;
        }
        
        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .dashboard-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }
        
        .card {
            background: white;
            border-radius: 15px;
            padding: 30px;
            text-align: center;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
            border-top: 5px solid #4CAF50;
        }
        
        .card:hover {
            transform: translateY(-5px);
        }
        
        .card h3 {
            color: #333;
            margin-bottom: 15px;
            font-size: 1.3em;
        }
        
        .card p {
            color: #666;
            margin-bottom: 20px;
            line-height: 1.5;
        }
        
        .card-btn {
            display: inline-block;
            padding: 10px 20px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 20px;
            font-weight: bold;
            transition: background 0.3s ease;
        }
        
        .card-btn:hover {
            background: #45a049;
        }
        
        .user-info {
            background: white;
            border-radius: 10px;
            padding: 25px;
            margin-bottom: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .user-info h2 {
            color: #333;
            margin-bottom: 15px;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        
        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
        }
        
        .info-item {
            padding: 10px;
        }
        
        .info-label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }
        
        .info-value {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">
                🐾 Welcome, <%= fullName %>!
            </div>
            <a href="UserLogoutServletnew" class="logout-btn">Logout</a>
        </div>
    </div>
    
    <div class="container">
        <div class="user-info">
            <h2>Your Profile Information</h2>
            <div class="info-grid">
                <div class="info-item">
                    <span class="info-label">Username:</span>
                    <span class="info-value"><%= username %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Email:</span>
                    <span class="info-value"><%= email %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Phone:</span>
                    <span class="info-value"><%= phone != null ? phone : "Not provided" %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Address:</span>
                    <span class="info-value"><%= address != null ? address : "Not provided" %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Full Name:</span>
                    <span class="info-value"><%= fullName %></span>
                </div>
            </div>
        </div>
        
        <div class="dashboard-cards">
            <div class="card">
                <h3>🐕 Manage Pets</h3>
                <p>Add, view, and manage your pets' information</p>
                <a href="pets-management.jsp" class="card-btn">Manage Pets</a>
            </div>
            
            <div class="card">
                <h3>📅 Book Appointment</h3>
                <p>Schedule appointments with veterinarians</p>
                <a href="book-appointment.jsp" class="card-btn">Book Now</a>
            </div>
            
            <div class="card">
                <h3>🏥 View Appointments</h3>
                <p>Check your upcoming and past appointments</p>
                <a href="view-appointments.jsp" class="card-btn">View Appointments</a>
            </div>
            
            <div class="card">
                <h3>📋 Medical Records</h3>
                <p>Access your pets' medical history</p>
                <a href="medical-records.jsp" class="card-btn">View Records</a>
            </div>
            
            <!-- NEW CARDS ADDED -->
            <div class="card">
                <h3>🛍️ Pet Store</h3>
                <p>Buy toys, food, and accessories for your pets</p>
                <a href="pet-products.jsp" class="card-btn">Shop Now</a>
            </div>
            
            <div class="card">
                <h3>💊 Pet Pharmacy</h3>
                <p>Order medicines and health products</p>
                <a href="pet-pharmacy.jsp" class="card-btn">View Pharmacy</a>
            </div>
        </div>
    </div>
</body>
</html>