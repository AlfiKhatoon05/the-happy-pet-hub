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
    <title>Medical Records - Pet Management</title>
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
        
        .back-btn {
            background: #6c757d;
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 5px;
        }
        
        .back-btn:hover {
            background: #5a6268;
        }
        
        .pets-section {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }
        
        .section-title {
            color: #333;
            margin-bottom: 20px;
            font-size: 1.5em;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        
        .pets-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }
        
        .pet-card {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            border-left: 4px solid #4CAF50;
            transition: transform 0.3s ease;
        }
        
        .pet-card:hover {
            transform: translateY(-2px);
        }
        
        .pet-name {
            color: #333;
            font-size: 1.3em;
            font-weight: bold;
            margin-bottom: 10px;
        }
        
        .pet-details {
            margin-bottom: 15px;
        }
        
        .pet-detail {
            display: flex;
            justify-content: space-between;
            margin-bottom: 5px;
            padding: 3px 0;
        }
        
        .detail-label {
            font-weight: bold;
            color: #555;
        }
        
        .detail-value {
            color: #333;
        }
        
        .view-records-btn {
            display: inline-block;
            padding: 8px 15px;
            background: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: background 0.3s ease;
        }
        
        .view-records-btn:hover {
            background: #45a049;
        }
        
        .records-section {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .no-pets {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        
        .no-pets h3 {
            margin-bottom: 15px;
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
        
        .medical-records-list {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        
        .record-card {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            border-left: 4px solid #2196F3;
        }
        
        .record-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        
        .record-title {
            color: #333;
            font-size: 1.2em;
            font-weight: bold;
        }
        
        .record-date {
            color: #666;
            font-size: 0.9em;
        }
        
        .record-details {
            margin-bottom: 15px;
        }
        
        .record-detail {
            margin-bottom: 8px;
        }
        
        .record-label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 3px;
        }
        
        .record-value {
            color: #333;
            line-height: 1.5;
        }
        
        .no-records {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        
        .no-records h3 {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">
                🏥 Medical Records
            </div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pets-management.jsp">My Pets</a>
                <a href="view-appointments.jsp">Appointments</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">Medical Records</h1>
            <a href="user-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
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
        
        <div class="pets-section">
            <h2 class="section-title">Your Pets</h2>
            <div class="pets-grid">
                <%
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get user's pets
                        String sql = "SELECT * FROM petsnew WHERE user_id = ? ORDER BY name";
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
                                </div>
                                
                                <a href="medical-records.jsp?petId=<%= rs.getInt("pet_id") %>" class="view-records-btn">
                                    View Medical Records
                                </a>
                            </div>
                <%
                        }
                        
                        if (!hasPets) {
                %>
                            <div class="no-pets">
                                <h3>No pets found</h3>
                                <p>You haven't added any pets yet.</p>
                                <a href="pets-add.jsp" class="view-records-btn">Add Your First Pet</a>
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
        
        <%
            // Check if specific pet is selected
            String petIdParam = request.getParameter("petId");
            if (petIdParam != null && !petIdParam.isEmpty()) {
                int selectedPetId = Integer.parseInt(petIdParam);
        %>
                <div class="records-section">
                    <h2 class="section-title">Medical Records</h2>
                    <div class="medical-records-list">
                        <%
                            Connection conn2 = null;
                            PreparedStatement pstmt2 = null;
                            ResultSet rs2 = null;
                            ResultSet rs3 = null;
                            
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                conn2 = DriverManager.getConnection(
                                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                                
                                // Get pet details
                                String petSQL = "SELECT * FROM petsnew WHERE pet_id = ?";
                                pstmt2 = conn2.prepareStatement(petSQL);
                                pstmt2.setInt(1, selectedPetId);
                                rs3 = pstmt2.executeQuery();
                                
                                String petName = "";
                                if (rs3.next()) {
                                    petName = rs3.getString("name");
                                }
                                rs3.close();
                                pstmt2.close();
                                
                                // Get medical records for this pet
                                // For now, we'll show appointment history as medical records
                                String recordsSQL = "SELECT a.*, d.full_name as doctor_name, d.specialization " +
                                                  "FROM appointmentsnew a " +
                                                  "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                                                  "WHERE a.pet_id = ? AND a.status = 'COMPLETED' " +
                                                  "ORDER BY a.appointment_date DESC";
                                pstmt2 = conn2.prepareStatement(recordsSQL);
                                pstmt2.setInt(1, selectedPetId);
                                rs2 = pstmt2.executeQuery();
                                
                                boolean hasRecords = false;
                                
                                while (rs2.next()) {
                                    hasRecords = true;
                                    java.util.Date appDate = rs2.getDate("appointment_date");
                                    String formattedDate = new java.text.SimpleDateFormat("MMMM dd, yyyy").format(appDate);
                        %>
                                    <div class="record-card">
                                        <div class="record-header">
                                            <div class="record-title">
                                                Veterinary Visit - <%= formattedDate %>
                                            </div>
                                            <div class="record-date">
                                                <%= formattedDate %>
                                            </div>
                                        </div>
                                        
                                        <div class="record-details">
                                            <div class="record-detail">
                                                <span class="record-label">Pet:</span>
                                                <span class="record-value"><%= petName %></span>
                                            </div>
                                            <div class="record-detail">
                                                <span class="record-label">Veterinarian:</span>
                                                <span class="record-value">Dr. <%= rs2.getString("doctor_name") %> (<%= rs2.getString("specialization") %>)</span>
                                            </div>
                                            <div class="record-detail">
                                                <span class="record-label">Reason for Visit:</span>
                                                <span class="record-value"><%= rs2.getString("reason") %></span>
                                            </div>
                                            <div class="record-detail">
                                                <span class="record-label">Treatment Notes:</span>
                                                <span class="record-value">
                                                    <%= rs2.getString("notes") != null ? rs2.getString("notes") : "No detailed notes available." %>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                        <%
                                }
                                
                                if (!hasRecords) {
                        %>
                                    <div class="no-records">
                                        <h3>No medical records found</h3>
                                        <p>No completed appointments found for <%= petName %>.</p>
                                        <p>Medical records will appear here after completed veterinary visits.</p>
                                    </div>
                        <%
                                }
                                
                            } catch (Exception e) {
                                e.printStackTrace();
                        %>
                                <div class="message error">
                                    Error loading medical records: <%= e.getMessage() %>
                                </div>
                        <%
                            } finally {
                                try { if (rs2 != null) rs2.close(); } catch (Exception e) {}
                                try { if (rs3 != null) rs3.close(); } catch (Exception e) {}
                                try { if (pstmt2 != null) pstmt2.close(); } catch (Exception e) {}
                                try { if (conn2 != null) conn2.close(); } catch (Exception e) {}
                            }
                        %>
                    </div>
                </div>
        <%
            }
        %>
    </div>
</body>
</html>