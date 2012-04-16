/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;
import java.util.ListIterator;
import java.util.LinkedList;

/**
 *
 * @author kevintyler
 */
public class courseRegistrationTester extends courseAndStudentCatalog{
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        //boolean studentLoginSuccessful = false;
        Student student = new Student();  
        Administrator admin = new Administrator(); 
        Faculty faculty = new Faculty();
        String userInput = "";
        courseAndStudentCatalog catalog = new courseAndStudentCatalog();
        System.out.println("Welcome to course registration.");
        LoginPrompt login = new LoginPrompt(student, admin, faculty);
        login.run(catalog);
        boolean studentLoginSuccessful = login.studentLoginSuccessful;
        boolean facultyLoginSuccessful = login.facultyLoginSuccessful;
        boolean adminLoginSuccessful = login.adminLoginSuccessful;
        if(studentLoginSuccessful == true){
            student = login.student;
            System.out.println("Please select an option from the list below.");
            System.out.println("\tP: Print schedule");
            System.out.println("\tA: Add a class");
            System.out.println("\tD: Drop a class");
            System.out.println("\tL: Logout");
        }
        if(login.Systemquit == true){
            System.exit(0);
        }
        if(facultyLoginSuccessful == true){
            faculty = login.faculty;
        }
        if(adminLoginSuccessful == true){
            admin = login.administrator;
        }
    }
}
