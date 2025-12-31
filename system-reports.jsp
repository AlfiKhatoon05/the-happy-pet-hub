<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
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
    <title>System Reports - Admin Panel</title>
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
        .stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-bottom: 30px; }
        .stat-card { background: white; border-radius: 10px; padding: 25px; text-align: center; box-shadow: 0 3px 10px rgba(0,0,0,0.1); }
        .stat-number { font-size: 2.5em; font-weight: bold; color: #FF9800; margin-bottom: 10px; }
        .stat-label { color: #666; font-size: 1.1em; }
        .reports-section { background: white; border-radius: 15px; padding: 30px; box-shadow: 0 5px 15px rgba(0,0,0,0.1); }
        .section-title { color: #333; margin-bottom: 20px; font-size: 1.5em; border-bottom: 2px solid #FF9800; padding-bottom: 10px; }
        .report-item { margin-bottom: 20px; padding: 15px; background: #f8f9fa; border-radius: 8px; }
        .report-label { font-weight: bold; color: #555; margin-bottom: 5px; }
        .report-value { color: #333; }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">📊 System Reports</div>
            <div class="nav-links">
                <a href="admin-dashboard.jsp">Dashboard</a>
                <a href="AdminLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">System Reports & Analytics</h1>
            <a href="admin-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
        </div>
        
        <div class="stats-grid">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Total Users
                    String usersSQL = "SELECT COUNT(*) as count FROM user202";
                    pstmt = conn.prepareStatement(usersSQL);
                    rs = pstmt.executeQuery();
                    int totalUsers = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Doctors
                    String doctorsSQL = "SELECT COUNT(*) as count FROM doctors";
                    pstmt = conn.prepareStatement(doctorsSQL);
                    rs = pstmt.executeQuery();
                    int totalDoctors = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Pets
                    String petsSQL = "SELECT COUNT(*) as count FROM petsnew";
                    pstmt = conn.prepareStatement(petsSQL);
                    rs = pstmt.executeQuery();
                    int totalPets = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                    
                    // Total Appointments
                    String appointmentsSQL = "SELECT COUNT(*) as count FROM appointmentsnew";
                    pstmt = conn.prepareStatement(appointmentsSQL);
                    rs = pstmt.executeQuery();
                    int totalAppointments = rs.next() ? rs.getInt("count") : 0;
            %>
            <div class="stat-card">
                <div class="stat-number"><%= totalUsers %></div>
                <div class="stat-label">Total Users</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= totalDoctors %></div>
                <div class="stat-label">Total Doctors</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= totalPets %></div>
                <div class="stat-label">Total Pets</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= totalAppointments %></div>
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
        
        <div class="reports-section">
            <h2 class="section-title">System Overview</h2>
            <%
                conn = null; pstmt = null; rs = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Appointment Status Breakdown
                    String statusSQL = "SELECT status, COUNT(*) as count FROM appointmentsnew GROUP BY status";
                    pstmt = conn.prepareStatement(statusSQL);
                    rs = pstmt.executeQuery();
            %>
            <div class="report-item">
                <div class="report-label">Appointment Status Breakdown:</div>
                <%
                    while (rs.next()) {
                %>
                    <div class="report-value">- <%= rs.getString("status") %>: <%= rs.getInt("count") %> appointments</div>
                <%
                    }
                    rs.close(); pstmt.close();
                    
                    // Recent Registrations (last 7 days)
                    String recentSQL = "SELECT COUNT(*) as count FROM user202 WHERE created_date >= SYSDATE - 7";
                    pstmt = conn.prepareStatement(recentSQL);
                    rs = pstmt.executeQuery();
                    int recentUsers = rs.next() ? rs.getInt("count") : 0;
                    rs.close(); pstmt.close();
                %>
            </div>
            
            <div class="report-item">
                <div class="report-label">New Users (Last 7 days):</div>
                <div class="report-value"><%= recentUsers %> new registrations</div>
            </div>
            
            <div class="report-item">
                <div class="report-label">Doctor Approval Status:</div>
                <%
                    // Doctor approval stats
                    String doctorStatsSQL = "SELECT is_approved, COUNT(*) as count FROM doctors GROUP BY is_approved";
                    pstmt = conn.prepareStatement(doctorStatsSQL);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()) {
                        int status = rs.getInt("is_approved");
                        String statusText = status == 0 ? "Pending" : (status == 1 ? "Approved" : "Rejected");
                %>
                    <div class="report-value">- <%= statusText %>: <%= rs.getInt("count") %> doctors</div>
                <%
                    }
                %>
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
    </div>
</body>
</html>