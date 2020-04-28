 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
 

            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                            <link rel="stylesheet" type="text/css" href="view/stylesheet.css">

        <title>Admin-add new employee</title>
    </head>
    <body>
        <div>
        <form action="adminServlet" method="post">    

            <p>First Name
                <input type="text" name="firstName"   >    
            </p>
            <p>Last name
                <input type="text" name="lastName"  > 
            <p>
            <p>Contacts
                <input type="text" name="contact"  > 
            <p> 
            <p>Job   
 
                <select name="role">
                    <option value="admin">Admin</option>
                    <option value="manager">Manager</option>
                    <option value="driver">Driver</option>
                </select>

                <br>
                <input type="submit" value="Add new employee"> 
                <input type="hidden" name="todo" value="empAdded">   
        </form>  
           </div>
    </body>
</html>
