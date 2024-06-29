
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass, getAllCourseCodes, getClassSeats, 
            clearClasses, removeSeat, getDropClassChanges, dropClass;
    private static ResultSet resultSet;
    public String changeLog;
    
    public static void addClass(ClassEntry cla){
        connection = DBConnection.getConnection();
        
        try{
        addClass = connection.prepareStatement("insert into app.class values (?,?,?)");
        addClass.setObject(1, cla.getSem());
        addClass.setObject(2, cla.getCourse());
        addClass.setObject(3, cla.getSeats());
        
        addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(String sem){
       connection = DBConnection.getConnection();
       ArrayList<String> cla = new ArrayList<String>();
       
       try {
       getAllCourseCodes = connection.prepareStatement("select coursecode from app.class WHERE (Semester = ?)");
       getAllCourseCodes.setObject(1, sem);
       resultSet = getAllCourseCodes.executeQuery();
            
       while(resultSet.next()){
        cla.add(resultSet.getString(1));
        }
       }
       
       catch(SQLException sqlException)
       {
            sqlException.printStackTrace();
        }
       
        return cla;
    }
    
    public static int getClassSeats(String sem, String cc){
        connection = DBConnection.getConnection();
        int count = 0;
        
        try {
        getClassSeats = connection.prepareStatement("SELECT seats from app.class WHERE"
                + " (Semester = ?) AND (CourseCode = ?)");
        getClassSeats.setObject(1, sem);
        getClassSeats.setObject(2, cc);
        resultSet = getClassSeats.executeQuery();
        if (resultSet.next()){
            count = resultSet.getInt(1);
        }
        
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static void removeSeat(String sem, String cc){
        connection = DBConnection.getConnection();
        try{
            removeSeat = connection.prepareStatement("UPDATE app.class SET seats = seats-1 WHERE"
                        + " (Semester = ?) AND (Coursecode = ?)");
            removeSeat.setObject(1, sem);
            removeSeat.setObject(2, cc);
            removeSeat.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
       
    }
    
    public static void clearClasses(){
        connection = DBConnection.getConnection();
        try{
            clearClasses = connection.prepareStatement("DELETE FROM app.class");
            clearClasses.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    // gonna need a function before this that returns all the changes that will happen from this
    // because that information will be gone later
    
//    public static ArrayList<Object> getDropClassChanges(){
//        
//    }
    
    public static void dropClass(String sem, String cc){
        connection = DBConnection.getConnection();
        // have to remove instance of class in its own table as well as all the 
        // instances in the schedule table
        try{
            dropClass = connection.prepareStatement("DELETE FROM app.class WHERE (Semester = ?) AND (CourseCode = ?");
            dropClass.setObject(1, sem);
            dropClass.setObject(2, cc);
            dropClass.executeUpdate();
            
            dropClass = connection.prepareStatement("DELETE FROM app.schedule WHERE (Semester = ?) AND (CourseCode = ?)");
            dropClass.setObject(1, sem);
            dropClass.setObject(2, cc);
            dropClass.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
            
    }
        
        
    
 
    
    
}
