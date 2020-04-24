 
<%@page import="model.Customer"%>
<%@page import="model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
      
     Customer myInfo = (Customer)request.getAttribute("myInfo");
     ArrayList<Order> myOrders = (ArrayList<Order>)request.getAttribute("myOrders");
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
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer</title>
    </head>
    <body>
        <h1>Customer: <%= username + " " + myInfo.getId()%></h1>
       <p>
            <h3>History</h3>
            <div>
                <%
                    for(int i = 0; i < myOrders.size(); i++){
                        Order order = myOrders.get(i);
                         
                            out.println("<p>FROM: " + order.getLocation()+ "</p>");
                             out.println("<p>TO: " + order.getDestination()+ "</p>");
                             out.println("<p>CARGO: " + order.getCargo()+ "</p>");
                             
                             
                            break;
                        
                    }
                %>
            </div>
        </p>
        
    </body>
</html>
