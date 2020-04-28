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

    
    
    
}
