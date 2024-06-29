/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions, getScheduledStudentsByClass, getWaitlistedStudentsByClass;
    
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String sem){
       connection = DBConnection.getConnection();
       ArrayList<ClassDescription> descriptions = new ArrayList<ClassDescription>();
       
       try {
       getAllClassDescriptions = connection.prepareStatement("SELECT app.class.courseCode, "
               + "description, seats FROM app.class, app.course WHERE semester = ? AND "
               + "app.class.courseCode = app.course.courseCode ORDER BY app.class.courseCode");
       getAllClassDescriptions.setObject(1, sem);
       resultSet = getAllClassDescriptions.executeQuery();
            
       while(resultSet.next()){
        descriptions.add(new ClassDescription(resultSet.getString(1),resultSet.getString(2), resultSet.getInt(3)));
        }
       }
       
       catch(SQLException sqlException)
       {
            sqlException.printStackTrace();
        }
       
        return descriptions;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String sem, String cc){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try{
            getScheduledStudentsByClass = connection.prepareStatement("SELECT app.Student.StudentID, FirstName, LastName FROM"
                    + " app.student, app.schedule  WHERE (app.student.studentID = app.schedule.studentID) AND (app.schedule.Semester = ?)"
                    + " AND (app.schedule.CourseCode = ?) AND (app.schedule.Status = 'S') ORDER BY app.schedule.timestamp");          
            getScheduledStudentsByClass.setObject(1, sem);
            getScheduledStudentsByClass.setObject(2, cc);
            resultSet = getScheduledStudentsByClass.executeQuery();  
            
            while(resultSet.next()){
                String id, first, last;
                id = resultSet.getString(1);
                first = resultSet.getString(2);
                last = resultSet.getString(3); 
                
                students.add(new StudentEntry(id, first, last));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return students;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String sem, String cc){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("SELECT app.Student.StudentID, FirstName, LastName FROM"
                    + " app.student, app.schedule  WHERE (app.student.studentID = app.schedule.studentID) AND (app.schedule.Semester = ?)"
                    + " AND (app.schedule.CourseCode = ?) AND (app.schedule.Status = 'W') ORDER BY app.schedule.timestamp");          
            getWaitlistedStudentsByClass.setObject(1, sem);
            getWaitlistedStudentsByClass.setObject(2, cc);
            resultSet = getWaitlistedStudentsByClass.executeQuery();  
            
            while(resultSet.next()){
                String id, first, last;
                id = resultSet.getString(1);
                first = resultSet.getString(2);
                last = resultSet.getString(3); 
                
                students.add(new StudentEntry(id, first, last));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return students;
    }
       
}
