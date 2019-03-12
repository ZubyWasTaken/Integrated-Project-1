/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integratedproject;

/**
 *
 * @author Zuby
 */
public class Patient {

    private String forename;
    private String surname;
    private String userID;

    public void setForename(String string){
        this.forename = string;
    
    }
    
    public void setSurname(String string){
        this.surname = string;
    }
    
    public void setuserID(String string){
        String userID = string;
    }
    
    public String getForename(){
        return this.forename;
    }
    public String getSurname(){
        return this.surname;
    }
    public String getuserID(){
        return userID;
    }
}
