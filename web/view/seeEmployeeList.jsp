 

<%@page import="model.Manager"%>
<%@page import="model.Admin"%>
<%@page import="model.Driver"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> </title>
    </head>
    <body>
      <%
              ArrayList<Driver> alldrivers = (ArrayList<Driver>)request.getAttribute("allDrivers");
              ArrayList<Admin> admins = (ArrayList<Admin>)request.getAttribute("allAdmins");
              ArrayList<Manager> managers = (ArrayList<Manager>) request.getAttribute("allManagers");
             
              
               

                        for(int i = 0; i < alldrivers.size(); i++){
                            Driver driver = alldrivers.get(i);
                            String driver_username = (driver.getFirst_name() + " " + driver.getLast_name());
                            out.println("<li><div>");
                            out.println("<h2>Driver: " + driver_username + "</h2>");
                            out.println("<p>ID: " + driver.getId() + "</p>");
                             out.println("<p>CONTACT: " + driver.getContacts() + "</p>");                                       
                            out.println("</div></li>");
                        }
 
                    %>
                    
                    
                    <%
                        for(int i = 0; i < admins.size(); i++){
                            Admin admin = admins.get(i);
                            String admin_username = (admin.getFirstName() + " " + admin.getLastName());
                            out.println("<li><div>");
                            out.println("<h2>Admin: " + admin_username + "</h2>");
                            out.println("<p>ID: " + admin.getId() + "</p>");                           
                            out.println("</div></li>");
                        }
                    %>
                    
                    
                    
                    
                     <%
                        for(int i = 0; i < managers.size(); i++){
                            Manager mng = managers.get(i);
                            String manager_username = (mng.getFirst_name() +" "+ mng.getLast_name() );
                            out.println("<li><div>");
                            out.println("<h2>Manager " + manager_username + "</h2>");
                            out.println("<p>ID: " + mng.getId() + "</p>");  
                            out.println("<p>CONTACT: " + mng.getContacts() + "</p>");                                                     
                            out.println("</div></li>");
                        }
                    %>
                    
                    
                    
    </body>
</html>
