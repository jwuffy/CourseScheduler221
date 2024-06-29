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



public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent, getAllStudents, getStudentCodes, 
            clearStudents, dropStudent, getStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student){
        connection = DBConnection.getConnection();
        
        try{
        addStudent = connection.prepareStatement("insert into app.student values (?,?,?)");
        addStudent.setObject(1, student.getID());
        addStudent.setObject(2, student.getFirst());
        addStudent.setObject(3, student.getLast());
        addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        String id, first, last;
        
        try{
            getAllStudents = connection.prepareStatement("SELECT * FROM app.Student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next()){
                id = resultSet.getString("StudentID");
                first = resultSet.getString("FirstName");
                last = resultSet.getString("LastName");
            
                students.add(new StudentEntry(id, first, last));
            }
        }
        catch(SQLException sqlexception){
            sqlexception.getStackTrace();
        }
        
        return students;
    }
    
    public static ArrayList<String> getStudentCodes(){
        connection = DBConnection.getConnection();
        ArrayList<String> students = new ArrayList<String>();
        
        try{
            getStudentCodes = connection.prepareStatement("SELECT StudentID FROM app.Student");
            resultSet = getStudentCodes.executeQuery();
            
            while(resultSet.next()){
                students.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlexception){
            sqlexception.getStackTrace();
        }
        
        return students;
    }
    
    public static void clearStudents(){
        connection = DBConnection.getConnection();
        try{
            clearStudents = connection.prepareStatement("DELETE FROM app.student");
            clearStudents.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static StudentEntry getStudent(String id){
        connection = DBConnection.getConnection();
        StudentEntry student;
        try{
            getStudent = connection.prepareStatement("SELECT FirstName, LastName FROM app.student WHERE (StudentID = ?)");
            getStudent.setObject(1, id);
            resultSet = getStudent.executeQuery();
            if (resultSet.next()){
                student = new StudentEntry(id, resultSet.getString(1), resultSet.getString(2));
            }
            else{
                student = new StudentEntry("-1", "-1","-1");
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            student = new StudentEntry(id, "-1", "-1");
        }
        return student;
    }
    
    public static void dropStudent(String id){
        connection = DBConnection.getConnection();
        try{
            dropStudent = connection.prepareStatement("DELETE FROM app.student WHERE (StudentID = ?)");
            dropStudent.setObject(1, id);
            dropStudent.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    

}
