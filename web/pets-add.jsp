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
    <title>Add New Pet - Pet Management</title>
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
        
        .nav-links {
            display: flex;
            gap: 20px;
        }
        
        .nav-links a {
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 20px;
            transition: background 0.3s ease;
        }
        
        .nav-links a:hover {
            background: rgba(255,255,255,0.2);
        }
        
        .container {
            max-width: 800px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .form-card {
            background: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .form-card h2 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
            border-bottom: 3px solid #4CAF50;
            padding-bottom: 10px;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: bold;
            font-size: 16px;
        }
        
        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }
        
        .form-group input:focus, .form-group select:focus, .form-group textarea:focus {
            border-color: #4CAF50;
            outline: none;
        }
        
        .form-row {
            display: flex;
            gap: 20px;
        }
        
        .form-row .form-group {
            flex: 1;
        }
        
        .btn {
            padding: 12px 30px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
            margin-right: 10px;
        }
        
        .btn:hover {
            background: #45a049;
        }
        
        .btn-secondary {
            background: #6c757d;
        }
        
        .btn-secondary:hover {
            background: #5a6268;
        }
        
        .form-actions {
            text-align: center;
            margin-top: 30px;
        }
        
        .message {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 8px;
            text-align: center;
            font-size: 16px;
        }
        
        .error {
            background: #ffebee;
            color: #c62828;
            border: 1px solid #ffcdd2;
        }
        
        .success {
            background: #e8f5e8;
            color: #2e7d32;
            border: 1px solid #c8e6c9;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">
                🐕 Add New Pet
            </div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pets-management.jsp">My Pets</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="form-card">
            <h2>Add Your Pet</h2>
            
            <%-- Display messages --%>
            <%
                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            %>
                <div class="message error">
                    <%= error %>
                </div>
            <%
                }
                if (success != null) {
            %>
                <div class="message success">
                    <%= success %>
                </div>
            <%
                }
            %>
            
            <form action="AddPetServletnew" method="POST">
                <div class="form-row">
                    <div class="form-group">
                        <label for="name">Pet Name *</label>
                        <input type="text" id="name" name="name" required placeholder="Enter pet name">
                    </div>
                    
                    <div class="form-group">
                        <label for="species">Species *</label>
                        <select id="species" name="species" required>
                            <option value="">Select Species</option>
                            <option value="Dog">Dog</option>
                            <option value="Cat">Cat</option>
                            <option value="Bird">Bird</option>
                            <option value="Rabbit">Rabbit</option>
                            <option value="Fish">Fish</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="breed">Breed</label>
                        <input type="text" id="breed" name="breed" placeholder="Enter breed">
                    </div>
                    
                    <div class="form-group">
                        <label for="age">Age (years)</label>
                        <input type="number" id="age" name="age" min="0" max="50" placeholder="Age in years">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="weight">Weight (kg)</label>
                        <input type="number" id="weight" name="weight" min="0" step="0.1" placeholder="Weight in kg">
                    </div>
                    
                    <div class="form-group">
                        <label for="color">Color</label>
                        <input type="text" id="color" name="color" placeholder="Color">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="medicalHistory">Medical History</label>
                    <textarea id="medicalHistory" name="medicalHistory" rows="4" placeholder="Any existing medical conditions, allergies, or previous treatments"></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn">Add Pet</button>
                    <a href="pets-management.jsp" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>