<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Login - Pet Management</title>
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
            display: flex;
            align-items: center;
            justify-content: center;
        }
        
        .container {
            display: flex;
            max-width: 900px;
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 15px 30px rgba(0,0,0,0.2);
        }
        
        .welcome-section {
            flex: 1;
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            padding: 50px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        
        .welcome-section h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        
        .welcome-section p {
            line-height: 1.6;
            margin-bottom: 20px;
        }
        
        .form-section {
            flex: 1;
            padding: 50px;
        }
        
        .form-section h2 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        
        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }
        
        .form-group input:focus {
            border-color: #4CAF50;
            outline: none;
        }
        
        .btn {
            width: 100%;
            padding: 12px;
            background: #4CAF50;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        
        .btn:hover {
            background: #45a049;
        }
        
        .switch-form {
            text-align: center;
            margin-top: 20px;
        }
        
        .switch-form a {
            color: #667eea;
            text-decoration: none;
        }
        
        .switch-form a:hover {
            text-decoration: underline;
        }
        
        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            text-align: center;
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
    <div class="container">
        <div class="welcome-section">
            <h1>Welcome Back!</h1>
            <p>Login to manage your pets, book appointments with veterinarians, and access your pet's medical records.</p>
            <p>Don't have an account? Register now to get started with comprehensive pet care management.</p>
        </div>
        
        <div class="form-section">
            <h2>User Login</h2>
            
            <%-- Display error messages --%>
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
            
            <form action="UserLoginServlet112" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit" class="btn">Login</button>
            </form>
            
            <div class="switch-form">
                <p>Don't have an account? <a href="user-register.jsp">Register here</a></p>
                <p><a href="index.jsp">← Back to Home</a></p>
            </div>
        </div>
    </div>
</body>
</html>