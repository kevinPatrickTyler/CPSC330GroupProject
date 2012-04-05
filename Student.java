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
public class Student {
   public Student(String lName, String fName, String emailAddy){
       email = emailAddy;
       lastName = lName;
       firstName = fName;
       courseCatalog = new LinkedList();
   }
   public void addClass(Course course){
       if(course.numCurrentlyEnrolled >= course.capacity){
           System.out.println("Course is currently full.");
       }
       else{
        ListIterator iterator = courseCatalog.listIterator();
        boolean alreadyEnrolled = false;
        while(iterator.hasNext()){
            if(iterator.next().equals(course)){
                alreadyEnrolled = true;
            }
        }
        if(alreadyEnrolled == false){
            courseCatalog.add(course);
        }
        else{
            System.out.println("Student already enrolled in course.");
        }
       }
   }
   public void changeYear(String year){
       academicYear = year;
   }
   public void dropClass(Course course){
       ListIterator iterator = courseCatalog.listIterator();
       boolean alreadyEnrolled = false;
       while(iterator.hasNext()){
           if(iterator.next().equals(course)){
               alreadyEnrolled = true;
           }
       }
       if(alreadyEnrolled == true){
        courseCatalog.remove(course);
       }
       else{
           System.out.println("Student is not enrolled in course.");
       }
   }
   public void printSchedule(){
       ListIterator iterator = courseCatalog.listIterator();
       while(iterator.hasNext()){
           System.out.println(iterator.next()); //this line will need to change
       }
   }
   int bannerId;
   String lastName;
   String firstName;
   String academicYear;
   String email;
   LinkedList courseCatalog;
}
