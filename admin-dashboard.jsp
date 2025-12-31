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
    
    // Initialize variables
    int usersCount = 0;
    int doctorsCount = 0;
    int pendingCount = 0;
    int petsCount = 0;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard - Pet Management</title>
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
            background: linear-gradient(135deg, #FF9800, #F57C00);
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
            color: #FF9800;
            margin-bottom: 10px;
        }
        
        .stat-label {
            color: #666;
            font-size: 1.1em;
        }
        
        .admin-sections {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 25px;
            margin-top: 30px;
        }
        
        .section-card {
            background: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            text-align: center;
        }
        
        .section-card h3 {
            color: #333;
            margin-bottom: 15px;
            font-size: 1.4em;
        }
        
        .section-card p {
            color: #666;
            margin-bottom: 20px;
            line-height: 1.5;
        }
        
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background: #FF9800;
            color: white;
            text-decoration: none;
            border-radius: 20px;
            font-weight: bold;
            transition: background 0.3s ease;
        }
        
        .btn:hover {
            background: #F57C00;
        }
        
        .pending-alert {
            background: #ffebee;
            color: #c62828;
            padding: 10px;
            border-radius: 5px;
            margin-top: 10px;
            font-size: 14px;
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
                ⚙️ Admin Dashboard - Welcome, <%= adminFullName %>!
            </div>
            <div class="nav-links">
                <a href="admin-dashboard.jsp">Dashboard</a>
                <a href="AdminLogoutServletnew">Logout</a>
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
        
        <div class="stats-cards">
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                    
                    // Count total users
                    String usersSQL = "SELECT COUNT(*) as count FROM user202";
                    pstmt = conn.prepareStatement(usersSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) usersCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count total doctors
                    String doctorsSQL = "SELECT COUNT(*) as count FROM doctors";
                    pstmt = conn.prepareStatement(doctorsSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) doctorsCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count pending doctors
                    String pendingSQL = "SELECT COUNT(*) as count FROM doctors WHERE is_approved = 0";
                    pstmt = conn.prepareStatement(pendingSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) pendingCount = rs.getInt("count");
                    rs.close();
                    pstmt.close();
                    
                    // Count total pets
                    String petsSQL = "SELECT COUNT(*) as count FROM petsnew";
                    pstmt = conn.prepareStatement(petsSQL);
                    rs = pstmt.executeQuery();
                    if (rs.next()) petsCount = rs.getInt("count");
            %>
            <div class="stat-card">
                <div class="stat-number"><%= usersCount %></div>
                <div class="stat-label">Total Users</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= doctorsCount %></div>
                <div class="stat-label">Total Doctors</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= pendingCount %></div>
                <div class="stat-label">Pending Approvals</div>
            </div>
            <div class="stat-card">
                <div class="stat-number"><%= petsCount %></div>
                <div class="stat-label">Total Pets</div>
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
        
        <div class="admin-sections">
            <div class="section-card">
                <h3>👨‍⚕️ Doctor Management</h3>
                <p>Approve or reject doctor registrations and manage doctor accounts</p>
                <a href="doctor-approval.jsp" class="btn">Manage Doctors</a>
                <%
                    if (pendingCount > 0) {
                %>
                    <div class="pending-alert">
                        ⚠️ <%= pendingCount %> doctors pending approval
                    </div>
                <%
                    }
                %>
            </div>
            
            <div class="section-card">
                <h3>👥 User Management</h3>
                <p>View all registered users, their pets, and manage user accounts</p>
                <a href="user-management.jsp" class="btn">Manage Users</a>
            </div>
            
            <div class="section-card">
                <h3>📊 System Reports</h3>
                <p>View system statistics, appointment reports, and generate analytics</p>
                <a href="system-reports.jsp" class="btn">View Reports</a>
            </div>
            
            <div class="section-card">
                <h3>📅 Appointments</h3>
                <p>Monitor all appointments across the system and view booking trends</p>
                <a href="appointment-management.jsp" class="btn">View Appointments</a>
            </div>
        </div>
    </div>
</body>
</html>