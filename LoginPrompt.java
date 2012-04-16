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
public class LoginPrompt {
    public LoginPrompt(Student stud,  Administrator admin, Faculty facult){
        student = stud;
        administrator = admin;
        faculty = facult;       
    }
    public void run(courseAndStudentCatalog catalog){
        java.util.Scanner input2 = new java.util.Scanner(System.in);
        System.out.println("\tL: Login");
        System.out.println("\tN: New student user");
        System.out.println("\tQ: Quit");
        System.out.println("Please select an option from the list above: ");
        String userInput = input2.nextLine();
        while(! userInput.equals("Q")){
            if(userInput.equals("L")){
                System.out.println("Please select an option from the list below.");
                System.out.println("\tS: Student login");
                System.out.println("\tI: Instructor login");
                System.out.println("\tA: Administrator login");
                System.out.println("\tB: Back");
                userInput = input2.nextLine();
                if(userInput.equals("S")){
                    System.out.println("Student login.\n");
                    System.out.print("Email address: ");
                    String email = input2.nextLine();
                    System.out.print("Password: ");
                    String password = input2.nextLine();
                    if(catalog.studentLogin(email, password) == true){
                        student = catalog.getStudent(email);
                        studentLoginSuccessful = true;
                        break;
                    }
                    else{
                        System.out.println("Login incorrect.  Please try again.");
                        userInput = "L";
                    }
                }
            }
            else if(userInput.equals("N")){
                System.out.print("Enter an email address: ");
                String email = input2.nextLine();
                System.out.print("Enter a password: ");
                String password = input2.nextLine();
                if(catalog.studentExists(email) == true){
                    System.out.println("Student email already exists.");
                    System.out.println("Please register again using a different email address.");                    
                }
                else{
                    Student studentObj = new Student(email, password);
                    catalog.studentNameList.add(email);
                    catalog.studentList.add(studentObj);
                    System.out.println("\tL: Login");
                    System.out.println("\tN: New student user");
                    System.out.println("\tQ: Quit");
                    System.out.println("Please select an option from the list above: ");
                    userInput = input2.nextLine();
                }
            }
        }
        //end while loop
        if(userInput.equals("Q")){
            Systemquit = true;
        }
    }
    Student student;  Administrator administrator; Faculty faculty;
    boolean studentLoginSuccessful, facultyLoginSuccessful, adminLoginSuccessful, Systemquit;
}