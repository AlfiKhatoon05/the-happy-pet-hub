<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pet Management System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .header {
            text-align: center;
            color: white;
            margin-bottom: 50px;
        }
        
        .header h1 {
            font-size: 3em;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }
        
        .header p {
            font-size: 1.2em;
            opacity: 0.9;
        }
        
        .login-cards {
            display: flex;
            justify-content: center;
            gap: 30px;
            flex-wrap: wrap;
        }
        
        .card {
            background: white;
            border-radius: 15px;
            padding: 40px;
            width: 300px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            transition: transform 0.3s ease;
        }
        
        .card:hover {
            transform: translateY(-10px);
        }
        
        .card h3 {
            color: #333;
            margin-bottom: 20px;
            font-size: 1.5em;
        }
        
        .card p {
            color: #666;
            margin-bottom: 25px;
            line-height: 1.5;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 30px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 25px;
            font-weight: bold;
            transition: background 0.3s ease;
        }
        
        .btn:hover {
            background: #764ba2;
        }
        
        .user-card { border-top: 5px solid #4CAF50; }
        .doctor-card { border-top: 5px solid #2196F3; }
        .admin-card { border-top: 5px solid #FF9800; }
        
        .features {
            margin-top: 80px;
            text-align: center;
            color: white;
        }
        
        .features h2 {
            margin-bottom: 30px;
            font-size: 2em;
        }
        
        .feature-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        
        .feature-item {
            background: rgba(255,255,255,0.1);
            padding: 20px;
            border-radius: 10px;
            backdrop-filter: blur(10px);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🐾The Happy Pet Hub</h1>
            <p>Comprehensive Pet Health Management System</p>
        </div>
        
        <div class="login-cards">
            <div class="card user-card">
                <h3>Pet Owner</h3>
                <p>Manage your pets, book appointments, and view medical records</p>
                <a href="user-login.jsp" class="btn">Login / Register</a>
            </div>
            
            <div class="card doctor-card">
                <h3>Veterinarian</h3>
                <p>Manage appointments, update medical records, and set availability</p>
                <a href="doctor-login.jsp" class="btn">Login / Register</a>
            </div>
            
            <div class="card admin-card">
                <h3>Administrator</h3>
                <p>Manage system users, approve doctors, and view reports</p>
                <a href="admin-login.jsp" class="btn">Admin Login</a>
            </div>
        </div>
        
        <div class="features">
            <h2>Why Choose Our System?</h2>
            <div class="feature-grid">
                <div class="feature-item">
                    <h4>📅 Easy Scheduling</h4>
                    <p>Book appointments with trusted veterinarians</p>
                </div>
                <div class="feature-item">
                    <h4>🏥 Medical Records</h4>
                    <p>Keep track of your pet's health history</p>
                </div>
                <div class="feature-item">
                    <h4>👨‍⚕️ Expert Care</h4>
                    <p>Connect with qualified veterinarians</p>
                </div>
                <div class="feature-item">
                    <h4>📱 24/7 Access</h4>
                    <p>Manage your pet's health anytime, anywhere</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>