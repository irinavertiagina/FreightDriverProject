

<%@page import="model.Admin"%>
<%@page import="model.Driver"%>
<%@page import="model.Manager"%>
<%@page import="model.Order"%>
<%@page import="model.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    ArrayList<Driver> drivers = (ArrayList<Driver>)request.getAttribute("allDrivers");
    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>)request.getAttribute("allVehicles");
    ArrayList<Admin> admins = (ArrayList<Admin>)request.getAttribute("allAdmins");

     

    
    Admin myInfo = (Admin)request.getAttribute("myInfo");
    String username = (myInfo.getFirstName() + " " + myInfo.getLastName());
%>
    

<html>
    <style>
        footer {
            background-color: #E6E6FA;
        }
        div {
            width: 250px;
            border: 10px solid green;
            padding: 10px;
            margin: 20px;
         }
        ul{
            height: 80%;
            width: 22%;
            overflow:hidden;
            overflow-y: scroll;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body >
        <h1>Admin <%= username %></h1>
        <p>       
            <nav>
                <h3 style="position: absolute; left: 40%; top: 8%">Workers</h3>
                <ul style="position: absolute; left: 40%; top: 10%">
                    <p>
                    <form action="ControlServlet" method="post">    
                        <select name="role">
                            <option value="admin">admin</option>
                            <option value="manager">manager</option>
                            <option value="driver">driver</option>
                        </select>
                        <input type="hidden" name="action" value="seeEmployeeList">   
                        <input type="submit" value="See workers list"> 
                    </form>  
                    <%
                        for(int i = 0; i < drivers.size(); i++){
                            Driver driver = drivers.get(i);
                            String driver_username = (driver.getFirst_name() + " " + driver.getLast_name());
                            out.println("<li><div>");
                            out.println("<h2>Driver: " + driver_username + "</h2>");
                            out.println("<p>ID: " + driver.getId() + "</p>");                           
                            out.println("<h3>Current Assignment: </h3>");                          
                            out.println("</div></li>");
                        }
                    %>
                    
                    
                    
                    
                    
                     <%
                        for(int i = 0; i < admins.size(); i++){
                            Admin admin = admins.get(i);
                            String admin_username = (admin.getFirstName() + " " + admin.getLastName());
                            out.println("<li><div>");
                            out.println("<h2>Admin: " + admin_username + "</h2>");
                            out.println("<p>ID: " + admin.getId() + "</p>");                           
                                                    
                            out.println("</div></li>");
                        }
                    %>
                    
                </ul>
            </nav>
            
            
            
            
            
            
            
            
            
            
            
            
            
             <nav>
                <h3 style="position: absolute; left: 70%; top: 8%">Vehicles</h3>
                <ul style="position: absolute; right: 5%; top: 10%">
                    <%
                        for(int i = 0; i < vehicles.size(); i++){
                            Vehicle vehicle = vehicles.get(i);
                            out.println("<li><div>");
                            out.println("<h2>Vehicle ID: " + vehicle.getId() + "</h2>");
                            out.println("<p>Specs: " + vehicle.getSpecs() + "</p>");
                            out.println("<p>Status: " + vehicle.getStatus() + "</p>");
                            out.println("</div></li>");
                        }
                    %>
                </ul>
            </nav>
                <footer>footer</footer>
     </body>
</html>
