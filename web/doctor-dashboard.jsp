<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    // Check if doctor is logged in
    String doctorFullName = (String) session.getAttribute("doctorFullName");
    Integer doctorId = (Integer) session.getAttribute("doctorId");
    if (doctorFullName == null || doctorId == null) {
        response.sendRedirect("doctor-login.jsp?error=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctor Dashboard - Pet Management</title>
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
            background: linear-gradient(135deg, #2196F3, #1976D2);
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
        
        .stats-cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        
        .stat-card {
            background: white;
            border-radius: 10px;
            padding: 25px;
            text-align: center;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }
        
        .stat-number {
            font-size: 2.5em;
            font-weight: bold;
            color: #2196F3;
            margin-bottom: 10px;
        }
        
        .stat-label {
            color: #666;
            font-size: 1.1em;
        }
        
        .appointments-section {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .section-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            border-bottom: 2px solid #f0f0f0;
            padding-bottom: 15px;
        }
        
        .section-title {
            color: #333;
            font-size: 1.8em;
        }
        
        .appointments-table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .appointments-table th,
        .appointments-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        .appointments-table th {
            background: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        
        .appointments-table tr:hover {
            background: #f8f9fa;
        }
        
        .status-badge {
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: bold;
        }
        
        .status-pending {
            background: #fff3cd;
            color: #856404;
        }
        
        .status-confirmed {
            background: #d1ecf1;
            color: #0c5460;
        }
        
        .status-completed {
            background: #d4edda;
            color: #155724;
        }
        
        .status-cancelled {
            background: #f8d7da;
            color: #721c24;
        }
        
        .action-btn {
            padding: 6px 12px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 12px;
            margin-right: 5px;
            transition: all 0.3s ease;
        }
        
        .confirm-btn {
            background: #28a745;
            color: white;
        }
        
        .confirm-btn:hover {
            background: #218838;
        }
        
        .complete-btn {
            background: #17a2b8;
            color: white;
        }
        
        .complete-btn:hover {
            background: #138496;
        }
        
        .no-appointments {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        
        .doctor-info {
            background: white;
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .doctor-info h2 {
            color: #333;
            margin-bottom: 15px;
            border-bottom: 2px solid #2196F3;
            padding-bottom: 10px;
        }
        
        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
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
                👨‍⚕️ Welcome, Dr. <%= doctorFullName %>!
            </div>
            <div class="nav-links">
                <a href="doctor-dashboard.jsp">Dashboard</a>
                <a href="DoctorLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
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
        
        <div class="doctor-info">
            <h2>Your Profile</h2>
            <div class="info-grid">
                <div class="info-item">
                    <span class="info-label">Full Name:</span>
                    <span class="info-value">Dr. <%= doctorFullName %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Specialization:</span>
                    <span class="info-value"><%= session.getAttribute("doctorSpecialization") %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Email:</span>
                    <span class="info-value"><%= session.getAttribute("doctorEmail") %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">Phone:</span>
                    <span class="info-value"><%= session.getAttribute("doctorPhone") %></span>
                </div>
                <div class="info-item">
                    <span class="info-label">License Number:</span>
                    <span class="info-value"><%= session.getAttribute("doctorLicense") %></span>
                </div>
            </div>
        </div>
        
        <div class="stats-cards">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Count pending appointments
                    String pendingSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ? AND status = 'PENDING'";
                    pstmt = conn.prepareStatement(pendingSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int pendingCount = rs.next() ? rs.getInt("count") : 0;
                    rs.close();
                    pstmt.close();
                    
                    // Count confirmed appointments
                    String confirmedSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ? AND status = 'CONFIRMED'";
                    pstmt = conn.prepareStatement(confirmedSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int confirmedCount = rs.next() ? rs.getInt("count") : 0;
                    rs.close();
                    pstmt.close();
                    
                    // Count total appointments
                    String totalSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE doctor_id = ?";
                    pstmt = conn.prepareStatement(totalSQL);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    int totalCount = rs.next() ? rs.getInt("count") : 0;
            %>
            <div class="stat-card">
                <div class="stat-number"><%= pendingCount %></div>
                <div class="stat-label">Pending Appointments</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= confirmedCount %></div>
                <div class="stat-label">Confirmed Appointments</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= totalCount %></div>
                <div class="stat-label">Total Appointments</div>
            </div>
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if (rs != null) rs.close(); } catch (Exception e) {}
                    try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
                    try { if (conn != null) conn.close(); } catch (Exception e) {}
                }
            %>
        </div>
        
        <div class="appointments-section">
            <div class="section-header">
                <h2 class="section-title">Your Appointments</h2>
            </div>
            
            <%
                conn = null;
                pstmt = null;
                rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT a.*, p.name as pet_name, p.species, p.breed, u.full_name as owner_name " +
                                "FROM appointmentsnew a " +
                                "JOIN petsnew p ON a.pet_id = p.pet_id " +
                                "JOIN user202 u ON p.user_id = u.user_id " +
                                "WHERE a.doctor_id = ? " +
                                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, doctorId);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
            %>
                        <table class="appointments-table">
                            <thead>
                                <tr>
                                    <th>Appointment ID</th>
                                    <th>Pet & Owner</th>
                                    <th>Date & Time</th>
                                    <th>Reason</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
            <%
                        do {
                            String status = rs.getString("status");
                            String statusClass = "status-" + status.toLowerCase();
                            java.util.Date appDate = rs.getDate("appointment_date");
                            String formattedDate = new java.text.SimpleDateFormat("MMM dd, yyyy").format(appDate);
            %>
                            <tr>
                                <td>#<%= rs.getInt("appointment_id") %></td>
                                <td>
                                    <strong><%= rs.getString("pet_name") %></strong><br>
                                    (<%= rs.getString("species") %> - <%= rs.getString("breed") != null ? rs.getString("breed") : "N/A" %>)<br>
                                    Owner: <%= rs.getString("owner_name") %>
                                </td>
                                <td>
                                    <%= formattedDate %><br>
                                    <%= rs.getString("appointment_time") %>
                                </td>
                                <td><%= rs.getString("reason") %></td>
                                <td>
                                    <span class="status-badge <%= statusClass %>">
                                        <%= status %>
                                    </span>
                                </td>
                                <td>
                                    <%
                                        if ("PENDING".equals(status)) {
                                    %>
                                        <a href="ConfirmAppointmentServletnew?id=<%= rs.getInt("appointment_id") %>" 
                                           class="action-btn confirm-btn">Confirm</a>
                                    <%
                                        } else if ("CONFIRMED".equals(status)) {
                                    %>
                                        <a href="CompleteAppointmentServletnew?id=<%= rs.getInt("appointment_id") %>" 
                                           class="action-btn complete-btn">Complete</a>
                                    <%
                                        }
                                    %>
                                </td>
                            </tr>
            <%
                        } while (rs.next());
            %>
                            </tbody>
                        </table>
            <%
                    } else {
            %>
                        <div class="no-appointments">
                            <h3>No appointments found</h3>
                            <p>You don't have any appointments scheduled yet.</p>
                        </div>
            <%
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                    <div class="message error">
                        Error loading appointments: <%= e.getMessage() %>
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