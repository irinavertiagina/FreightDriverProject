 
<%@page import="model.Customer"%>
<%@page import="model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Customer myInfo = (Customer) request.getAttribute("myInfo");
    ArrayList<Order> myOrders = (ArrayList<Order>) request.getAttribute("myOrders");
    String username = (myInfo.getFirstName() + " " + myInfo.getLastName());
%>

<html>  
    <head>
        <link rel="stylesheet" type="text/css" href="view/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer</title>
    </head>
    <body>
        <h1>Customer: <%= username + " " + myInfo.getId()%></h1>
        <p>
        <h3>History</h3>
        <nav>
            <%
                for (int i = 0; i < myOrders.size(); i++) {
                    Order order = myOrders.get(i);
                    out.println("<li><div>");
                    out.println("<p>FROM: " + order.getLocation() + "</p>");
                    out.println("<p>TO: " + order.getDestination() + "</p>");
                    out.println("<p>CARGO: " + order.getCargo() + "</p>");
                    out.println("</li></div>");
                }
            %>
        </nav>
    </p>

    <nav>
        <h3 style="position: absolute; left: 30%; top: 8%">Make new order</h3>
        <ul style="height: 80%; width: 30%; overflow: hidden; overflow-y: scroll; position: absolute; left: 30%; top: 10%">

            <form action="ControlServlet" method="get">    
                <p>Date
                    <input type="text" name="date"   >    
                </p>
                <p>Cargo
                    <input type="text" name="cargo"  > 
                <p>
                <p>Origin location
                    <input type="text" name="location"  > 
                <p>
                <p>Destination
                    <input type="text" name="destination"  > 
                <p>            
                    <input type="hidden" name="action" value="makeNewOrder">
                    <input type='hidden' name='customerId' value='<%= myInfo.getId()%>'>                   
                    <input type="submit" value="Place order"> 
            </form> 
            <p>
        </ul>
    </nav>

</body>
</html>
