/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;
import java.util.ListIterator;
import java.util.LinkedList;

/**
 * courseRegistrationTester.  Main class, sets up interface
 * and quits if user enters "Q."
 * @author kevintyler
 */
public class courseRegistrationTester extends courseAndStudentCatalog{
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        Student student = new Student();   
        Faculty faculty = new Faculty();
        String userInput = "";
        courseAndStudentCatalog catalog = new courseAndStudentCatalog();
        System.out.println("Welcome to course registration.");
        LoginPrompt login = new LoginPrompt(student, faculty);
        login.run(catalog);
        boolean studentLoginSuccessful = login.studentLoginSuccessful;
//        if(studentLoginSuccessful == true){
//            student = login.student;
//            System.out.println("Please select an option from the list below.");
//            System.out.println("\tP: Print schedule");
//            System.out.println("\tA: Add a class");
//            System.out.println("\tD: Drop a class");
//            System.out.println("\tL: Logout");
//        }
        if(login.Systemquit == true){
            System.exit(0);
        }
    }
}
