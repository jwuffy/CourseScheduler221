/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class CourseEntry {
    private String courseCode, description;
    
    public CourseEntry(String c, String d){
        courseCode = c;
        description = d;
    }
    
    public String getCode(){
        return courseCode;
    }
    public String getDescription(){
        return description;
    }
        
}
