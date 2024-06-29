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
public class CourseQueries {
    
    public static Connection connection;
    public static PreparedStatement addCourse, getAllCourseCodes, clearCourses;
    private static ResultSet resultSet;

    
    public static void addCourse(CourseEntry course){
        connection = DBConnection.getConnection();
        
        try{
        addCourse = connection.prepareStatement("insert into app.course values (?,?)");
        addCourse.setObject(1, course.getCode());
        addCourse.setObject(2, course.getDescription());
        
        addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(){
       connection = DBConnection.getConnection();
       ArrayList<String> courses = new ArrayList<String>();
       
       try {
       getAllCourseCodes = connection.prepareStatement("select coursecode from app.course order by coursecode");
       resultSet = getAllCourseCodes.executeQuery();
            
       while(resultSet.next()){
        courses.add(resultSet.getString(1));
        }
       }
       
       catch(SQLException sqlException)
       {
            sqlException.printStackTrace();
        }
       
        return courses;
    }
    
    public static void clearCourses(){
        connection = DBConnection.getConnection();
        try{
            clearCourses = connection.prepareStatement("DELETE FROM app.course");
            clearCourses.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
}
