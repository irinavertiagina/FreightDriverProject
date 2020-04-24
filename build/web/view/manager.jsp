<%-- 
    Document   : manager
    Created on : Apr 11, 2020, 5:49:07 PM
    Author     : Kyle
--%>

<%@page import="model.Driver"%>
<%@page import="model.Manager"%>
<%@page import="model.Order"%>
<%@page import="model.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    ArrayList<Driver> drivers = (ArrayList<Driver>)request.getAttribute("myDrivers");
    ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>)request.getAttribute("myVehicles");
    ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("myOrders");
    
    Order a = new Order(1,1,1,1,1,1, "q", "q", "q", "q", "q");
    orders.add(a);
    Order b = new Order(1,1,1,1,1,1, "q", "q", "q", "q", "q");
    orders.add(b);
    Manager myInfo = (Manager)request.getAttribute("myInfo");
    String username = (myInfo.getFirst_name() + " " + myInfo.getLast_name());
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
        <title>Manager</title>
    </head>
    <body>
        <h1>Manager: <%= username + " " + myInfo.getId()%></h1>
        <p>
            <nav>
                <h3>Orders</h3>
                <ul style="position: absolute; top: 10%">
                    <%
                        for(int i = 0; i < orders.size(); i++){
                            Order order = orders.get(i);
                            out.println("<li><div>");
                            out.println("<p>Order ID: " + order.getId() + "</p>");
                            out.println("<p>Customer ID: " + order.getCustomer_id() + "</p>");
                            out.println("<p>Location From: " + order.getLocation() + "</p>");
                            out.println("<p>Destination: " + order.getDestination() + "</p>");
                            out.println("<p>Cargo: " + order.getCargo() + "</p>");
                            out.println("<p>Arrival Date: " + order.getFinish_date() + "</p>");
                            out.println("<p>Driver ID: " + order.getDriver_id() + "</p>");
                            out.println("<p>Vehicle ID: " + order.getVehicle_id() + "</p>");
                    %>
                    <form action="ControlServlet" method="get">
                        <p>Reassign Driver with ID <input type="text" name="newDriverID"></p>
                        <p>Reassign Vehicle with ID <input type="text" name="newVehicleID"></p>
                        <input type="submit" value="Reassign">
                        <input type="hidden" name="action" value="managerChangeOrder">
                        <input type='hidden' name='managerId' value='<%= myInfo.getId()%>'>
                        <input type='hidden' name='managerFN' value='<%= myInfo.getFirst_name()%>'>
                        <input type='hidden' name='managerLN' value='<%= myInfo.getLast_name()%>'>
                        <input type='hidden' name='managerP' value='<%= myInfo.getPassword()%>'>
                        <input type='hidden' name='orderId' value='<%= order.getId()%>'>
                    </form>
                    <%
                            out.println("</div></li>");
                        }
                    %>
                </ul>
            </nav>
        </p>
        <p>
            <nav>
                <h3 style="position: absolute; left: 40%; top: 8%">Drivers</h3>
                <ul style="position: absolute; left: 40%; top: 10%">
                    <%
                        for(int i = 0; i < drivers.size(); i++){
                            Driver driver = drivers.get(i);
                            String driver_username = (driver.getFirst_name() + " " + driver.getLast_name());
                            out.println("<li><div>");
                            out.println("<h2>Driver: " + driver_username + "</h2>");
                            out.println("<p>ID: " + driver.getId() + "</p>");
                            out.println("<h3>Current Assignment: </h3>");
                            for(int j = 0; j < orders.size(); j++){
                                Order order = orders.get(j);
                                if(order.getId() == driver.getCurrent_assignment_id()){
                                    out.println("<p>Customer ID: " + order.getCustomer_id() + "</p>");
                                    out.println("<p>Location From: " + order.getLocation() + "</p>");
                                    out.println("<p>Destination: " + order.getDestination() + "</p>");
                                    out.println("<p>Cargo: " + order.getCargo() + "</p>");
                                    out.println("<p>Arrival Date: " + order.getFinish_date() + "</p>");
                                    out.println("<p>Vehicle ID: " + order.getVehicle_id() + "</p>");
                                    break;
                                }
                            }
                            out.println("</div></li>");
                        }
                    %>
                </ul>
            </nav>
        </p>
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
        </p>
    </body>
</html>
