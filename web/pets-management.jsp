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
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My Pets - Pet Management</title>
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
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        
        .page-title {
            color: #333;
            font-size: 2em;
        }
        
        .btn {
            padding: 12px 25px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background 0.3s ease;
        }
        
        .btn:hover {
            background: #45a049;
        }
        
        .pets-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 25px;
            margin-top: 20px;
        }
        
        .pet-card {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
            border-left: 5px solid #4CAF50;
        }
        
        .pet-card:hover {
            transform: translateY(-5px);
        }
        
        .pet-name {
            color: #333;
            font-size: 1.4em;
            margin-bottom: 10px;
            border-bottom: 2px solid #f0f0f0;
            padding-bottom: 10px;
        }
        
        .pet-details {
            margin-bottom: 15px;
        }
        
        .pet-detail {
            display: flex;
            justify-content: space-between;
            margin-bottom: 8px;
            padding: 5px 0;
        }
        
        .detail-label {
            font-weight: bold;
            color: #555;
        }
        
        .detail-value {
            color: #333;
        }
        
        .pet-actions {
            display: flex;
            gap: 10px;
            margin-top: 15px;
        }
        
        .action-btn {
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        
        .edit-btn {
            background: #2196F3;
            color: white;
        }
        
        .edit-btn:hover {
            background: #1976D2;
        }
        
        .delete-btn {
            background: #f44336;
            color: white;
        }
        
        .delete-btn:hover {
            background: #d32f2f;
        }
        
        .no-pets {
            text-align: center;
            padding: 50px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .no-pets h3 {
            color: #666;
            margin-bottom: 20px;
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
                🐾 My Pets
            </div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pets-add.jsp">Add Pet</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">My Pets</h1>
            <a href="pets-add.jsp" class="btn">+ Add New Pet</a>
        </div>
        
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
        
        <div class="pets-grid">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT * FROM petsnew WHERE user_id = ? ORDER BY pet_id DESC";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, userId);
                    rs = pstmt.executeQuery();
                    
                    boolean hasPets = false;
                    
                    while (rs.next()) {
                        hasPets = true;
            %>
                        <div class="pet-card">
                            <div class="pet-name">
                                <%= rs.getString("name") %>
                                <span style="float: right;">
                                    <% 
                                        String species = rs.getString("species");
                                        if ("Dog".equals(species)) out.print("🐕");
                                        else if ("Cat".equals(species)) out.print("🐈");
                                        else if ("Bird".equals(species)) out.print("🐦");
                                        else if ("Rabbit".equals(species)) out.print("🐇");
                                        else out.print("🐾");
                                    %>
                                </span>
                            </div>
                            
                            <div class="pet-details">
                                <div class="pet-detail">
                                    <span class="detail-label">Species:</span>
                                    <span class="detail-value"><%= rs.getString("species") %></span>
                                </div>
                                <div class="pet-detail">
                                    <span class="detail-label">Breed:</span>
                                    <span class="detail-value"><%= rs.getString("breed") != null ? rs.getString("breed") : "Not specified" %></span>
                                </div>
                                <div class="pet-detail">
                                    <span class="detail-label">Age:</span>
                                    <span class="detail-value">
                                        <%= rs.getInt("age") > 0 ? rs.getInt("age") + " years" : "Not specified" %>
                                    </span>
                                </div>
                                <div class="pet-detail">
                                    <span class="detail-label">Weight:</span>
                                    <span class="detail-value">
                                        <%= rs.getDouble("weight") > 0 ? rs.getDouble("weight") + " kg" : "Not specified" %>
                                    </span>
                                </div>
                                <div class="pet-detail">
                                    <span class="detail-label">Color:</span>
                                    <span class="detail-value"><%= rs.getString("color") != null ? rs.getString("color") : "Not specified" %></span>
                                </div>
                            </div>
                            
                            <div class="pet-actions">
                                <a href="edit-pets.jsp?id=<%= rs.getInt("pet_id") %>" class="action-btn edit-btn">Edit</a>
                                <a href="DeletePetServletnew?id=<%= rs.getInt("pet_id") %>" class="action-btn delete-btn" 
                                   onclick="return confirm('Are you sure you want to delete <%= rs.getString("name") %>?')">Delete</a>
                            </div>
                        </div>
            <%
                    }
                    
                    if (!hasPets) {
            %>
                        <div class="no-pets">
                            <h3>No pets found</h3>
                            <p>You haven't added any pets yet.</p>
                            <a href="pets-add.jsp" class="btn" style="margin-top: 15px;">Add Your First Pet</a>
                        </div>
            <%
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                    <div class="message error">
                        Error loading pets: <%= e.getMessage() %>
                    </div>
            <%
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception e) {}
                    try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                    try { if (conn != null) conn.close(); } catch (Exception e) {}
                }
            %>
        </div>
    </div>
</body>
</html>