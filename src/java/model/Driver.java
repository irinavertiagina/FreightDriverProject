 
package model;

import java.io.Serializable;

 
public class Driver implements Serializable {
    
    private int id = 0, current_assignment_id = 0;
    private String last_name = "None", first_name = "None", contacts = "None", manager_id = "N/A", password = "None";
    
    public Driver(){
        
    }
    
    public Driver(int i, String lastName, String firstName, String contact, String managerId, String passw, int currassid){
        id = i;
        last_name = lastName;
        first_name = firstName;
        contacts = contact;
        manager_id = managerId;
        password = passw;
        current_assignment_id = currassid;
    }

    public int getCurrent_assignment_id() {
        return current_assignment_id;
    }

    public void setCurrent_assignment_id(int current_assignment_id) {
        this.current_assignment_id = current_assignment_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
