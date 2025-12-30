<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctor Registration - Pet Management</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #2196F3, #1976D2);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        
        .container {
            display: flex;
            max-width: 1000px;
            background: white;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 15px 30px rgba(0,0,0,0.2);
        }
        
        .welcome-section {
            flex: 1;
            background: linear-gradient(135deg, #2196F3, #1976D2);
            color: white;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        
        .welcome-section h1 {
            font-size: 2.2em;
            margin-bottom: 20px;
        }
        
        .welcome-section p {
            line-height: 1.6;
            margin-bottom: 15px;
        }
        
        .form-section {
            flex: 1;
            padding: 40px;
        }
        
        .form-section h2 {
            color: #333;
            margin-bottom: 25px;
            text-align: center;
            font-size: 1.8em;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
            font-size: 14px;
        }
        
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            transition: border-color 0.3s ease;
        }
        
        .form-group input:focus, .form-group select:focus {
            border-color: #2196F3;
            outline: none;
        }
        
        .btn {
            width: 100%;
            padding: 12px;
            background: #2196F3;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease;
            margin-top: 10px;
        }
        
        .btn:hover {
            background: #1976D2;
        }
        
        .switch-form {
            text-align: center;
            margin-top: 20px;
        }
        
        .switch-form a {
            color: #2196F3;
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
            font-size: 14px;
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
        
        .form-row {
            display: flex;
            gap: 15px;
        }
        
        .form-row .form-group {
            flex: 1;
        }
        
        .info-note {
            background: #e3f2fd;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
            font-size: 14px;
            color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="welcome-section">
            <h1>Join as Veterinarian!</h1>
            <p>Register now to join our network of professional veterinarians and provide quality care to pets.</p>
            <p>✓ Manage your appointments</p>
            <p>✓ Access medical records</p>
            <p>✓ Set your availability</p>
            <p>✓ Grow your practice</p>
            <p>✓ 24/7 platform access</p>
        </div>
        
        <div class="form-section">
            <h2>Doctor Registration</h2>
            
            <div class="info-note">
                ⓘ Your registration will be reviewed by admin before approval.
            </div>
            
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
            
            <form action="DoctorRegisterServletnew" method="POST">
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="username">Username *</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address *</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="password">Password *</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password *</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="specialization">Specialization *</label>
                    <select id="specialization" name="specialization" required>
                        <option value="">Select Specialization</option>
                        <option value="General Veterinary">General Veterinary</option>
                        <option value="Surgery">Surgery</option>
                        <option value="Dermatology">Dermatology</option>
                        <option value="Dentistry">Dentistry</option>
                        <option value="Ophthalmology">Ophthalmology</option>
                        <option value="Cardiology">Cardiology</option>
                        <option value="Neurology">Neurology</option>
                        <option value="Oncology">Oncology</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone">Phone Number *</label>
                        <input type="tel" id="phone" name="phone" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="licenseNumber">License Number *</label>
                        <input type="text" id="licenseNumber" name="licenseNumber" required>
                    </div>
                </div>
                
                <button type="submit" class="btn">Register as Doctor</button>
            </form>
            
            <div class="switch-form">
                <p>Already have an account? <a href="doctor-login.jsp">Login here</a></p>
                <p><a href="index.jsp">← Back to Home</a></p>
            </div>
        </div>
    </div>

    <script>
        // Client-side password validation
        document.querySelector('form').addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (password !== confirmPassword) {
                e.preventDefault();
                alert('Passwords do not match!');
                return false;
            }
            
            if (password.length < 6) {
                e.preventDefault();
                alert('Password must be at least 6 characters long!');
                return false;
            }
        });
    </script>
</body>
</html>