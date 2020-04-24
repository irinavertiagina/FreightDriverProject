

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
   // ArrayList<Driver> drivers = (ArrayList<Driver>)request.getAttribute("allDrivers");
    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>)request.getAttribute("allVehicles");
   // ArrayList<Admin> admins = (ArrayList<Admin>)request.getAttribute("allAdmins");

     

    
    Admin myInfo = (Admin)request.getAttribute("myInfo");
    String username = (myInfo.getFirstName() + " " + myInfo.getLastName());
%>
    

<html>
    <style>
         
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
       
            <nav>
                <h3 style="position: absolute; left: 40%; top: 8%">Workers</h3>
                <ul style="position: absolute; left: 40%; top: 10%">
                    <p>
                          <a href ="adminServlet?todo=addNewEmployee">Add new worker</a>
        <p>
                       
                      
 
                     <%@include file = "seeEmployeeList.jsp" %> 
                     
              
                    
                </ul>
            </nav>
            
            
            
            
            
             <nav>
                <h3 style="position: absolute; left: 70%; top: 8%">Vehicles</h3>
                <ul style="position: absolute; right: 5%; top: 10%">
                       <a href ="adminServlet?todo=addNewVehicle">Add new vehicle</a> 
        <p>
                         <%@include file = "seeVehicleList.jsp" %> 
                     
                </ul>
            </nav>
                
     </body>
</html>
