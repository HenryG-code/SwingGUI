package servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Users";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            String sql = "SELECT * FROM UserRegistration WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            

                if (rs.next()) {
                // Store username in session
                HttpSession session = request.getSession();
                session.setAttribute("Username", username);
                
                // Login successful
                request.setAttribute("message", "Login successful! Welcome " + rs.getString("name") + ".");
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                dispatcher.forward(request, response);

                // Redirect to welcome page
                response.sendRedirect("welcome.jsp");
            } else {
                 
                // Login unsuccessful
                request.setAttribute("message", "Invalid username or password.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);    
                    
                // Failed login
                response.sendRedirect("login.jsp?error=invalid");
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            request.setAttribute("message", "An error occurred. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            
            response.sendRedirect("login.jsp"); // Redirect to an error page
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
}
