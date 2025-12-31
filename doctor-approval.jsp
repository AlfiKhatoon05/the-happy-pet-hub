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
    <title>Doctor Approval - Admin Panel</title>
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
        
        .doctors-list {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        
        .doctor-card {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            border-left: 5px solid #FF9800;
        }
        
        .doctor-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            border-bottom: 2px solid #f0f0f0;
            padding-bottom: 10px;
        }
        
        .doctor-name {
            color: #333;
            font-size: 1.4em;
            font-weight: bold;
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
        
        .status-approved {
            background: #d4edda;
            color: #155724;
        }
        
        .status-rejected {
            background: #f8d7da;
            color: #721c24;
        }
        
        .doctor-details {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 15px;
        }
        
        .detail-item {
            padding: 8px 0;
        }
        
        .detail-label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }
        
        .detail-value {
            color: #333;
        }
        
        .action-buttons {
            display: flex;
            gap: 10px;
            margin-top: 15px;
        }
        
        .btn {
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
        }
        
        .approve-btn {
            background: #28a745;
            color: white;
        }
        
        .approve-btn:hover {
            background: #218838;
        }
        
        .reject-btn {
            background: #dc3545;
            color: white;
        }
        
        .reject-btn:hover {
            background: #c82333;
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
        
        .no-doctors {
            text-align: center;
            padding: 50px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        
        .no-doctors h3 {
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
        
        .tabs {
            display: flex;
            margin-bottom: 20px;
            border-bottom: 2px solid #ddd;
        }
        
        .tab {
            padding: 12px 25px;
            background: #f8f9fa;
            border: none;
            cursor: pointer;
            margin-right: 5px;
            border-radius: 5px 5px 0 0;
            font-weight: bold;
        }
        
        .tab.active {
            background: #FF9800;
            color: white;
        }
        
        .tab-content {
            display: none;
        }
        
        .tab-content.active {
            display: block;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-content">
            <div class="welcome">
                👨‍⚕️ Doctor Approval
            </div>
            <div class="nav-links">
                <a href="admin-dashboard.jsp">Dashboard</a>
                <a href="AdminLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>
    
    <div class="container">
        <div class="page-header">
            <h1 class="page-title">Doctor Management</h1>
            <a href="admin-dashboard.jsp" class="back-btn">← Back to Dashboard</a>
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
        
        <div class="tabs">
            <button class="tab active" onclick="showTab('pending')">Pending Approval</button>
            <button class="tab" onclick="showTab('approved')">Approved Doctors</button>
            <button class="tab" onclick="showTab('all')">All Doctors</button>
        </div>
        
        <!-- Pending Doctors Tab -->
        <div id="pending-tab" class="tab-content active">
            <div class="doctors-list">
                <%
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get pending doctors
                        String sql = "SELECT * FROM doctors WHERE is_approved = 0 ORDER BY created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasPending = false;
                        
                        while (rs.next()) {
                            hasPending = true;
                %>
                            <div class="doctor-card">
                                <div class="doctor-header">
                                    <div class="doctor-name">Dr. <%= rs.getString("full_name") %></div>
                                    <span class="status-badge status-pending">PENDING</span>
                                </div>
                                
                                <div class="doctor-details">
                                    <div class="detail-item">
                                        <span class="detail-label">Username:</span>
                                        <span class="detail-value"><%= rs.getString("username") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Email:</span>
                                        <span class="detail-value"><%= rs.getString("email") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Specialization:</span>
                                        <span class="detail-value"><%= rs.getString("specialization") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Phone:</span>
                                        <span class="detail-value"><%= rs.getString("phone") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">License Number:</span>
                                        <span class="detail-value"><%= rs.getString("license_number") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Registered Date:</span>
                                        <span class="detail-value"><%= rs.getDate("created_date") %></span>
                                    </div>
                                </div>
                                
                                <div class="action-buttons">
                                    <form action="ApproveDoctorServletnew" method="POST" style="display: inline;">
                                        <input type="hidden" name="doctorId" value="<%= rs.getInt("doctor_id") %>">
                                        <button type="submit" class="btn approve-btn">Approve</button>
                                    </form>
                                    <form action="RejectDoctorServletnew" method="POST" style="display: inline;">
                                        <input type="hidden" name="doctorId" value="<%= rs.getInt("doctor_id") %>">
                                        <button type="submit" class="btn reject-btn" onclick="return confirm('Are you sure you want to reject this doctor?')">Reject</button>
                                    </form>
                                </div>
                            </div>
                <%
                        }
                        
                        if (!hasPending) {
                %>
                            <div class="no-doctors">
                                <h3>No pending doctors for approval</h3>
                                <p>All doctor registrations have been processed.</p>
                            </div>
                <%
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                        <div class="message error">
                            Error loading pending doctors: <%= e.getMessage() %>
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
        
        <!-- Approved Doctors Tab -->
        <div id="approved-tab" class="tab-content">
            <div class="doctors-list">
                <%
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get approved doctors
                        String sql = "SELECT * FROM doctors WHERE is_approved = 1 ORDER BY created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasApproved = false;
                        
                        while (rs.next()) {
                            hasApproved = true;
                %>
                            <div class="doctor-card">
                                <div class="doctor-header">
                                    <div class="doctor-name">Dr. <%= rs.getString("full_name") %></div>
                                    <span class="status-badge status-approved">APPROVED</span>
                                </div>
                                
                                <div class="doctor-details">
                                    <div class="detail-item">
                                        <span class="detail-label">Username:</span>
                                        <span class="detail-value"><%= rs.getString("username") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Email:</span>
                                        <span class="detail-value"><%= rs.getString("email") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Specialization:</span>
                                        <span class="detail-value"><%= rs.getString("specialization") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Phone:</span>
                                        <span class="detail-value"><%= rs.getString("phone") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">License Number:</span>
                                        <span class="detail-value"><%= rs.getString("license_number") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Approved Date:</span>
                                        <span class="detail-value"><%= rs.getDate("created_date") %></span>
                                    </div>
                                </div>
                            </div>
                <%
                        }
                        
                        if (!hasApproved) {
                %>
                            <div class="no-doctors">
                                <h3>No approved doctors</h3>
                                <p>No doctors have been approved yet.</p>
                            </div>
                <%
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                        <div class="message error">
                            Error loading approved doctors: <%= e.getMessage() %>
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
        
        <!-- All Doctors Tab -->
        <div id="all-tab" class="tab-content">
            <div class="doctors-list">
                <%
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        conn = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                        
                        // Get all doctors
                        String sql = "SELECT * FROM doctors ORDER BY is_approved, created_date DESC";
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();
                        
                        boolean hasDoctors = false;
                        
                        while (rs.next()) {
                            hasDoctors = true;
                            int approvalStatus = rs.getInt("is_approved");
                            String statusClass = "";
                            String statusText = "";
                            
                            if (approvalStatus == 0) {
                                statusClass = "status-pending";
                                statusText = "PENDING";
                            } else if (approvalStatus == 1) {
                                statusClass = "status-approved";
                                statusText = "APPROVED";
                            } else {
                                statusClass = "status-rejected";
                                statusText = "REJECTED";
                            }
                %>
                            <div class="doctor-card">
                                <div class="doctor-header">
                                    <div class="doctor-name">Dr. <%= rs.getString("full_name") %></div>
                                    <span class="status-badge <%= statusClass %>"><%= statusText %></span>
                                </div>
                                
                                <div class="doctor-details">
                                    <div class="detail-item">
                                        <span class="detail-label">Username:</span>
                                        <span class="detail-value"><%= rs.getString("username") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Email:</span>
                                        <span class="detail-value"><%= rs.getString("email") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Specialization:</span>
                                        <span class="detail-value"><%= rs.getString("specialization") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Phone:</span>
                                        <span class="detail-value"><%= rs.getString("phone") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">License Number:</span>
                                        <span class="detail-value"><%= rs.getString("license_number") %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Status:</span>
                                        <span class="detail-value <%= statusClass %>" style="padding: 4px 8px; border-radius: 10px;"><%= statusText %></span>
                                    </div>
                                    <div class="detail-item">
                                        <span class="detail-label">Registered Date:</span>
                                        <span class="detail-value"><%= rs.getDate("created_date") %></span>
                                    </div>
                                </div>
                                
                                <% if (approvalStatus == 0) { %>
                                    <div class="action-buttons">
                                        <form action="ApproveDoctorServletnew" method="POST" style="display: inline;">
                                            <input type="hidden" name="doctorId" value="<%= rs.getInt("doctor_id") %>">
                                            <button type="submit" class="btn approve-btn">Approve</button>
                                        </form>
                                        <form action="RejectDoctorServletnew" method="POST" style="display: inline;">
                                            <input type="hidden" name="doctorId" value="<%= rs.getInt("doctor_id") %>">
                                            <button type="submit" class="btn reject-btn" onclick="return confirm('Are you sure you want to reject this doctor?')">Reject</button>
                                        </form>
                                    </div>
                                <% } %>
                            </div>
                <%
                        }
                        
                        if (!hasDoctors) {
                %>
                            <div class="no-doctors">
                                <h3>No doctors found</h3>
                                <p>No doctors have registered in the system yet.</p>
                            </div>
                <%
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                        <div class="message error">
                            Error loading all doctors: <%= e.getMessage() %>
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
    </div>

    <script>
        function showTab(tabName) {
            // Hide all tabs
            document.querySelectorAll('.tab-content').forEach(tab => {
                tab.classList.remove('active');
            });
            
            // Remove active class from all tabs
            document.querySelectorAll('.tab').forEach(tab => {
                tab.classList.remove('active');
            });
            
            // Show selected tab and set active
            document.getElementById(tabName + '-tab').classList.add('active');
            event.target.classList.add('active');
        }
    </script>
</body>
</html>