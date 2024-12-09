
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <h1>Registration</h1>
        
         <% String message = (String) request.getAttribute("message"); %>
            <% //default is message != null
                if (message == "Registration successful! Please log in.") {
                    out.println("<script>alert('" + message + "'); window.location.href = 'login.jsp';</script>");
                }else if(message == "Registration Unsuccessful! Please try again."){
                    out.println("<script>alert('" + message + "'); window.location.href = 'register.jsp';</script>");
                }
            %>

          <form action="RegisterServlet" method="POST">
                <table border="0">
                  
                    <tbody>
                        <tr>
                            <td>Enter Username:</td>
                            <td><input type="text" name="Username" value="" size="50" required/></td>
                        </tr>
                        <tr>
                            <td>Enter Name:</td>
                            <td><input type="text" name="Name" value="" size="50" required/></td>
                        </tr>
                        <tr>
                            <td>Enter Surname: </td>
                            <td><input type="text" name="Surname" value="" size="50" required/></td>
                        </tr>
                        <tr>
                            <td>Enter Password: </td>
                            <td><input type="password" name="Password" value="" size="50" required/></td>
                        </tr>
                        <tr>
                            <td>Enter Phone Number: </td>
                            <td><input type="phone" name="Phone" value="" size="50" required/></td>
                        </tr>
                      
                         <tr>
                            <td>Enter email: </td>
                            <td><input type="email" name="Email" value="" size="50" required/></td>
                        </tr>
                                 </tbody>
                   
                </table>
                <input type="submit" value="Register"/>
                <input type="reset" value="Clear"/>   
            </form>
        
    </body>
</html>
