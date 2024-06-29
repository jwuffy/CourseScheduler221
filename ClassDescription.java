/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class ClassDescription {
    
    private String courseCode, description;
    private int seats;
    
    public ClassDescription(String c, String d, int s){
        courseCode = c;
        description = d;
        seats = s;
    }
    
    public String getCode(){
        return courseCode;
    }
    public String getDescription(){
        return description;
    }
    public int getSeats(){
        return seats;
    }
        
       
}
