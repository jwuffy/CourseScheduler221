/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class StudentEntry {
    private String studentID, firstName, lastName;
    
    public StudentEntry(String s, String f, String l){
        studentID = s;
        firstName = f;
        lastName = l;
    }
    
    public String getID(){
        return studentID;
    }
    public String getFirst(){
        return firstName;
    }
    public String getLast(){
        return lastName;
    }
    
    @Override
    public String toString(){
        return(getFirst() + " " + getLast() + ", " + getID());
    }
        
    
}
