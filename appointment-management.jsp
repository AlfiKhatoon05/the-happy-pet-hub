<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
    // Check if admin is logged in
    String adminFullName = (String) session.getAttribute("adminFullName");
    Integer adminId = (Integer) session.getAttribute("adminId");
    if (adminFullName == null || adminId == null) {
        response.sendRedirect("admin-login.jsp?error=Please login first");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Appointment Management - Admin Panel</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; }
        .header { background: linear-gradient(135deg, #FF9800, #F57C00); color: white; padding: 20px; }
        .header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }
        .welcome { font-size: 1.5em; font-weight: bold; }
        .nav-links { display: flex; gap: 20px; }
        .nav-links a { color: white; text-decoration: none; padding: 8px 15px; border-radius: 20px; transition: background 0.3s ease; }
        .nav-links a:hover { background: rgba(255,255,255,0.2); }
        .container { max-width: 1200px; margin: 30px auto; padding: 0 20px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .page-title { color: #333; font-size: 2em; }
        .back-btn { background: #6c757d; color: white; text-decoration: none; padding: 10px 20px; border-radius: 5px; }
        .back-btn:hover { background: #5a6268; }
        .appointments-table { width: 100%; background: white; border-radius: 15px; padding: 25px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        .appointments-table table { width: 100%; border-collapse: collapse; }
        .appointments-table th, .appointments-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }
        .appointments-table th { background: #f8f9fa; font-weight: bold; color: #333; }
        .appointments-table tr:hover { background: #f8f9fa; }
        .status-badge { padding: 6px 12px; border-radius: 20px; font-size: 12px; font-weight: bold; }
        .status-pending { background: #fff3cd; color: #856404; }
        .status-confirmed { background: #d1ecf1; color: #0c5460; }
        .status-completed { background: #d4edda; color: #155724; }
        .status-cancelled { background: #f8d7da; color: #721c24; }
        .stats-cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }
        .stat-card { background: white; border-radius: 10px; padding: 25px; text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.1); }
        .stat-number { font-size: 2.5em; font-weight: bold; color: #FF9800; margin-bottom: 10px; }
        .stat-label { color: #666; font-size: 1.1em; }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">📅 Appointment Management</div>
            <div class="nav-links">
                <a href="admin-dashboard.jsp">Dashboard</a>
                <a href="AdminLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">All Appointments</h1>
            <a href="admin-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
        
        <div class="stats-cards">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                int totalAppointments = 0;
                int todayAppointments = 0;
                int pendingAppointments = 0;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Total appointments
                    String totalSQL = "SELECT COUNT(*) as count FROM appointmentsnew";
                    pstmt = conn.prepareStatement(totalSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) totalAppointments = rs.getInt("count");
                    rs.close(); pstmt.close();
                    
                    // Today's appointments
                    String todaySQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE TRUNC(appointment_date) = TRUNC(SYSDATE)";
                    pstmt = conn.prepareStatement(todaySQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) todayAppointments = rs.getInt("count");
                    rs.close(); pstmt.close();
                    
                    // Pending appointments
                    String pendingSQL = "SELECT COUNT(*) as count FROM appointmentsnew WHERE status = 'PENDING'";
                    pstmt = conn.prepareStatement(pendingSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) pendingAppointments = rs.getInt("count");
            %>
            <div class="stat-card">
                <div class="stat-number"><%= totalAppointments %></div>
                <div class="stat-label">Total Appointments</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= todayAppointments %></div>
                <div class="stat-label">Today's Appointments</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= pendingAppointments %></div>
                <div class="stat-label">Pending Approval</div>
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
        
        <div class="appointments-table">
            <%
                conn = null; pstmt = null; rs = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    String sql = "SELECT a.*, p.name as pet_name, p.species, u.full_name as owner_name, " +
                                "d.full_name as doctor_name, d.specialization " +
                                "FROM appointmentsnew a " +
                                "JOIN petsnew p ON a.pet_id = p.pet_id " +
                                "JOIN user202 u ON p.user_id = u.user_id " +
                                "JOIN doctors d ON a.doctor_id = d.doctor_id " +
                                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
            %>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Pet & Owner</th>
                                    <th>Doctor</th>
                                    <th>Date & Time</th>
                                    <th>Reason</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
            <%
                        do {
                            String status = rs.getString("status");
                            String statusClass = "status-" + status.toLowerCase();
                            java.util.Date appDate = rs.getDate("appointment_date");
                            String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(appDate);
            %>
                            <tr>
                                <td>#<%= rs.getInt("appointment_id") %></td>
                                <td>
                                    <strong><%= rs.getString("pet_name") %></strong><br>
                                    (<%= rs.getString("species") %>)<br>
                                    Owner: <%= rs.getString("owner_name") %>
                                </td>
                                <td>
                                    Dr. <%= rs.getString("doctor_name") %><br>
                                    <%= rs.getString("specialization") %>
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
                            </tr>
            <%
                        } while (rs.next());
            %>
                            </tbody>
                        </table>
            <%
                    } else {
            %>
                        <div style="text-align: center; padding: 40px; color: #666;">
                            <h3>No appointments found</h3>
                            <p>No appointments have been booked in the system yet.</p>
                        </div>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
            %>
                    <div style="background: #ffebee; color: #c62828; padding: 15px; border-radius: 8px; text-align: center;">
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