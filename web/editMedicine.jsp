<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Medicine</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background-color: #ffffff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 350px;
            text-align: center;
        }
        h2 {
            color: #2575fc;
            margin-bottom: 20px;
        }
        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 10px 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #2575fc;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #1a5edb;
        }
        a {
            text-decoration: none;
            color: #2575fc;
            display: inline-block;
            margin-top: 20px;
            font-size: 14px;
            transition: color 0.3s ease;
        }
        a:hover {
            color: #1a5edb;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Edit Medicine</h2>
        <form action="UpdateMedicineServlet" method="post">
            <input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
            <input type="text" name="name" value="<%= request.getAttribute("name") %>" placeholder="Medicine Name" required>
            
            <input type="text" name="type" value="<%= request.getAttribute("type") %>" placeholder="Type" required>
            
            <input type="date" name="expiry_date" value="<%= request.getAttribute("expiry_date") %>" required>
            
            <input type="submit" value="Update Medicine">
        </form>
        <a href="manageMedicines.jsp">Back to Medicines</a>
    </div>
</body>
</html>
            
