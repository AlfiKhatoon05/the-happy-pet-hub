<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
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
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Pet - Pet Management</title>
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
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            width: 100%;
            max-width: 800px;
            background: white;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.2);
            overflow: hidden;
        }
        
        .header {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            padding: 25px;
            text-align: center;
        }
        
        .header h1 {
            font-size: 2em;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
        }
        
        .header p {
            opacity: 0.9;
        }
        
        .form-container {
            padding: 40px;
        }
        
        .back-link {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            color: #4CAF50;
            text-decoration: none;
            margin-bottom: 25px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        
        .back-link:hover {
            color: #45a049;
            transform: translateX(-5px);
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        .form-row {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
            margin-bottom: 25px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }
        
        .required::after {
            content: " *";
            color: #f44336;
        }
        
        .form-control {
            width: 100%;
            padding: 14px 18px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 16px;
            transition: all 0.3s ease;
            background: #f8f9fa;
        }
        
        .form-control:focus {
            outline: none;
            border-color: #4CAF50;
            background: white;
            box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.1);
        }
        
        select.form-control {
            appearance: none;
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='%23333' viewBox='0 0 16 16'%3E%3Cpath d='M8 11L3 6h10l-5 5z'/%3E%3C/svg%3E");
            background-repeat: no-repeat;
            background-position: right 18px center;
            background-size: 16px;
            padding-right: 50px;
        }
        
        .textarea-container textarea {
            min-height: 120px;
            resize: vertical;
        }
        
        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 15px;
            margin-top: 30px;
            padding-top: 25px;
            border-top: 2px solid #f0f0f0;
        }
        
        .btn {
            padding: 14px 35px;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            text-decoration: none;
        }
        
        .btn-primary {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
        }
        
        .btn-primary:hover {
            background: linear-gradient(135deg, #45a049, #3d8b40);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(76, 175, 80, 0.3);
        }
        
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        
        .btn-secondary:hover {
            background: #5a6268;
            transform: translateY(-2px);
        }
        
        .btn-cancel {
            background: #f44336;
            color: white;
        }
        
        .btn-cancel:hover {
            background: #d32f2f;
            transform: translateY(-2px);
        }
        
        .message {
            padding: 15px;
            margin-bottom: 25px;
            border-radius: 10px;
            text-align: center;
            font-size: 16px;
            animation: slideIn 0.5s ease;
        }
        
        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
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
        
        .icon {
            font-size: 1.2em;
        }
        
        @media (max-width: 768px) {
            .form-row {
                grid-template-columns: 1fr;
            }
            
            .form-container {
                padding: 25px;
            }
            
            .form-actions {
                flex-direction: column;
            }
            
            .btn {
                width: 100%;
            }
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-paw icon"></i> Edit Pet Information</h1>
            <p>Update your pet's details below</p>
        </div>
        
        <div class="form-container">
            <a href="pets-management.jsp" class="back-link">
                <i class="fas fa-arrow-left"></i> Back to My Pets
            </a>
            
            <%-- Display messages --%>
            <%
                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            %>
                <div class="message error">
                    <i class="fas fa-exclamation-circle"></i> <%= error %>
                </div>
            <%
                }
                if (success != null) {
            %>
                <div class="message success">
                    <i class="fas fa-check-circle"></i> <%= success %>
                </div>
            <%
                }
            %>
            
            <form action="UpdatePetServletnew" method="POST">
                <input type="hidden" name="pet_id" value="<%= petId %>">
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="name" class="required">Pet Name</label>
                        <input type="text" 
                               id="name" 
                               name="name" 
                               class="form-control" 
                               value="<%= name %>" 
                               required
                               placeholder="Enter pet name">
                    </div>
                    
                    <div class="form-group">
                        <label for="species" class="required">Species</label>
                        <select id="species" name="species" class="form-control" required>
                            <option value="">Select Species</option>
                            <option value="Dog" <%= species.equals("Dog") ? "selected" : "" %>>Dog 🐕</option>
                            <option value="Cat" <%= species.equals("Cat") ? "selected" : "" %>>Cat 🐈</option>
                            <option value="Bird" <%= species.equals("Bird") ? "selected" : "" %>>Bird 🐦</option>
                            <option value="Rabbit" <%= species.equals("Rabbit") ? "selected" : "" %>>Rabbit 🐇</option>
                            <option value="Fish" <%= species.equals("Fish") ? "selected" : "" %>>Fish 🐠</option>
                            <option value="Hamster" <%= species.equals("Hamster") ? "selected" : "" %>>Hamster 🐹</option>
                            <option value="Other" <%= species.equals("Other") ? "selected" : "" %>>Other 🐾</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="breed">Breed</label>
                        <input type="text" 
                               id="breed" 
                               name="breed" 
                               class="form-control" 
                               value="<%= breed %>"
                               placeholder="e.g., Golden Retriever, Persian">
                    </div>
                    
                    <div class="form-group">
                        <label for="age">Age (years)</label>
                        <input type="number" 
                               id="age" 
                               name="age" 
                               class="form-control" 
                               value="<%= age %>"
                               min="0" 
                               max="50"
                               placeholder="Enter age in years">
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="weight">Weight (kg)</label>
                        <input type="number" 
                               id="weight" 
                               name="weight" 
                               class="form-control" 
                               value="<%= weight %>"
                               min="0" 
                               max="200"
                               step="0.1"
                               placeholder="Enter weight in kilograms">
                    </div>
                    
                    <div class="form-group">
                        <label for="color">Color</label>
                        <input type="text" 
                               id="color" 
                               name="color" 
                               class="form-control" 
                               value="<%= color %>"
                               placeholder="e.g., Brown, White, Black">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="medical_history">Medical History</label>
                    <textarea id="medical_history" 
                              name="medical_history" 
                              class="form-control"
                              placeholder="Enter any medical conditions, allergies, or special care requirements"><%= medicalHistory %></textarea>
                </div>
                
                <div class="form-actions">
                    <a href="pets-management.jsp" class="btn btn-cancel">
                        <i class="fas fa-times"></i> Cancel
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-save"></i> Update Pet
                    </button>
                </div>
            </form>
        </div>
    </div>
    
    <script>
        // Form validation
        document.querySelector('form').addEventListener('submit', function(e) {
            const name = document.getElementById('name').value.trim();
            const species = document.getElementById('species').value;
            
            if (!name) {
                e.preventDefault();
                alert('Please enter pet name');
                return;
            }
            
            if (!species) {
                e.preventDefault();
                alert('Please select species');
                return;
            }
        });
        
        // Real-time validation feedback
        const inputs = document.querySelectorAll('.form-control');
        inputs.forEach(input => {
            input.addEventListener('blur', function() {
                if (this.value.trim()) {
                    this.classList.add('filled');
                    this.classList.remove('error');
                } else if (this.hasAttribute('required')) {
                    this.classList.add('error');
                }
            });
            
            input.addEventListener('input', function() {
                if (this.value.trim()) {
                    this.classList.remove('error');
                }
            });
        });
    </script>
</body>
</html>