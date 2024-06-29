
import java.sql.Timestamp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class ScheduleEntry {
    private String semester, courseCode, studentID, status;
    private Timestamp timestamp;
    
    public ScheduleEntry(String sem, String cc, String id, String s, Timestamp t){
        semester = sem;
        courseCode = cc;
        studentID = id;
        status = s;
        timestamp = t;
    }
    
    public String getScheduleSem(){
        return semester;
    }
    public String getScheduleCode(){
        return courseCode;
    }
    public String getScheduleID(){
        return studentID;
    }
    public String getScheduleStatus(){
        return status;
    }
    public Timestamp getScheduleTimestamp(){
        return timestamp;
    }
    
    
        
}

