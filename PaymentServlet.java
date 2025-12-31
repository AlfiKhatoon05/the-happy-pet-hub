import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import org.json.JSONObject;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PaymentServlet extends HttpServlet {
    
    private static final String RAZORPAY_KEY_ID = "rzp_test_YOUR_KEY_ID";
    private static final String RAZORPAY_KEY_SECRET = "YOUR_RAZORPAY_SECRET";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("verify".equals(action)) {
            verifyPayment(request, response);
        } else if ("createOrder".equals(action)) {
            createOrder(request, response);
        }
    }
    
    private void verifyPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();
        
        String appointmentId = request.getParameter("appointmentId");
        String razorpayPaymentId = request.getParameter("paymentId");
        String razorpayOrderId = request.getParameter("orderId");
        String razorpaySignature = request.getParameter("signature");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Verify payment signature
            String generatedSignature = generateSignature(razorpayOrderId + "|" + razorpayPaymentId, RAZORPAY_KEY_SECRET);
            
            if (generatedSignature.equals(razorpaySignature)) {
                // Payment verified successfully
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager");
                
                // Update appointment payment status
                String updateAppointmentSql = "UPDATE appointmentsnew SET payment_status = 'PAID', status = 'CONFIRMED' WHERE appointment_id = ?";
                pstmt = conn.prepareStatement(updateAppointmentSql);
                pstmt.setInt(1, Integer.parseInt(appointmentId));
                pstmt.executeUpdate();
                pstmt.close();
                
                // Insert payment record
                String insertPaymentSql = "INSERT INTO payments (payment_id, appointment_id, amount, payment_status, transaction_id, razorpay_order_id, razorpay_payment_id, razorpay_signature, payment_date) " +
                                         "VALUES (payment_id_seq.NEXTVAL, ?, ?, 'SUCCESS', ?, ?, ?, ?, CURRENT_TIMESTAMP)";
                pstmt = conn.prepareStatement(insertPaymentSql);
                
                // Get appointment amount
                String amountSql = "SELECT amount FROM appointmentsnew WHERE appointment_id = ?";
                PreparedStatement amountStmt = conn.prepareStatement(amountSql);
                amountStmt.setInt(1, Integer.parseInt(appointmentId));
                ResultSet rs = amountStmt.executeQuery();
                double amount = 0;
                if (rs.next()) {
                    amount = rs.getDouble("amount");
                }
                rs.close();
                amountStmt.close();
                
                pstmt.setInt(1, Integer.parseInt(appointmentId));
                pstmt.setDouble(2, amount);
                pstmt.setString(3, razorpayPaymentId);
                pstmt.setString(4, razorpayOrderId);
                pstmt.setString(5, razorpayPaymentId);
                pstmt.setString(6, razorpaySignature);
                pstmt.executeUpdate();
                
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Payment verified successfully");
                
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Payment verification failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error: " + e.getMessage());
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
            out.print(jsonResponse.toString());
        }
    }
    
    private String generateSignature(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] bytes = mac.doFinal(data.getBytes());
        StringBuilder hash = new StringBuilder();
        for (byte b : bytes) {
            hash.append(String.format("%02x", b));
        }
        return hash.toString();
    }
}