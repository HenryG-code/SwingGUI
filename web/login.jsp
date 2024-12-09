<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
        
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <script>alert('<%= message %>');</script>
        <% } %>

    <form action="LoginServlet" method="POST">
        <table border="0">
            <tr>
                <td>Username:</td>
                <td><input type="text" name="Username" size="50" required/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="Password" size="50" required/></td>
            </tr>
        </table>
        <input type="submit" value="Login"/>
    </form>

    <p>If you don't have an account, <a href="register.jsp">register here</a>.</p>

<!--    <c:if test="${param.error eq 'invalid'}">
        <p style="color: red;">Wrong username or password. Please try again.</p>
    </c:if>-->

</body>
</html>