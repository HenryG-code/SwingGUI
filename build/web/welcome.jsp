<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome Page</title>
</head>
<body>
    <table align="center" cellpadding="10">
        <tr>
            <td align="center" colspan="2">
                <h1>Welcome, ${sessionScope.Username}!</h1>
                <h2>Logged in User</h2>
                <p>Here, the library system must be linked</p>
            </td>
        </tr>
        <tr>
            <td align="center">
                <a href="index.jsp">Home</a>
            </td>
            <td align="center">
                <a href="login.jsp">Logout</a>
            </td>
        </tr>
    </table>
</body>
</html>
