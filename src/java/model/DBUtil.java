package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil {

    private static String DBclass = "com.mysql.cj.jdbc.Driver", DBurl = "jdbc:mysql://localhost:3306/db", DBusername = "root", DBpassword = "";

    public static void deleteOrder(){
         try {
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
           stmt.executeUpdate("DELETE * FROM `order` WHERE order_status_id = 3");
             
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Order> getFinishedOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `order` WHERE order_status_id = 3");
            while (rs.next()) {
                orders.add(new Order(rs.getInt("order_id"), rs.getInt("driver_id"), rs.getInt("customer_id"), rs.getInt("manager_id"), rs.getInt("vehicle_id"), rs.getInt("order_status_id"), rs.getString("order_start_date"), rs.getString("order_cargo"), rs.getString("order_destination"), rs.getString("order_location"), rs.getString("order_finish_date")));
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void addNewEmployee(String role, String lname, String fname, String contact) {
        try {

            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            //add manager
            if (role.equals("manager")) {
                stmt.executeUpdate("INSERT INTO `" + role + "`( `manager_last_name`, `manager_first_name`, `manager_contact`, `manager_password`) "
                        + "VALUES ('" + lname + "', '" + fname + "', '" + contact + "', 'pass')");
            } else if (role.equals("admin")) {
                stmt.executeUpdate("INSERT INTO `admin`( `admin_last_name`, `admin_first_name`, `admin_contact`, `admin_password`) "
                        + "VALUES ('" + lname + "', '" + fname + "', '" + contact + "', 'pass')");
            }

            //add driver
            if (role.equals("driver")) {
                stmt.executeUpdate("INSERT INTO `driver`( `driver_last_name`, `driver_first_name`, `driver_contact`, `manager_id`, `driver_password`) "
                        + "VALUES ('" + lname + "', '" + fname + "', '" + contact + "', 1, 'pass') ");
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Vehicle> seeVehicleList() {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle");
            while (rs.next()) {
                vehicles.add(new Vehicle(rs.getInt("vehicle_id"), rs.getInt("driver_id"), rs.getString("vehicle_service_date"), rs.getString("vehicle_specs"), rs.getString("vehicle_status")));
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public static void addNewVehicle(String info, String date, String id) {
        try {

            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("INSERT INTO `vehicle`(  `vehicle_service_date`, `vehicle_status`, `vehicle_specs`, `driver_id`) VALUES (\" " + date + "\", \"inspecified\", \"" + info + "\", " + id + ")");

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Driver> getAllDrivers() {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        try {
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl, DBusername, DBpassword);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM driver");
            while (rs.next()) {
                drivers.add(new Driver(rs.getInt("driver_id"), rs.getString("driver_last_name"), rs.getString("driver_first_name"), rs.getString("driver_contacts"), rs.getString("manager_id"), rs.getString("driver_password"), rs.getInt("current_assignment_id")));
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    
    
    
    public static void makeNewOrder(String date, String cargo, String loc, String dest, String id){
            try{
               
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `order`(`driver_id`, `customer_id`, `order_start_date`, `order_cargo`, `order_destination`, `order_location`, `vehicle_id`, `order_status_id`, `manager_id`, `order_finish_date`)"
                    + " VALUES (0, "+id+ ", \""+date+"\", \" "+cargo+"\", \""+dest+"\", \""+loc+"\", 1, 0, 4, \"order_finish_date\")");
            con.close();
        }
        catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void changeOrder(int new_driver_id, int new_vehicle_id, int order_id){
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
    
    public static void setCurrentAssignment(int driver_id, int assignment_id){
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
    
    public static void denyOrder(int order_id, int driver_id){
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

    public static ArrayList<Order> getDriverOrders(int driver_id){
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
    
    public static ArrayList<Order> getManagerOrders(int manager_id){
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
    
    public static ArrayList<Vehicle> getAllVehicles(){
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
    
//    publicstatic ArrayList<Driver> getAllDrivers(){
//        ArrayList<Driver> drivers = new ArrayList<Driver>();
//        try{
//            Class.forName(DBclass);
//            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
//            Statement stmt = con.createStatement();  
//            ResultSet rs = stmt.executeQuery("SELECT * FROM driver");
//            while(rs.next()){
//                drivers.add(new Driver(rs.getInt("driver_id"), rs.getString("driver_last_name"), rs.getString("driver_first_name"), rs.getString("driver_contacts"), rs.getString("manager_id"), rs.getString("driver_password"), rs.getInt("current_assignment_id")));
//            }
//            con.close();
//        }
//        catch(SQLException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        return drivers;
//    }
    
    public static ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> admins = new ArrayList<Admin>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin");
            while(rs.next()){
                admins.add(new Admin(rs.getInt("admin_id"), rs.getString("admin_last_name"), rs.getString("admin_first_name"), rs.getString("admin_contact"), rs.getString("admin_password") ));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return admins;
    }
    
    public static ArrayList<Manager> getAllManagers(){
        ArrayList<Manager> managers = new ArrayList<Manager>();
        try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM manager");
            while(rs.next()){
                managers.add(new Manager(rs.getInt("manager_id"), rs.getString("manager_last_name"), rs.getString("manager_first_name"), rs.getString("manager_contact"), rs.getString("manager_password") ));
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return managers;
    }
    public static ArrayList<Order> getOrderHistory(int id) {
                ArrayList<Order> history = new ArrayList<Order>();
try{
            Class.forName(DBclass);
            Connection con = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM `order` WHERE customer_id ="+id );
            while(rs.next()){
                history.add(new Order(rs.getInt("order_id"), rs.getInt("driver_id"),rs.getInt("customer_id"),rs.getInt("manager_id"),rs.getInt("vehicle_id"),rs.getInt("order_status_id"), rs.getString("order_start_date"), rs.getString("order_cargo"),rs.getString("order_destination"),rs.getString("order_location"),rs.getString("order_finish_date") ) );
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
         return history;
         
         
       }
    
    
    
    
    public static ArrayList<Driver> getManagerDrivers(int manager_id){
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
    
    public static Driver getDriver(String username, String password){
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
    
    
    
    
    
    
    public static Admin getAdmin(String username, String password){
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
   
    
    
    
        public static Customer getCustomer(String username, String password){
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
    
    
    public static Manager getManager(String username, String password){
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
    
    public static String getWorkerRole(String username, String password){
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


    
    
    
}
