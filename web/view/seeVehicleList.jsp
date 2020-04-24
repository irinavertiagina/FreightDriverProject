 

<%@page import="model.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    ArrayList<Vehicle> vList = (ArrayList<Vehicle>)request.getAttribute("allVehicles");
     
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All vehicle</title>
    </head>
    <body>
       
<!-- 
      <%
   
                for (Vehicle i : vList) {
                    int id = i.getId();
                    int driver = i.getDriver_id();
                    
                    //String status = i.getStatus();
                    String service = i.getService_date();
                    String info = i.getSpecs();
                    
             %>  
                <p>ID: <%=id%> 
                  
                   INFO: <%=info%> 
                   NEXT SERVICE DATE: <%=service%>  </p>
            <% }%>
-->
               
                
                <%
                        for(int i = 0; i < vList.size(); i++){
                            Vehicle vehicle = vList.get(i);
                            out.println("<li><div>");
                            out.println("<h2>Vehicle ID: " + vehicle.getId() + "</h2>");
                            out.println("<p>Specs: " + vehicle.getSpecs() + "</p>");
                            out.println("<p>Status: " + vehicle.getStatus() + "</p>");
                            out.println("</div></li>");
                        }
                    %>
    </body>
</html>
