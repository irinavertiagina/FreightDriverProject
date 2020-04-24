///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.*;
//import java.util.ArrayList;
//import model.Admin;
//import model.Customer;
//import model.Manager;
//import model.Driver;
//import model.Order;
//import model.Vehicle;
//
///**
// *
// * @author Kyle
// */
//public class adminServlet extends HttpServlet {
//
//   // private String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/freightdriverproj", DBusername = "root", DBpassword = "";
//     private String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/db", DBusername = "root", DBpassword = "";
//
//   
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        String url = "/index.jsp";
//        
//        if(action == null){
//            action = "login";
//        }
//        
//        if(action.equals("login")){
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");            
//            String userrole = getWorkerRole(username, password);
//            
//            if(userrole.equals("admin")){
//                url = "/view/admin.jsp";
//
//                Admin myAdmin= getAdmin(username, password);
//                request.setAttribute("myAdmin", myAdmin);
//                //request.setAttribute("myDrivers", getManagerDrivers(myInfo.getId()));
//                //request.setAttribute("myVehicles", getAllVehicles());
//                //request.setAttribute("myOrders", getManagerOrders(myInfo.getId()));
//            }
//            else if(userrole.equals("customer")){
//                url = "/view/customer.jsp";
//               // Customer myCustomer = getCustomer(username, password);
//                
//               // request.setAttribute("myCustomer", myCustomer);
//              //  request.setAttribute("myOrders", getDriverOrders(myInfo.getId()));
//            }
//        }
//        
//        
//       
//        this.getServletContext().getRequestDispatcher(url).forward(request, response);
//    }
//    
//
//    
//    private Admin getAdmin(String username, String password){
//        Admin admin = new Admin();
//        try{
//            Class.forName(DBclass);
//            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE admin_password = '" + password + "'");
//            while(rs.next()){
//                if(username.contains(rs.getString("admin_first_name")) && username.contains(rs.getString("admin_last_name"))){
//                    admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
//                }
//            }
//            con.close();
//        }
//        catch(SQLException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        return admin;
//    }
//    
//    
//    
//    
//
//    
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
