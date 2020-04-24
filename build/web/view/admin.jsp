

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
   // ArrayList<Driver> drivers = (ArrayList<Driver>)request.getAttribute("myDrivers");
    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>)request.getAttribute("myVehicles");
   // ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("myOrders");
    //Order a = new Order(1,1,1,1,1,1, "q", "q", "q", "q", "q");
    //orders.add(a);
    //Order b = new Order(1,1,1,1,1,1, "q", "q", "q", "q", "q");
   // orders.add(b);
   
    
    
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
    <body>
        <h1>Admin <%= username %></h1>
        <p>
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
 
    </body>
</html>
