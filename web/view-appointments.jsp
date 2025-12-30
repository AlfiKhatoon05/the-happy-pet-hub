<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    <title>My Appointments - Pet Management</title>
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
        
        .appointments-list {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        
        .appointment-card {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border-left: 5px solid #4CAF50;
        }
        
        .appointment-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            border-bottom: 2px solid #f0f0f0;
            padding-bottom: 10px;
        }
        
        .pet-doctor-info {
            display: flex;
            gap: 30px;
            margin-bottom: 15px;
        }
        
        .info-section {
            flex: 1;
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
        
        .status-badge {
            padding: 6px 12px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
        }
        
        .status-pending {
            background: #fff3cd;
            color: #856404;
            border: 1px solid #ffeaa7;
        }
        
        .status-confirmed {
            background: #d1ecf1;
            color: #0c5460;
            border: 1px solid #bee5eb;
        }
        
        .status-completed {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .status-cancelled {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        .appointment-actions {
            margin-top: 15px;
            display: flex;
            gap: 10px;
        }
        
        .action-btn {
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        
        .cancel-btn {
            background: #f44336;
            color: white;
        }
        
        .cancel-btn:hover {
            background: #d32f2f;
        }
        
        .no-appointments {
            text-align: center;
            padding: 50px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .no-appointments h3 {
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
                📅 My Appointments
            </div>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pets-management.jsp">My Pets</a>
                <a href="book-appointment.jsp">Book Appointment</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">My Appointments</h1>
            <a href="book-appointment.jsp" class="btn">+ Book New Appointment</a>
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
        
        <div class="appointments-list">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT a.*, p.name as pet_name, p.species, d.full_name as doctor_name, d.specialization " +
                                "FROM appointmentsnew a " +
                                "JOIN petsnew p ON a.pet_id = p.pet_id " +
                                "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                                "WHERE p.user_id = ? " +
                                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, userId);
                    rs = pstmt.executeQuery();
                    
                    boolean hasAppointments = false;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                    
                    while (rs.next()) {
                        hasAppointments = true;
                        String status = rs.getString("status");
                        String statusClass = "status-" + status.toLowerCase();
            %>
                        <div class="appointment-card">
                            <div class="appointment-header">
                                <h3>
                                    Appointment #<%= rs.getInt("appointment_id") %>
                                    - 
                                    <%= dateFormat.format(rs.getDate("appointment_date")) %>
                                    at <%= rs.getString("appointment_time") %>
                                </h3>
                                <span class="status-badge <%= statusClass %>">
                                    <%= status %>
                                </span>
                            </div>
                            
                            <div class="pet-doctor-info">
                                <div class="info-section">
                                    <span class="info-label">Pet Information:</span>
                                    <span class="info-value">
                                        <%= rs.getString("pet_name") %> (<%= rs.getString("species") %>)
                                    </span>
                                </div>
                                <div class="info-section">
                                    <span class="info-label">Doctor:</span>
                                    <span class="info-value">
                                        Dr. <%= rs.getString("doctor_name") %> - <%= rs.getString("specialization") %>
                                    </span>
                                </div>
                            </div>
                            
                            <div class="info-section">
                                <span class="info-label">Reason for Visit:</span>
                                <span class="info-value"><%= rs.getString("reason") %></span>
                            </div>
                            
                            <%
                                if ("PENDING".equals(status) || "CONFIRMED".equals(status)) {
                            %>
                                <div class="appointment-actions">
                                    <a href="CancelAppointmentServletnew?id=<%= rs.getInt("appointment_id") %>" 
                                       class="action-btn cancel-btn"
                                       onclick="return confirm('Are you sure you want to cancel this appointment?')">
                                        Cancel Appointment
                                    </a>
                                </div>
                            <%
                                }
                            %>
                        </div>
            <%
                    }
                    
                    if (!hasAppointments) {
            %>
                        <div class="no-appointments">
                            <h3>No appointments found</h3>
                            <p>You haven't booked any appointments yet.</p>
                            <a href="book-appointment.jsp" class="btn" style="margin-top: 15px;">Book Your First Appointment</a>
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