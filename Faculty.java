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
    //default constructor with no arguements
    public Faculty(){
    }
    //default constructor
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
    
    
    //Instantiates linked list iterator to traverse through linked list of 
    //  courses a professor is already teaching.  If the course is not in the
    //  list, alreadyInstructor returns false.  Otherwise, course is dropped
    //  from professor's course catalog.
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
