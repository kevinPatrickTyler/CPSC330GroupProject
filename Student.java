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
   public Student(String lName, String fName){
       lastName = lName;
       firstName = fName;
       courseCatalog = new LinkedList();
   }
   public void addClass(int CRN){
       ListIterator iterator = courseCatalog.listIterator();
       boolean alreadyEnrolled = false;
       while(iterator.hasNext()){
           if(iterator.equals(CRN)){
               alreadyEnrolled = true;
           }
       }
       if(alreadyEnrolled == false){
        courseCatalog.add(CRN);
       }
       else{
           System.out.println("Student already enrolled in course.");
       }
   }
   public void dropClass(int CRN){
       ListIterator iterator = courseCatalog.listIterator();
       boolean alreadyEnrolled = false;
       while(iterator.hasNext()){
           if(iterator.equals(CRN)){
               alreadyEnrolled = true;
           }
       }
       if(alreadyEnrolled == true){
        courseCatalog.remove(CRN);
       }
       else{
           System.out.println("Student is not enrolled in course.");
       }
   }
   public void printSchedule(){
       //this is unfinished; needs to interface with course object and
       //interface with SQL
       ListIterator iterator = courseCatalog.listIterator();
       while(iterator.hasNext()){
           System.out.println("course"); //this line will need to change
       }
   }
   int bannerId;
   String lastName;
   String firstName;
   String academicYear;
   LinkedList courseCatalog;
}
