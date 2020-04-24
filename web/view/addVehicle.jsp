 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add vehicle</title>
    </head>
    <body>
       <p>Add new vehicle</p>
        <form action="adminServlet" method="post">    
             
            <p>Vehicle information
                <input type="text" name="info"   >    
            </p>
            <p>Service date
                <input type="text" name="serviceDate"   >    
            </p>
            <p>Assign driver
                <input type="text" name="driverID"   >    
            </p>
            <input type="hidden" name="todo" value="vehicleAdded">  
            <input type="submit" value="Add vehicle"> 
        </form>
    </body>
</html>
