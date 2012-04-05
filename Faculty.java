/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author kevintyler
 */
public class Faculty {
    public Faculty(String lastname, String firstname){
        lastName = lastname;
        firstName = firstname;
    }
    public String getDepartment(){
        if(department.length() == 0){
            return "Department unspecified.";
        }
        else{
            return department;
        }
    }
    public void setDepartment(String departName){
        department = departName;
    }
    
    
    public boolean dropCourse(Course course){
        ListIterator iterator = currentlyTeaching.listIterator();
       boolean alreadyInstructor = false;
       while(iterator.hasNext()){
           if(iterator.next().equals(course)){
               alreadyInstructor = true;
           }
       }
       if(alreadyInstructor == false){
        System.out.println("Error: cannot remove course professor is not "
                + "already teaching.");
        return false;
       }
       else{
           currentlyTeaching.remove(course);
           return true;
       }
    }
    
    String lastName, firstName, department;
    LinkedList currentlyTeaching;
}
