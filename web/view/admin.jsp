

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
    Admin myInfo = (Admin) request.getAttribute("myInfo");
    String username = (myInfo.getFirstName() + " " + myInfo.getLastName()); 
    ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("finishedOrders");

%>


<html>
    <head>
        <link rel="stylesheet" type="text/css" href="view/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body >
        <h1>Admin <%= username%></h1>
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
                    <form action="adminServlet" method="get">                    
                      
                        <input type="submit" value="Delete from database">
                        <input type="hidden" name="todo" value="deleteOrder">                       
<!--                    <input type='hidden' name='managerP' value='<%= myInfo.getPassword()%>'>
                        <input type='hidden' name='orderId' value='<%= order.getId()%>'>-->
                    </form>
                    <%
                            out.println("</div></li>");
                        }
                    %>
                </ul>
            </nav>








        <nav>
            <h3 style="position: absolute; left: 40%; top: 8%">Workers</h3>
            <ul style="position: absolute; left: 40%; top: 10%">
                <p>
                    <a href ="adminServlet?todo=addNewEmployee"  class="button">Add new worker</a>
                <p>                             
                    <%@include file = "seeEmployeeList.jsp" %>                  
            </ul>
        </nav>

        <nav>
            <h3 style="position: absolute; left: 70%; top: 8%">Vehicles</h3>
            <ul style="position: absolute; right: 5%; top: 10%">
                <p>
                    <a href ="adminServlet?todo=addNewVehicle" class="button">Add new vehicle</a> 
                <p>
                    <%@include file = "seeVehicleList.jsp" %>                      
            </ul>
        </nav>
    </body>
</html>
