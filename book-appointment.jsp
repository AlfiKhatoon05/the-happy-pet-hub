<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

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
    <meta charset="UTF-8">
    <title>Book Appointment - Pet Management</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
        }

        .header {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            padding: 25px;
            border-radius: 12px;
            margin-bottom: 30px;
        }

        .header-content {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            margin-left: 15px;
            padding: 8px 18px;
            background: rgba(255,255,255,0.2);
            border-radius: 20px;
        }

        .nav-links a:hover {
            background: rgba(255,255,255,0.35);
        }

        .form-card, .doctors-section {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        }

        .main-content {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 30px;
        }

        h2, h3 {
            text-align: center;
            margin-bottom: 20px;
            border-bottom: 3px solid #4CAF50;
            padding-bottom: 10px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            font-weight: 600;
            display: block;
            margin-bottom: 6px;
        }

        input, select, textarea {
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            border: 2px solid #e1e5ee;
        }

        input:focus, select:focus, textarea:focus {
            border-color: #4CAF50;
            outline: none;
        }

        .form-row {
            display: flex;
            gap: 15px;
        }

        .btn {
            padding: 12px 28px;
            border-radius: 8px;
            border: none;
            font-weight: bold;
            cursor: pointer;
            color: white;
        }

        .btn-primary {
            background: #4CAF50;
        }

        .btn-secondary {
            background: #6c757d;
            text-decoration: none;
        }

        .form-actions {
            text-align: center;
            margin-top: 25px;
        }

        .doctor-card {
            background: #f9f9f9;
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 15px;
        }

        .doctor-name {
            font-weight: bold;
            font-size: 18px;
        }

        .message {
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 6px;
            text-align: center;
        }

        .error {
            background: #ffebee;
            color: #c62828;
        }

        .success {
            background: #e8f5e8;
            color: #2e7d32;
        }

        @media(max-width:900px) {
            .main-content {
                grid-template-columns: 1fr;
            }
            .form-row {
                flex-direction: column;
            }
        }
    </style>
</head>

<body>
<div class="container">

    <div class="header">
        <div class="header-content">
            <h2>Book Appointment</h2>
            <div class="nav-links">
                <a href="user-dashboard.jsp">Dashboard</a>
                <a href="pets-management.jsp">My Pets</a>
                <a href="view-appointments.jsp">My Appointments</a>
                <a href="UserLogoutServletnew">Logout</a>
            </div>
        </div>
    </div>

    <div class="main-content">

        <!-- BOOK APPOINTMENT FORM -->
        <div class="form-card">
            <h2>New Appointment</h2>

            <%
                String error = request.getParameter("error");
                String success = request.getParameter("success");
                if (error != null) {
            %>
                <div class="message error"><%= error %></div>
            <% } %>

            <% if (success != null) { %>
                <div class="message success"><%= success %></div>
            <% } %>

            <form action="BookAppointmentServletnew" method="POST">

                <!-- PET -->
                <div class="form-group">
                    <label>Select Pet *</label>
                    <select name="petId" required>
                        <option value="">Select Your Pet</option>
                        <%
                            Connection conn = null;
                            PreparedStatement ps = null;
                            ResultSet rs = null;
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                conn = DriverManager.getConnection(
                                    "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                                ps = conn.prepareStatement(
                                    "SELECT pet_id, name, species FROM petsnew WHERE user_id=?");
                                ps.setInt(1, userId);
                                rs = ps.executeQuery();

                                while (rs.next()) {
                        %>
                            <option value="<%= rs.getInt("pet_id") %>">
                                <%= rs.getString("name") %> (<%= rs.getString("species") %>)
                            </option>
                        <%
                                }
                            } finally {
                                if (rs != null) rs.close();
                                if (ps != null) ps.close();
                                if (conn != null) conn.close();
                            }
                        %>
                    </select>
                </div>

                <!-- DOCTOR -->
                <div class="form-group">
                    <label>Select Doctor *</label>
                    <select name="doctorId" required>
                        <option value="">Select Doctor</option>
                        <%
                            Connection conn2 = null;
                            PreparedStatement ps2 = null;
                            ResultSet rs2 = null;
                            try {
                                Class.forName("oracle.jdbc.driver.OracleDriver");
                                conn2 = DriverManager.getConnection(
                                    "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                                ps2 = conn2.prepareStatement(
                                    "SELECT doctor_id, full_name, specialization FROM doctors WHERE is_approved=1");
                                rs2 = ps2.executeQuery();

                                while (rs2.next()) {
                        %>
                            <option value="<%= rs2.getInt("doctor_id") %>">
                                Dr. <%= rs2.getString("full_name") %> - <%= rs2.getString("specialization") %>
                            </option>
                        <%
                                }
                            } finally {
                                if (rs2 != null) rs2.close();
                                if (ps2 != null) ps2.close();
                                if (conn2 != null) conn2.close();
                            }
                        %>
                    </select>
                </div>

                <!-- DATE & TIME -->
                <div class="form-row">
                    <div class="form-group">
                        <label>Date *</label>
                        <input type="date" name="appointmentDate" required>
                    </div>
                    <div class="form-group">
                        <label>Time *</label>
                        <select name="appointmentTime" required>
                            <option value="">Select Time</option>
                            <option>09:00 AM</option>
                            <option>10:00 AM</option>
                            <option>11:00 AM</option>
                            <option>12:00 PM</option>
                            <option>02:00 PM</option>
                            <option>03:00 PM</option>
                            <option>04:00 PM</option>
                            <option>05:00 PM</option>
                        </select>
                    </div>
                </div>

                <!-- REASON -->
                <div class="form-group">
                    <label>Reason *</label>
                    <textarea name="reason" rows="3" required></textarea>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Book Appointment</button>
                    <a href="user-dashboard.jsp" class="btn btn-secondary">Cancel</a>
                </div>

            </form>
        </div>

        <!-- DOCTORS LIST -->
        <div class="doctors-section">
            <h3>Available Doctors</h3>
            <%
                Connection conn3 = null;
                PreparedStatement ps3 = null;
                ResultSet rs3 = null;
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn3 = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE","system","manager");

                    ps3 = conn3.prepareStatement(
                        "SELECT full_name, specialization FROM doctors WHERE is_approved=1");
                    rs3 = ps3.executeQuery();

                    while (rs3.next()) {
            %>
                <div class="doctor-card">
                    <div class="doctor-name">Dr. <%= rs3.getString("full_name") %></div>
                    <div>Specialization: <%= rs3.getString("specialization") %></div>
                </div>
            <%
                    }
                } finally {
                    if (rs3 != null) rs3.close();
                    if (ps3 != null) ps3.close();
                    if (conn3 != null) conn3.close();
                }
            %>
        </div>

    </div>
</div>

<script>
    document.querySelector("input[type='date']").min =
        new Date().toISOString().split("T")[0];
</script>

</body>
</html>
