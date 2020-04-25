 

<%@page import="java.util.ArrayList"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String conf = (String) request.getAttribute("confirmation");
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
      <body>
         
        <h1>Thank you <%= conf %></h1>
                    <a href ="ControlServlet?action=login"> Home</a>

               
    </body>
</html>
