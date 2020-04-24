 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin-add new emp</title>
    </head>
    <body>
        <form action="adminServlet" method="post">    

            <p>first Name
                <input type="text" name="firstName"   >    
            </p>
            <p>last name
                <input type="text" name="lastName"  > 
            <p>
            <p>Contacts
                <input type="text" name="contact"  > 
            <p> 
            <p>Job
 
                <select name="role">
                    <option value="admin">admin</option>
                    <option value="manager">manager</option>
                    <option value="driver">driver</option>
                </select>

                <br>
                <input type="submit" value="Add new employee"> 
                <input type="hidden" name="todo" value="empAdded">   
        </form>  
    </body>
</html>
