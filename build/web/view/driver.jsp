 
<%@page import="model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<Order> myOrders = (ArrayList<Order>)request.getAttribute("myOrders");  
    Driver myInfo = (Driver)request.getAttribute("myInfo");
    String username = (myInfo.getFirst_name() + " " + myInfo.getLast_name());
%>

<html>  
  
    <head>
                <link rel="stylesheet" type="text/css" href="view/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driver</title>
    </head>
    <body>
        <h1>Driver: <%= username + " " + myInfo.getId()%></h1>
        <p>
            <h3>Current Assignment</h3>
            <div>
                <%
                    for(int i = 0; i < myOrders.size(); i++){
                        Order order = myOrders.get(i);
                        if(order.getId() == myInfo.getCurrent_assignment_id()){
                            out.println("<p>Vehicle ID: " + order.getVehicle_id() + "</p>");
                            out.println("<p>Destination: " + order.getDestination() + "</p>");
                            out.println("<p>Arrival Date: " + order.getFinish_date() + "</p>");
                            out.println("<p>Cargo: " + order.getCargo() + "</p>");
                            break;
                        }
                    }
                %>
            </div>
        </p>
        <p>
            <nav>
                <h3 style="position: absolute; left: 30%; top: 8%">My Assignments</h3>
                <ul style="height: 80%; width: 30%; overflow: hidden; overflow-y: scroll; position: absolute; left: 30%; top: 10%">
                    <%
                    for(int i = 0; i < myOrders.size(); i++) {
                        Order order = myOrders.get(i);
                        out.println("<li><div>");
                        out.println("<p>Vehicle ID: " + order.getVehicle_id() + "</p>");
                        out.println("<p>Destination: " + order.getDestination() + "</p>");
                        out.println("<p>Arrival Date: " + order.getFinish_date() + "</p>");
                        out.println("<p>Cargo: " + order.getCargo() + "</p>");
                    %>
                    <form action="ControlServlet" method="get">
                        <input type="submit" value="Confirm">
                        <input type="hidden" name="action" value="driverConfirm">
                        <input type='hidden' name='driverId' value='<%= myInfo.getId()%>'>
                        <input type='hidden' name='driverFN' value='<%= myInfo.getFirst_name()%>'>
                        <input type='hidden' name='driverLN' value='<%= myInfo.getLast_name()%>'>
                        <input type='hidden' name='driverP' value='<%= myInfo.getPassword()%>'>
                        <input type='hidden' name='theOrderId' value='<%= order.getId()%>'>
                    </form>
                    <p>
                        <form action="ControlServlet" method="get">
                            <input type='submit' value='Decline'>
                            <input type='hidden' name='action' value='driverDecline'>
                            <input type='hidden' name='driverId' value='<%= myInfo.getId()%>'>
                            <input type='hidden' name='driverFN' value='<%= myInfo.getFirst_name()%>'>
                            <input type='hidden' name='driverLN' value='<%= myInfo.getLast_name()%>'>
                            <input type='hidden' name='driverP' value='<%= myInfo.getPassword()%>'>
                            <input type='hidden' name='theOrderId' value='<%= order.getId()%>'>
                        </form>
                    </p>
                    <%
                        out.println("</div></li>");
                    }
                    %>
                </ul>
            </nav>
        </p>
    </body>
</html>
