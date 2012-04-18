/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ListIterator;
import java.util.LinkedList;
/**
 *  LoginPrompt.  Primary interface.  Contained in a separate class via 
 *  run() function in order to reduce some coding.
 * @author kevintyler
 */
public class LoginPrompt {
    public LoginPrompt(Student stud,  Faculty facult){
        student = stud;
        faculty = facult;       
    }
    public void run(courseAndStudentCatalog catalog){
        java.util.Scanner input2 = new java.util.Scanner(System.in);
        System.out.println("\tL: Login");
        System.out.println("\tN: New student user");
        System.out.println("\tQ: Quit");
        System.out.println("\tV: View all available courses");
        System.out.println("Please select an option from the list above: ");
        String userInput = input2.nextLine();
        while(! userInput.equals("Q")){
            if(userInput.equals("L")){
                System.out.println("Please select an option from the list below.");
                System.out.println("\tS: Student login");
                System.out.println("\tI: Instructor login");
                System.out.println("\tL: Logout");
                userInput = input2.nextLine();
                if(userInput.equals("I")){
                    while(! userInput.equals("L")){
                        System.out.println("Faculty login. \n");
                        System.out.print("Email address: ");
                        String email = input2.nextLine();
                        System.out.print("Password: ");
                        String password = input2.nextLine();
                        if(catalog.facultyLogin(email, password) == true){
                            faculty = catalog.getFaculty(email);
                            while(! userInput.equals("L")){
                                System.out.println("Faculty login complete.");
                                System.out.println("V: View Enrollment");
                                System.out.println("F: Force Add A Student");
                                System.out.println("L: Logout");
                                System.out.println("Please select from the menu above.");
                                userInput = input2.nextLine();
                                if(userInput.equals("V")){
                                    System.out.println("What course would you like to view the current enrollment of (CRN)?");
                                    int response = input2.nextInt();
                                    int numEnroll = catalog.getEnrollment(response);
                                    if(numEnroll == -1){
                                        System.out.println("Error: must search by valid CRN number.");
                                    }
                                    else{
                                        System.out.println("Current enrollment is " + numEnroll + "\n");
                                    }
                                }
                                if(userInput.equals("F")){
                                    System.out.println("Enter student email: ");
                                    String studEmail = input2.nextLine();
                                    System.out.println("Enter a course to add to (CRN): ");
                                    int courseNumber = input2.nextInt();
                                    catalog.insertIntoCurrentScheduleTable(studEmail, courseNumber);
                                }
                                

                            }
                        }
                    }
                }
                //end instructor login
                
                //begin student
                if(userInput.equals("S")){
                    System.out.println("Student login.\n");
                    System.out.print("Email address: ");
                    String email = input2.nextLine();
                    System.out.print("Password: ");
                    String password = input2.nextLine();
                    if(catalog.studentLogin(email, password) == true){
                        Student newStudent = new Student(email, password);
                        studentLoginSuccessful = true;
                        System.out.println("\nLogin confirmed.\n");
                        System.out.println("Student login.\n");
                        System.out.println("Account associate with: "+ newStudent.email);                        
                        while(! userInput.equals("L")){
                            System.out.println("A: Add Course");
                            System.out.println("D: Drop Course");
                            System.out.println("P: Print schedule");
                            System.out.println("L: Logout");
                            System.out.println("Select an option from above.");
                            userInput = input2.nextLine();
                            String studentEmailAddress = newStudent.email;
                            if(userInput.equals("A")){
                                System.out.println("Enter a CRN of course to add.");
                                int courseNum = input2.nextInt();
                                if(catalog.courseExistsInCatalog(courseNum) == true && catalog.studentAlreadyEnrolled(studentEmailAddress, courseNum) == false){
                                    if(catalog.courseAlreadyInstantiated(courseNum) == false){
                                        Course newCourse = new Course(courseNum);
                                        catalog.insertIntoAlreadyInstantiatedTable(courseNum);
                                    }
                                    catalog.insertIntoCurrentScheduleTable(studentEmailAddress, courseNum);
                                    System.out.println("Course " + courseNum + " added successfully.");
                                }
                                else{
                                    if(catalog.courseExistsInCatalog(courseNum) == false){
                                        System.out.println("You must enter a valid CRN.");
                                    }
                                    else{
                                        System.out.println("You cannot add a class you are already registered for.");
                                    }
                                }

                            }//end add
                            if(userInput.equals("P")){
                                System.out.println("Schedule below: \n");
                                catalog.printSchedule(studentEmailAddress);
                             }//end print course
                             if(userInput.equals("D")){
                                System.out.println("Enter a CRN of course to drop.");
                                int courseNum = input2.nextInt();               
                                if(catalog.courseExistsInCatalog(courseNum) == true && catalog.studentAlreadyEnrolled(studentEmailAddress, courseNum) == true){
                                    catalog.dropCourse(courseNum, studentEmailAddress);
                                    catalog.decrementEnrollment(courseNum);             
                                    System.out.println("Course " + courseNum + " dropped successfully.");
                                }
                                else{
                                    if(catalog.courseExistsInCatalog(courseNum) == true && catalog.studentAlreadyEnrolled(studentEmailAddress, courseNum) == false){
                                        System.out.println("You can't drop a class that you are not registered for.");
                                    }
                                    else{
                                        System.out.println("Please enter a valid CRN.");
                                    }
                                }
                                
                            }//end Drop course
                        
                        }
                    }
                    else{
                        System.out.println("Login incorrect.  Please try again.");
                        userInput = "L";
                    }
                }//end
            }
            if(userInput.equals("V")){
                System.out.println("\nS: Search for classes by four letter major code");
                System.out.println("D: Display all available classes");
                System.out.println("Please select an option from the list below.");
                userInput = input2.nextLine();
                boolean searchComplete = false;
                while(! searchComplete){searchComplete = true;}
                    if(userInput.equals("S")){
                        System.out.println("Enter a four letter major code: ");
                        String majorCode = input2.nextLine();
                        Connection myCon;
                        Statement myStmt;
                        try{
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            // Connect to an instance of mysql with the follow details
                            //varables: SQL database location, password
                            myCon = DriverManager.getConnection(
                                    "jdbc:mysql://localhost/umw",
                                    "root","");
                            //create statement
                            myStmt = myCon.createStatement();
                            //generate query and store in result variable
                            ResultSet result = myStmt.executeQuery(
                                "SELECT * FROM umw2010");
                            //iterate through result sets
                            //since we're searching by CRN, there should only be one
                            while (result.next()){
                                //set CRN, course, section etc variables from database
                                int CRN = Integer.parseInt(result.getString("crn"));            
                                String course = result.getString("course");            
                                int section = Integer.parseInt(result.getString("section"));
                                String title = result.getString("title");   

                                String poiString = result.getString("poi"); 
                                char poi;
                                if(poiString.length() > 0){
                                    poi = poiString.charAt(0);
                                }
                                else{
                                    poi = 'N';
                                }
                                char co;
                                String coString = result.getString("co");
                                if(coString.length() > 0){
                                    co = coString.charAt(0);
                                }
                                else{
                                    co = 'N';
                                }
                                char prereqs;
                                String prString = result.getString("prereqs"); 
                                if(prString.length() > 0){
                                    prereqs = prString.charAt(0); 
                                }
                                else{
                                    prereqs = 'N';
                                }
                                String atc = result.getString("atc");
                                int credits = Integer.parseInt(result.getString("credits"));
                                String time = result.getString("time");
                                String days = result.getString("days");
                                String building = result.getString("building");
                                String room = result.getString("room");
                                String instructor = result.getString("instructor");
                                String requirements = result.getString("requirements");
                                
                                if(course.startsWith(majorCode)){
                                    System.out.println(CRN + " " + course + " " + section + " " 
                                    + title + " " + poi + " " + co + " "
                                    + prereqs + " " + atc + " " + credits + " "
                                    + time + " " + days + " " + building + " "
                                    + room + " " + instructor + requirements);
                                }
                            //close connection                      
                        }
                            myCon.close();  
                        }
                        catch (Exception sqlEx){
                        System.err.println(sqlEx);
                        }
                        
                        searchComplete = true;
                        System.out.println("");
                        System.out.println("");
                        System.out.println("\nS: Search for classes by four letter major code");
                        System.out.println("D: Display all available classes");
                        System.out.println("L: Log out.");
                        System.out.println("Please select an option from the list below.");
                        userInput = input2.nextLine();
                    }
                    
                    else if(userInput.equals("D")){
                        Connection myCon;
                        Statement myStmt;
                        try{
                            Class.forName("com.mysql.jdbc.Driver").newInstance();
                            // Connect to an instance of mysql with the follow details
                            //varables: SQL database location, password
                            myCon = DriverManager.getConnection(
                                    "jdbc:mysql://localhost/umw",
                                    "root","");
                            //create statement
                            myStmt = myCon.createStatement();
                            //generate query and store in result variable
                            ResultSet result = myStmt.executeQuery(
                                "SELECT * FROM umw2010");
                            //iterate through result sets
                            //since we're searching by CRN, there should only be one
                            while (result.next()){
                                //set CRN, course, section etc variables from database
                                int CRN = Integer.parseInt(result.getString("crn"));            
                                String course = result.getString("course");            
                                int section = Integer.parseInt(result.getString("section"));
                                String title = result.getString("title");   

                                String poiString = result.getString("poi"); 
                                char poi;
                                if(poiString.length() > 0){
                                    poi = poiString.charAt(0);
                                }
                                else{
                                    poi = 'N';
                                }
                                char co;
                                String coString = result.getString("co");
                                if(coString.length() > 0){
                                    co = coString.charAt(0);
                                }
                                else{
                                    co = 'N';
                                }
                                char prereqs;
                                String prString = result.getString("prereqs"); 
                                if(prString.length() > 0){
                                    prereqs = prString.charAt(0); 
                                }
                                else{
                                    prereqs = 'N';
                                }
                                String atc = result.getString("atc");
                                int credits = Integer.parseInt(result.getString("credits"));
                                String time = result.getString("time");
                                String days = result.getString("days");
                                String building = result.getString("building");
                                String room = result.getString("room");
                                String instructor = result.getString("instructor");
                                String requirements = result.getString("requirements");
                                
                                System.out.println(CRN + " " + course + " " + section + " " 
                                + title + " " + poi + " " + co + " "
                                + prereqs + " " + atc + " " + credits + " "
                                + time + " " + days + " " + building + " "
                                + room + " " + instructor + requirements);
                            }
                            //close connection
                            myCon.close();                       
                        }
                        catch (Exception sqlEx){
                        System.err.println(sqlEx);
                        }
                        searchComplete = true;
                        System.out.println("");
                        System.out.println("");
                      
                    
                }//end of D
            }//end V
            if(userInput.equals("N")){
                boolean newStudentUserComplete = false;
                while(! newStudentUserComplete){
                    newStudentUserComplete = true;
                    System.out.println("Enter an email address: ");
                    String email = input2.nextLine();
                    System.out.println("Enter a password: ");
                    String password = input2.nextLine();
                    if(catalog.studentExists(email) == true){
                        System.out.println("Student email already exists.");
                        System.out.println("Please register again using a different email address.");                    
                    }
                    else{
                        Student studentObj = new Student(email, password);
                        newStudentUserComplete = true;
                        System.out.println("\tL: Login");
                        System.out.println("\tN: New student user");
                        System.out.println("\tQ: Quit");
                        System.out.println("\tV: View all available courses");
                        System.out.println("Please select an option from the list above: ");
                        userInput = input2.nextLine();
                    }
                }
            }
            
        System.out.println("Options:");
        System.out.println("\tL: Login");
        System.out.println("\tN: New student user");
        System.out.println("\tQ: Quit");
        System.out.println("\tV: View all available courses");
        System.out.println("Please select an option from the list above: ");
        userInput = input2.nextLine();
            //}//*****************
        }//end of first L
        //end of first Q
        
        //end while loop
        if(userInput.equals("Q")){
            Systemquit = true;
        
    }
}
    
    Student student; Faculty faculty;
    boolean Systemquit, studentLoginSuccessful;
}