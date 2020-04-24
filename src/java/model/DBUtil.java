package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil {
        public static ArrayList<Order> getOrderHistory() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            String dbURL = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM `order`");

            while (result.next()) {
            //    Order item = new Order(result.getInt("order_id"), result.getString("order_cargo"), result.getString("order_start_date"),  result.getString("order_location"), result.getString("order_destination")   );

             //   list.add(item);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
        return list;
        }
        
        public static ArrayList<Vehicle> seeVehicleList() {
        ArrayList<Vehicle> list = new ArrayList<>();
        try {
            String dbURL = "jdbc:mysql://localhost:3306/db";
            String user = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, user, password);
            Statement statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery("SELECT vehicle.vehicle_id, vehicle.vehicle_service_date, vehicle.vehicle_info, vehicle.vehicle_status, vehicle.driver_id, driver.driver_last_name\n" +
"FROM vehicle INNER JOIN driver ON vehicle.driver_id = driver.driver_id");
            while (result.next()) {
                Vehicle i = new Vehicle(result.getInt("vehicle_id"), result.getInt("driver_id"), result.getString("vehicle_status"),
                result.getString("vehicle_service_date"), result.getString("vehicle_info") );

                list.add(i);
            }

            connection.close();

        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
        return list;
        }
        
        
        
         
   
        
        
        
        
        
        
        
         
        
         
         
         
                 
                  
                  
            
        
        
        
         
 
}