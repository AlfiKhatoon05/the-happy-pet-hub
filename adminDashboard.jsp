<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #2575fc;
            color: white;
            padding: 20px 0;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        nav {
            margin: 40px auto;
            max-width: 400px;
            text-align: center;
        }
        nav ul {
            list-style: none;
            padding: 0;
        }
        nav ul li {
            margin: 15px 0;
        }
        nav ul li a {
            text-decoration: none;
            background-color: #2575fc;
            color: white;
            padding: 12px 20px;
            border-radius: 8px;
            transition: background-color 0.3s ease, transform 0.2s ease;
            display: inline-block;
            width: 200px;
        }
        nav ul li a:hover {
            background-color: #1a5edb;
            transform: scale(1.05);
        }
        footer {
            text-align: center;
            margin-top: 50px;
            color: #777;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome Admin</h1>
    </header>
    <nav>
        <ul>
            <li><a href="viewUsers.jsp">View & Approve Users</a></li>
            <li><a href="manageProducts.jsp">Manage Products</a></li>
            <li><a href="managePets.jsp">Manage Pets</a></li>
            <li><a href="manageMedicines.jsp">Manage Medicines</a></li>
            <li><a href="index.jsp">Logout</a></li>
        </ul>
    </nav>
    <footer>
        &copy; 2025 Admin Panel
    </footer>
</body>
</html>
