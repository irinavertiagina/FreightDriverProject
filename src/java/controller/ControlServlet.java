/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            String userrole = getWorkerRole(username, password);
            
            if(userrole.equals("manager")){
                url = "/view/manager.jsp";
                Manager myInfo = getManager(username, password);
                request.setAttribute("myInfo", myInfo);
                request.setAttribute("myDrivers", getManagerDrivers(myInfo.getId()));
                request.setAttribute("myVehicles", getAllVehicles());
                request.setAttribute("myOrders", getManagerOrders(myInfo.getId()));
            }
            else if(userrole.equals("driver")){
                url = "/view/driver.jsp";
                Driver myInfo = getDriver(username, password); 
                request.setAttribute("myInfo", myInfo);
                request.setAttribute("myOrders", getDriverOrders(myInfo.getId()));
            }
            else if(userrole.equals("admin")){
                url = "/view/admin.jsp";
                Admin myInfo = getAdmin(username, password);              
                request.setAttribute("myInfo", myInfo);
            }
            else if(userrole.equals("customer")){
                url = "/view/customer.jsp";
                //Customer myInfo = getCustomer(username, password);              
               // request.setAttribute("myInfo", myInfo);
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
            
            changeOrder(newDriverId, newVehicleId, orderId);
            
            request.setAttribute("myInfo", getManager((managerFN + " " + managerLN), managerP));
            request.setAttribute("myDrivers", getManagerDrivers(managerId));
            request.setAttribute("myVehicles", getAllVehicles());
            request.setAttribute("myOrders", getManagerOrders(managerId));
        }
        else if(action.equals("driverConfirm")){
            url = "/view/driver.jsp";
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String driverFN = request.getParameter("driverFN");
            String driverLN = request.getParameter("driverLN");
            String driverP = request.getParameter("driverP");
            
            int theOrderId = Integer.parseInt(request.getParameter("theOrderId"));
            setCurrentAssignment(driverId, theOrderId);
            
            request.setAttribute("myInfo", getDriver((driverFN + " " + driverLN), driverP));
            request.setAttribute("myOrders", getDriverOrders(driverId));
        }
        else if(action.equals("driverDecline")){
            url = "/view/driver.jsp";
            int driverId = Integer.parseInt(request.getParameter("driverId"));
            String driverFN = request.getParameter("driverFN");
            String driverLN = request.getParameter("driverLN");
            String driverP = request.getParameter("driverP");
            
            int theOrderId = Integer.parseInt(request.getParameter("theOrderId"));
            denyOrder(theOrderId, driverId);
            
            request.setAttribute("myInfo", getDriver((driverFN + " " + driverLN), driverP));
            request.setAttribute("myOrders", getDriverOrders(driverId));
        }
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    private void changeOrder(int new_driver_id, int new_vehicle_id, int order_id){
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            if(new_driver_id != 0){
                stmt.executeUpdate("UPDATE `order` SET driver_id = " + new_driver_id + " WHERE order_id = " + order_id);
            }
            if(new_vehicle_id != 0){
                stmt.executeUpdate("UPDATE `order` SET vehicle_id = " + new_vehicle_id + " WHERE order_id = " + order_id);
            }
            stmt.close();
            con.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void setCurrentAssignment(int driver_id, int assignment_id){
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE driver SET current_assignment_id = " + assignment_id + " WHERE driver_id = " + driver_id);
            con.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void denyOrder(int order_id, int driver_id){
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate("UPDATE `order`SET driver_id = 0 WHERE order_id = " + order_id + " AND driver_id = " + driver_id);
            System.out.println("Rows affected: " + rows);
            con.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Order> getDriverOrders(int driver_id){
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM `order` WHERE driver_id = " + driver_id);
            while(rs.next()){
                orders.add(new Order(rs.getInt("order_id"), rs.getInt("customer_id"), rs.getInt("vehicle_id"), rs.getInt("order_status_id"), rs.getInt("driver_id"), rs.getInt("manager_id"), rs.getString("order_cargo"), rs.getString("order_start_date"), rs.getString("order_location"), rs.getString("order_finish_date"), rs.getString("order_destination")));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }
    
    private ArrayList<Order> getManagerOrders(int manager_id){
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM `order` WHERE manager_id = '" + manager_id + "'");
            while(rs.next()){
                orders.add(new Order(rs.getInt("order_id"), rs.getInt("customer_id"), rs.getInt("vehicle_id"), rs.getInt("order_status_id"), rs.getInt("driver_id"), rs.getInt("manager_id"), rs.getString("order_cargo"), rs.getString("order_start_date"), rs.getString("order_location"), rs.getString("order_finish_date"), rs.getString("order_destination")));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return orders;
    }
    
    private ArrayList<Vehicle> getAllVehicles(){
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle");
            while(rs.next()){
                vehicles.add(new Vehicle(rs.getInt("vehicle_id"), rs.getInt("driver_id"), rs.getString("vehicle_service_date"), rs.getString("vehicle_specs"), rs.getString("vehicle_status")));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return vehicles;
    }
    
    private ArrayList<Driver> getManagerDrivers(int manager_id){
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM driver WHERE manager_id = '" + manager_id + "'");
            while(rs.next()){
                drivers.add(new Driver(rs.getInt("driver_id"), rs.getString("driver_last_name"), rs.getString("driver_first_name"), rs.getString("driver_contacts"), rs.getString("manager_id"), rs.getString("driver_password"), rs.getInt("current_assignment_id")));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return drivers;
    }
    
    private Driver getDriver(String username, String password){
        Driver driver = new Driver();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM driver WHERE driver_password = '" + password + "'");
            while(rs.next()){
                if(username.contains(rs.getString("driver_first_name")) && username.contains(rs.getString("driver_last_name"))){
                    driver = new Driver(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                }
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return driver;
    }
    
    
    
    
    
    
    private Admin getAdmin(String username, String password){
        Admin admin = new Admin();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE admin_password = '" + password + "'");
            while(rs.next()){
                if(username.contains(rs.getString("admin_first_name")) && username.contains(rs.getString("admin_last_name"))){
                    admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                }
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return admin;
    }
   
    
    
    
        private Customer getCustomer(String username, String password){
        Customer customer = new Customer();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer WHERE customer_password = '" + password + "'");
            while(rs.next()){
                if(username.contains(rs.getString("customer_first_name")) && username.contains(rs.getString("customer_last_name"))){
                    customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                }
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return customer;
    }
    
    
    private Manager getManager(String username, String password){
        Manager manager = new Manager();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM manager WHERE manager_password = '" + password + "'");
            while(rs.next()){
                if(username.contains(rs.getString("manager_first_name")) && username.contains(rs.getString("manager_last_name"))){
                    manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                }
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return manager;
    }
    
    private String getWorkerRole(String username, String password){
        String role = "None";
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM manager WHERE manager_password = '" + password + "'");
            while(rs.next()){
                if(username.contains(rs.getString("manager_first_name")) && username.contains(rs.getString("manager_last_name"))){
                    role = "manager";
                    break;
                }
            }
            if(role.equals("None")){
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM driver WHERE driver_password = '" + password + "'");
                while(rs2.next()){
                    if(username.contains(rs2.getString("driver_first_name")) && username.contains(rs2.getString("driver_last_name"))){
                        role = "driver";
                        break;
                    }
                }
            }
             if(role.equals("None")){
                Statement stmt3 = con.createStatement();
                ResultSet rs3 = stmt3.executeQuery("SELECT * FROM admin WHERE admin_password = '" + password + "'");
                while(rs3.next()){
                    if(username.contains(rs3.getString("admin_first_name")) && username.contains(rs3.getString("admin_last_name"))){
                        role = "admin";
                        break;
                    }
                }
            }
               if(role.equals("None")){
                Statement stmt4 = con.createStatement();
                ResultSet rs4 = stmt4.executeQuery("SELECT * FROM customer WHERE customer_password = '" + password + "'");
                while(rs4.next()){
                    if(username.contains(rs4.getString("customer_first_name")) && username.contains(rs4.getString("customer_last_name"))){
                        role = "customer";
                        break;
                    }
                }
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return role;
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
