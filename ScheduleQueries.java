/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry, getSchedule, getStudentCount, 
            isScheduled, clearSchedule, getWaitlistedStudentsByClass, dropStudentScheduleByCourse,
            dropScheduleByCourse, updateScheduleEntry, getTopWaitlist;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        
        try{
            addScheduleEntry = connection.prepareStatement("INSERT INTO app.schedule VALUES (?, ?, ?, ?, ?)");
            addScheduleEntry.setObject(1, entry.getScheduleSem());
            addScheduleEntry.setObject(2, entry.getScheduleCode());
            addScheduleEntry.setObject(3, entry.getScheduleID());
            addScheduleEntry.setObject(4, entry.getScheduleStatus());
            addScheduleEntry.setObject(5, entry.getScheduleTimestamp());
            addScheduleEntry.executeUpdate();
            
            // decrements the seats if an entry successfully added
            if(entry.getScheduleStatus().equals("S")){
                ClassQueries.removeSeat(entry.getScheduleSem(), entry.getScheduleCode());
            } 
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }
    
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
               
        try {
            getSchedule = connection.prepareStatement("SELECT * FROM app.schedule WHERE (StudentID = ?) AND (Semester = ?)");
            getSchedule.setObject(1, studentID);
            getSchedule.setObject(2, semester);
            resultSet = getSchedule.executeQuery();
            
            while(resultSet.next()){
                String code = resultSet.getString("Coursecode");
                String status = resultSet.getString("Status");
                Timestamp timestamp = resultSet.getTimestamp("Timestamp");
                
                schedule.add(new ScheduleEntry(semester, code, studentID, status, timestamp));
            }
        }
        catch(SQLException exception){
            exception.getStackTrace();
        }
        return schedule;
    }
    
    public static int getScheduleStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        // count set to -1 for testing purposes as long as its reset to 0 in try bracket outside of while loop
        int count = 0;
        
        try{
            getStudentCount = connection.prepareStatement("SELECT StudentID FROM app.schedule WHERE (Semester = ?) AND (Coursecode = ?)");
            getStudentCount.setObject(1, semester);
            getStudentCount.setObject(2, courseCode);
            resultSet = getStudentCount.executeQuery();
            while(resultSet.next()){
                count ++;
            }
        }
       
        catch(SQLException exception){
            exception.getStackTrace();
        }
       
        return count;
    }
    //returns S for scheduled, W for waitlisted, N for null/not scheduled
    public static String isScheduled(String id, String cc, String s){
        connection = DBConnection.getConnection();
        try{
            isScheduled = connection.prepareStatement("SELECT * FROM app.schedule WHERE (StudentID = ?) AND (CourseCode = ?) AND (Semester = ?)");
            isScheduled.setObject(1, id);
            isScheduled.setObject(2, cc);
            isScheduled.setObject(3, s);
            resultSet = isScheduled.executeQuery();
            
            if(resultSet.next()){
                // might be redundant
                if(resultSet.getString("Status").equals("S")){
                    return "S";
                }
                return "W";
            }            
        }
        
        catch(SQLException exception){
            exception.getStackTrace();
        }
        
        return "N";        
    }
    
    public static void clearSchedule(){
        connection = DBConnection.getConnection();
        try{
            clearSchedule = connection.prepareStatement("DELETE FROM app.schedule");
            clearSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String sem, String cc){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist = new ArrayList<ScheduleEntry>();
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("SELECT * FROM app.Schedule WHERE (Semester = ?) "
                    + "AND (CourseCourse = ?) AND (status = W)");
            getWaitlistedStudentsByClass.setObject(1, sem);
            getWaitlistedStudentsByClass.setObject(2, cc);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next()){
                String studentID = resultSet.getString("StudentID");
                Timestamp timestamp = resultSet.getTimestamp("Timestamp");
                
                waitlist.add(new ScheduleEntry(sem, cc, studentID, "W", timestamp));
            }           
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
    
    public static void dropStudentScheduleByCourse(String sem, String studentID, String cc){
        connection = DBConnection.getConnection();
        try{
            dropStudentScheduleByCourse = connection.prepareStatement("DELETE FROM app.schedule WHERE"
                    + "(Semester = ?) AND (StudentID = ?) AND (CourseCode = ?)");
            dropStudentScheduleByCourse.setObject(1, sem);
            dropStudentScheduleByCourse.setObject(2, studentID);
            dropStudentScheduleByCourse.setObject(3, cc);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String sem, String cc){
        connection = DBConnection.getConnection();
        try{
            clearSchedule = connection.prepareStatement("DELETE FROM app.schedule WHERE"
                    + "(Semester = ?) AND (CourseCode = ?)");
            clearSchedule.setObject(1, sem);
            clearSchedule.setObject(2, cc);
            clearSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    //get next in line or else a list by class of schedule entries sorted by timestamp
    
    
    // gonna leave this method alone for now but I think it will be used to move students off the waitlist
    public static void updateScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateScheduleEntry = connection.prepareStatement("UPDATE app.schedule SET status = 'S' "
                    + "WHERE (Semester = ?) AND (CourseCode = ?) AND (StudentID = ?)");
            updateScheduleEntry.setObject(1, entry.getScheduleSem());
            updateScheduleEntry.setObject(2, entry.getScheduleCode());
            updateScheduleEntry.setObject(3, entry.getScheduleID());
            updateScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ScheduleEntry getTopWaitlist(String s, String cc){
        connection = DBConnection.getConnection();
        
        try{
            getTopWaitlist = connection.prepareStatement("SELECT * FROM app.Schedule "
                    + "WHERE Semester = ? AND CourseCode = ? AND Status = 'W' ORDER BY Timestamp");
            getTopWaitlist.setObject(1, s);
            getTopWaitlist.setObject(2, cc);
            resultSet = getTopWaitlist.executeQuery();
            if(resultSet.next()){
                String id = resultSet.getString("StudentID");
                Timestamp t = resultSet.getTimestamp("Timestamp");
                return new ScheduleEntry(s, cc, id, "S", t);
            }
        }   
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return null;
    }
    
    
}
