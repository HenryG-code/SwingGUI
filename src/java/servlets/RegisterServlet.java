package servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Users";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "1234";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        String name = request.getParameter("Name");
        String surname = request.getParameter("Surname");
        String phone = request.getParameter("Phone");
        String email = request.getParameter("Email");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "INSERT INTO UserRegistration (username, password, name, surname, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, surname);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.executeUpdate();
            
            //Now to give feedback to the user
            request.setAttribute("message", "Registration successful! Please log in.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);

            response.sendRedirect("login.jsp"); // Redirect to login page after successful registration

        } catch (IOException | ClassNotFoundException | SQLException e) {
            request.setAttribute("message", "Registration Unsuccessful! Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
            response.sendRedirect("register.jsp"); // Redirect to an error page
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
}