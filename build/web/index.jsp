 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Freight Deliveries co.</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="ControlServlet" method="get">
            <p>
            Username: <input type="text" name="username">
            </p>
            <p>
            Password: <input type="text" name="password">
            </p>
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="login">
        </form>
    </body>
</html>
