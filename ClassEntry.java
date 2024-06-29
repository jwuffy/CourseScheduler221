/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joshw.
 */
public class ClassEntry {
    private String semester, courseCode;
    private Integer seats;
    
    public ClassEntry(String sem, String c, Integer s){
        semester = sem;
        courseCode = c;
        seats = s;
    }
    
    public String getSem(){
        return semester;
    }
    public String getCourse(){
        return courseCode;
    }
    public int getSeats(){
        return seats;
    }
    
    public String toString(){
        return courseCode;
    }
    
    // dont think this will be as of use
    public void decrementClassSeats(){
        this.seats--;
    }
    
    public static boolean isOpen(ClassEntry cla){
        return (cla.getSeats() > 0);
    }
    
//    public Object[] toArray(){
//        return[this.getClass(), this.getSeats(), this.getDescription()];
//    }
    
}
