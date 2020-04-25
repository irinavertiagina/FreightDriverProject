package model;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil {
         private static String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/db", DBusername = "root", DBpassword = "";

        
        
         
          public static void addNewEmployee(String role, String lname, String fname, String contact){
            try{
               
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            //add manager
            if(role.equals("manager")){
                 stmt.executeUpdate( "INSERT INTO `"+role+"`( `manager_last_name`, `manager_first_name`, `manager_contact`, `manager_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 'pass')" ); 
            } else if(role.equals("admin")){
               stmt.executeUpdate( "INSERT INTO `admin`( `admin_last_name`, `admin_first_name`, `admin_contact`, `admin_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 'pass')" ); 
            }
            
            
            //add driver
            if(role.equals("driver")){ 
                stmt.executeUpdate( "INSERT INTO `driver`( `driver_last_name`, `driver_first_name`, `driver_contact`, `manager_id`, `driver_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 1, 'pass') "); 
            }
            
            
            
            stmt.executeUpdate("");
            con.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
      
        public static ArrayList<Vehicle> seeVehicleList() {
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
       
         public static void placeOrder(String date, String cargo, String location, String dest) { 
 try {
            String dbURL = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, password);
           Statement statement = connection.createStatement();
      
            
          
         //String preparedQuery = "INSERT INTO `testtable`(`first`, `second`, `third`) VALUES (1,2,3)";//test!
String preparedQuery = "INSERT INTO `order`(`driver_id`, `customer_id`, `order_cargo`, `order_start_date`,`order_destination`, `order_location`    ) VALUES (1, 1, '"+cargo+"', '"+date+"', '"+dest+"', '"+location+"')";

            
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(preparedQuery);
          //ps.setString(1, "test");
           ps.executeUpdate();
                     connection.close();
        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
        
        }
        
     
         
         
                  public static void addEmployee(String role, String lname, String fname, String contact) {
         
        try {
            String dbURL = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, password);
           Statement statement = connection.createStatement();
           String preparedQuery = null;
            
           //add manager
            if(role.equals("manager")){
                preparedQuery =  "INSERT INTO `"+role+"`( `manager_last_name`, `manager_first_name`, `manager_contact`, `manager_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 'pass')"; 
            }
            
            //add admin
            
            else if(role.equals("admin")){
               preparedQuery =  "INSERT INTO `admin`( `admin_last_name`, `admin_first_name`, `admin_contact`, `admin_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 'pass')"; 
            }
            
            
            //add driver
            if(role.equals("driver")){  preparedQuery =  "INSERT INTO `driver`( `driver_last_name`, `driver_first_name`, `driver_contact`, `manager_id`, `driver_password`) "
                    + "VALUES ('"+lname+"', '"+fname+"', '"+contact+"', 1, 'pass')"; 
            }
            
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(preparedQuery);
         
           ps.executeUpdate();
                     connection.close();
        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
        
        }
         
         
         
                  
                  
                  
                  
                     public static void addVehicle(String info, String date, String id) {
         
        try {
            String dbURL = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, password);
           Statement statement = connection.createStatement();

            String preparedQuery = "INSERT INTO `vehicle`( `vehicle_service_date`, `vehicle_status`, `vehicle_specs`, `driver_id`) VALUES (`"+date+"` , `status`, `"+info+"`, "+id+")"; 
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(preparedQuery);
 
           ps.executeUpdate();
                     connection.close();
            } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
        
        }
                  
                  public static ArrayList<Driver> getAllDrivers(){
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM driver");
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
          
        
        
        
         
 
}