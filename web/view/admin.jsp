

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
        <h1>Admin <%= username + " " + myInfo.getId()%></h1>
        <p>
            <nav>
                <h3>Orders</h3>
 
    </body>
</html>
