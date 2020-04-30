 
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import model.Admin;
import model.Customer;
import model.DBUtil;
import model.Manager;
import model.Driver;
import model.Order;
import model.Vehicle;

/**
 *
 * @author Kyle
 */
public class ControlServlet extends HttpServlet {

   // private String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/freightdriverproj", DBusername = "root", DBpassword = "";
     private String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/db", DBusername = "root", DBpassword = "";

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "/index.jsp";
        
        if(action == null){
            action = "login";
        }
        
        if(action.equals("login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");            
            String userrole = DBUtil.getWorkerRole(username, password);
            
            if(userrole.equals("manager")){
                url = "/view/manager.jsp";
                Manager myInfo = DBUtil.getManager(username, password);
                request.setAttribute("myInfo", myInfo);
                request.setAttribute("myDrivers", DBUtil.getManagerDrivers(myInfo.getId()));
                request.setAttribute("myVehicles", DBUtil.getAllVehicles());
                request.setAttribute("myOrders", DBUtil.getManagerOrders(myInfo.getId()));
            }
            else if(userrole.equals("driver")){
                url = "/view/driver.jsp";
                Driver myInfo = DBUtil.getDriver(username, password); 
                request.setAttribute("myInfo", myInfo);
                request.setAttribute("myOrders", DBUtil.getDriverOrders(myInfo.getId()));
            }
            else if(userrole.equals("admin")){
                url = "/view/admin.jsp";
                Admin myInfo = DBUtil.getAdmin(username, password);              
                request.setAttribute("myInfo", myInfo);
                request.setAttribute("allVehicles", DBUtil.getAllVehicles());
                request.setAttribute("allDrivers", DBUtil.getAllDrivers() );
                request.setAttribute("allAdmins", DBUtil.getAllAdmins() );  
                request.setAttribute("allManagers", DBUtil.getAllManagers());
                request.setAttribute("finishedOrders", DBUtil.getFinishedOrders() ); 
            }
            else if(userrole.equals("customer")){
                url = "/view/customer.jsp";
               Customer myInfo = DBUtil.getCustomer(username, password);              
               request.setAttribute("myInfo", myInfo);
               request.setAttribute("myOrders", DBUtil.getOrderHistory(myInfo.getId()));
            }
        }
        
       
        else if(action.equals("managerChangeOrder")){
            url = "/view/manager.jsp";
            int managerId = Integer.parseInt(request.getParameter("managerId"));
            String managerFN = request.getParameter("managerFN");
            String managerLN = request.getParameter("managerLN");
            String managerP = request.getParameter("managerP");
            
            String nDID = request.getParameter("newDriverID"), nVID = request.getParameter("newVehicleID");
            int newDriverId = (nDID != null && !nDID.isEmpty()) ? Integer.parseInt(nDID): 0;
            int newVehicleId = (nVID != null && !nVID.isEmpty()) ? Integer.parseInt(nVID): 0;
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            
            DBUtil.changeOrder(newDriverId, newVehicleId, orderId);
            
            request.setAttribute("myInfo", DBUtil.getManager((managerFN + " " + managerLN), managerP));
            request.setAttribute("myDrivers", DBUtil.getManagerDrivers(managerId));
            request.setAttribute("myVehicles", DBUtil.getAllVehicles());
            request.setAttribute("myOrders", DBUtil.getManagerOrders(managerId));
        }
         else if(action.equals("makeNewOrder")){
           request.setAttribute("confirmation", " your order have been placed");
         url =  "/view/orderConfirm.jsp";
         
          String date = request.getParameter("date");
          String cargo = request.getParameter("cargo");
          String loc = request.getParameter("location");
          String dest = request.getParameter("destination");
          String id = request.getParameter("customerId");       
           DBUtil.makeNewOrder(date, cargo, loc, dest,id);
       }
        
        
        
        
        
        
        else if(action.equals("driverConfirm")){
            url = "/view/driver.jsp";
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String driverFN = request.getParameter("driverFN");
            String driverLN = request.getParameter("driverLN");
            String driverP = request.getParameter("driverP");
            
            int theOrderId = Integer.parseInt(request.getParameter("theOrderId"));
            DBUtil.setCurrentAssignment(driverId, theOrderId);
            
            request.setAttribute("myInfo", DBUtil.getDriver((driverFN + " " + driverLN), driverP));
            request.setAttribute("myOrders", DBUtil.getDriverOrders(driverId));
        }
        else if(action.equals("driverDecline")){
            url = "/view/driver.jsp";
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String driverFN = request.getParameter("driverFN");
            String driverLN = request.getParameter("driverLN");
            String driverP = request.getParameter("driverP");
            
            int theOrderId = Integer.parseInt(request.getParameter("theOrderId"));
            DBUtil.denyOrder(theOrderId, driverId);
            
            request.setAttribute("myInfo", DBUtil.getDriver((driverFN + " " + driverLN), driverP));
            request.setAttribute("myOrders", DBUtil.getDriverOrders(driverId));
        }
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    


    
    
      
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
