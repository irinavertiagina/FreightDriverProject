/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Kyle
 */
public class Vehicle implements Serializable {
    
    private int id = 0, driver_id = 0;
    private String service_date = "N/A", specs = "N/A", status = "N/A";
    
    public Vehicle(){
        
    }
    
    public Vehicle(int i, int driverID, String serviceDate, String sp, String stat){
        id = i;
        driver_id = driverID;
        service_date = serviceDate;
        specs = sp;
        status = stat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getService_date() {
        return service_date;
    }

    public void setService_date(String service_date) {
        this.service_date = service_date;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
