 
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DBUtil;
import model.Driver;
import model.Vehicle;

 
public class adminServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
                
                 
        
         String todo = request.getParameter("todo");
         String role = request.getParameter("role");
         String lname= request.getParameter("lastName");
         String fname = request.getParameter("firstName");
         String contact = request.getParameter("contact");
         
         
         
          String info= request.getParameter("info");
          String date= request.getParameter("serviceDate");
          String id= request.getParameter("driverID");
        String url = "";
        if(todo.equals(null)){
        url = "/error.jsp";
        }
        //add workers
        else if(todo.equals("addNewEmployee")){
        url = "/view/addEmployee.jsp";         
        } 
         else if(todo.equals("empAdded")){
             DBUtil.addEmployee(role, lname, fname, contact);
        url = "/view/orderConfirm.jsp"; //test!
        } 
         //add vehicles
         
         
         else if(todo.equals("addNewVehicle")){
         url = "/view/addVehicle.jsp";
         }
          else if(todo.equals("vehicleAdded")){
             DBUtil.addVehicle(info, date, id);
        url = "/view/orderConfirm.jsp"; //test!
        }
        
        //see all vehicle
          else if(todo.equals("seeVehicleList")){
              ArrayList<Vehicle> vList = DBUtil.seeVehicleList();
              request.setAttribute("vList", vList);
           //   String driverName = "testtesttest";
              //request.setAttribute("driverName",driverName);
          url = "/seeVehicleList.jsp";
          }
        
         //see all workers
          else if(todo.equals("seeEmployeeList")){
              
              if(role.equals("admin")){
                   url = "/seeEmployeeList.jsp";
                    
              } else if(role.equals("manager")){
                   url = "/seeEmployeeList.jsp";
              }else if(role.equals("driver")){
                   ArrayList<Driver> driverList = DBUtil.getAllDrivers();
                   url = "/seeEmployeeList.jsp";
                    request.setAttribute("allDrivers", driverList);     
              }
            
              
              
          }
        
             
//         request.setAttribute("role", role);
//          request.setAttribute("lastName", lastName);
//          person.setLastName(lastName);
//            session.setAttribute("person", person);



         getServletContext().getRequestDispatcher(url)
                .forward(request, response);
        
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
