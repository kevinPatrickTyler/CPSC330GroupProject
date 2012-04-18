/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * courseAndStudentCatalog.java.  File to hold a catalog.  A catalog hold all
 * the courses and user information for a given semester.  For example, there would
 * be one catalog per semester.  For this demo, there is one catalog reference,
 * "catalog." 
 * @author kevintyler
 */
public class courseAndStudentCatalog {
    public courseAndStudentCatalog(){
        instantiatedCourses = new LinkedList();
        studentList = new LinkedList <Student>();
    }
    /**
     * insertIntoCurrentScheduleTable.  Inserts user's email address and 
     *  crn into a junction table.  This is used in keeping track of which
     *  students are taking which classes and vice-versa.  Used in this 
     *  program to print student schedules and view current course enrollment
     *  numbers.
     * @param emailAddy
     * @param crn
     * @return boolean
     */
    public boolean insertIntoCurrentScheduleTable(String emailAddy, int crn){
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
        String sql = "INSERT INTO currentSchedule VALUES ('" + emailAddy+ "','" + crn + "')"; 
        myStmt.executeUpdate(sql);
        //iterate through result sets
        //since we're searching by CRN, there should only be one
        this.incrementEnrollment(crn);
        //close connection
        myCon.close();
        }
        catch (Exception sqlEx){
        //System.err.println(sqlEx);
        }
        return true;
    }
    /**
     * insertIntoAlreadyInstantiatedTable.  Used as a means of keep track of
     * which courses have been instantiated, and which haven't.  Prevents 
     * same course having more than one object.
     * @param crn
     * @return boolean
     */
    public boolean insertIntoAlreadyInstantiatedTable(int crn){
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
        String sql = "INSERT INTO previouslyInstanitatedClasses VALUES ('" + crn + "')"; 
        myStmt.executeUpdate(sql);
        //iterate through result sets
        //since we're searching by CRN, there should only be one
        
        //close connection
        myCon.close();
        }
        catch (Exception sqlEx){
        //System.err.println(sqlEx);
        }
        return true;
    }
    /**
     * incrementEnrollment.  Used to increment course enrollment by one when student 
     * adds class, or is force added.
     * @param crn 
     */
    public void incrementEnrollment (int crn){
       Connection conn;
       Statement myStmt;
       try{
       Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Connect to an instance of mysql with the follow details
        //varables: SQL database location, password
       conn = DriverManager.getConnection(
               "jdbc:mysql://localhost/umw",
               "root","");
        //create statement
       PreparedStatement ps = conn.prepareStatement(
      "UPDATE umw2010 SET currentEnrollment = ? WHERE crn = ?");
        
        // set the preparedstatement parameters
        int currentEnrollment = this.getEnrollment(crn);
        currentEnrollment++;
        ps.setInt(2, crn);
        ps.setInt(1,currentEnrollment);

        // call executeUpdate to execute our sql update statement
        ps.executeUpdate();
        ps.close();
  

        //generate query and store in result variable
           
       conn.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
  
    }
    /**
     * decrementEnrollment.  Used to decrement course enrollment when student
     * drops class
     * @param crn 
     */
    public void decrementEnrollment (int crn){
       Connection conn;
       Statement myStmt;
       try{
       Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Connect to an instance of mysql with the follow details
        //varables: SQL database location, password
       conn = DriverManager.getConnection(
               "jdbc:mysql://localhost/umw",
               "root","");
        //create statement
       PreparedStatement ps = conn.prepareStatement(
      "UPDATE umw2010 SET currentEnrollment = ? WHERE crn = ?");
        
        // set the preparedstatement parameters
        int currentEnrollment = this.getEnrollment(crn);
        currentEnrollment--;
        ps.setInt(2, crn);
        ps.setInt(1,currentEnrollment);

        // call executeUpdate to execute our sql update statement
        ps.executeUpdate();
        ps.close();
  

        //generate query and store in result variable
           
       conn.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
  
    }
    /**
     * courseAlreadyInstantiated.  Demonstrates whether or not 
     * a course has been created.
     * @param CRN
     * @return boolean
     */
    public boolean courseAlreadyInstantiated(int CRN){
       
       boolean alreadyInstantiated = false;
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
       System.out.println("Welcome to the method2");
        //generate query and store in result variable
       ResultSet result = myStmt.executeQuery("SELECT * FROM previouslyInstanitatedClasses WHERE crn = " + CRN);
       System.out.println("Result " + result);
       String resultString = "";
       while (result.next()){
        resultString =  result.getString("crn");
       }
       System.out.println("Result string " + resultString);
       if(! resultString.equals("")){
           alreadyInstantiated = true;
       }      
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
        return alreadyInstantiated;
    }
    /**
     * courseExistsInCatalog.  Verifies in course exists in SQL database.
     * @param CRN
     * @return boolean
     */
    public boolean courseExistsInCatalog(int CRN){
       boolean courseExists = false;
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
       ResultSet result = myStmt.executeQuery(            "SELECT * FROM umw2010 WHERE crn = " + CRN);
       String resultString = "";
       while(result.next()){
           resultString =  result.getString("crn");
       }
       if(! resultString.equals("")){
           courseExists = true;
       }      
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
       return courseExists;
    }
    /**
     * studentLogin.  Verifies if student login and password information are legitimate.
     * @param email
     * @param password
     * @return boolean
     */
    public boolean studentLogin(String email, String password){       
        Student student = new Student();
        boolean accountExists = false;
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
               "SELECT * FROM student");
       while(result.next()){
           String resultString =  result.getString("emailAddress");
           String resultString2 = result.getString("password");
           System.out.println(resultString);
           System.out.println(resultString2);
           if(resultString.equals(email) && resultString2.equals(password)){
                accountExists = true;
            }      
       }
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
        return accountExists;
    }
    /**
     * dropCourse.  Removes user from course, and removes row from currentEnrollment table
     * @param crn
     * @param emailAddy 
     */
    public void dropCourse(int crn, String emailAddy){
       Connection conn;
       Statement myStmt;
       try{
       Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Connect to an instance of mysql with the follow details
        //varables: SQL database location, password
       conn = DriverManager.getConnection(
               "jdbc:mysql://localhost/umw",
               "root","");
        //create statement
       PreparedStatement ps = conn.prepareStatement(
      "DELETE FROM currentSchedule WHERE emailAddress = ? AND crn = ?");
        
        // set the preparedstatement parameters
        int currentEnrollment = this.getEnrollment(crn);
        currentEnrollment++;
        ps.setInt(2, crn);
        ps.setString(1,emailAddy);

        // call executeUpdate to execute our sql update statement
        ps.executeUpdate();
        ps.close();
  

        //generate query and store in result variable
           
       conn.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
  
    }
    /**
     * facultyLogin.  Legitimates faculty email/password.
     * @param email
     * @param password
     * @return boolean
     */
    public boolean facultyLogin(String email, String password){   
        Faculty faculty = new Faculty();
        boolean accountExists = false;
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
               "SELECT * FROM faculty");
       while(result.next()){
           String resultString =  result.getString("emailAddress");
           String resultString2 = result.getString("password");
           System.out.println(resultString);
           System.out.println(resultString2);
           if(resultString.equals(email) && resultString2.equals(password)){
                accountExists = true;
            }      
       }
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
        return accountExists;
    }
    /**
     * Verifies that student exists in SQL file.  Used in avoiding null pointer
     * exceptions.
     * @param studentEmail
     * @return boolean
     */
    public boolean studentExists(String studentEmail){
       boolean studentExistence = false;
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
               "SELECT * FROM student");
       String resultString =  result.getString("emailAddress");
       while(result.next()){
        if(resultString.equals(studentEmail)){
            studentExistence = true;
        }      
       }
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
       return studentExistence;
    }
    /**
     * studentAlreadyEnrolled.  Usd to determine if student is already enrolled
     * in a course.  Used for exception handling
     * @param studentEmail
     * @param crn
     * @return boolean
     */
    public boolean studentAlreadyEnrolled(String studentEmail, int crn){
       boolean studentExistence = false;
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
               "SELECT * FROM currentSchedule");
       String resultString =  "";
       int resultString2 = 0;
       while(result.next()){
           resultString =  result.getString("emailAddress");
           resultString2 = result.getInt("crn");
        if(resultString.equals(studentEmail) && resultString2 == crn){ 
            return true;
        }      
       }
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
       return studentExistence;
    }
    /**
     * getStudent.  Used in login processing.
     * @param studEmail
     * @return Student
     */
    public Student getStudent(String studEmail){
       Student student = new Student();
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
       ResultSet result = myStmt.executeQuery("SELECT * "
               + "FROM student WHERE emailAddress = " + studEmail);
       String emailAddy =  result.getString("emailAddress");
       String pw = result.getString("password");
       student.email = emailAddy;
       student.password = pw;
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
       return student;
    }
    /**
     * getFaculty.  Used in login processing.
     * @param facEmail
     * @return Faculty
     */
    public Faculty getFaculty(String facEmail){
       Faculty faculty = new Faculty();
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
       ResultSet result = myStmt.executeQuery("SELECT * "
               + "FROM faculty WHERE emailAddress = " + facEmail);
       String emailAddy =  result.getString("emailAddress");
       String pw = result.getString("password");
       faculty.email = emailAddy;
       faculty.password = pw;
       myCon.close();
       }
       catch (Exception sqlEx){
       //System.err.println(sqlEx);
       }
       return faculty;
    }   
    /**
     * getEnrollment.  Used in incrementing, decrementing, and determining course enrollments.
     * @param crn
     * @return int
     */
    public int getEnrollment(int crn){
        int numEnrolled = 0;
        if(courseExistsInCatalog(crn) == false){
            //return -1;
        }
        else{
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
            
            
            PreparedStatement ps = myCon.prepareStatement("SELECT currentEnrollment FROM umw2010 WHERE crn =  ?");
        
        // set the preparedstatement parameters
        ps.setInt(1, crn);

        // call executeUpdate to execute our sql update statement
        ResultSet result = ps.executeQuery();
        while (result.next()){
                //set CRN, course, section etc variables from database
                numEnrolled = result.getInt("currentEnrollment");
            }
        ps.close();
            
            
            
//            //generate query and store in result variable
//            ResultSet result = myStmt.executeQuery(
//            "SELECT currentEnrollment FROM umw2010 WHERE crn = " + crn);
//            //iterate through result sets
//            //since we're searching by CRN, there should only be one
//            while (result.next()){
//                //set CRN, course, section etc variables from database
//                numEnrolled = result.getInt("currentEnrollment");
//            }
            //close connection
            myCon.close();                       
        }
        catch (Exception sqlEx){
        //System.err.println(sqlEx);
        }        
    }
        return numEnrolled;
        //return 1;
    }
    /**
     * printSchedule. Prints users table according to SQL junction table, 'currentlyEnrolled.'
     * @param email 
     */
    
    public void printSchedule(String email){ 
       String course = "", title = "", time = "", days = "", building = "", room = "";
       Connection conn;
       Statement myStmt;
       try{
       Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Connect to an instance of mysql with the follow details
        //varables: SQL database location, password
       conn = DriverManager.getConnection(
               "jdbc:mysql://localhost/umw",
               "root","");
        //create statement
       PreparedStatement ps = conn.prepareStatement(
      "SELECT umw2010.course, umw2010.title, umw2010.time, umw2010.days, umw2010.building, umw2010.room FROM umw2010 INNER JOIN currentSchedule ON umw2010.crn = currentSchedule.crn WHERE currentSchedule.emailAddress =  ?");        
        // set the preparedstatement parameters        
        ps.setString(1,email);
        // call executeUpdate to execute our sql update statement
        ResultSet result = ps.executeQuery();
        while(result.next()){
            course = result.getString("course");
            title = result.getString("title");
            time = result.getString("time");
            days = result.getString("days");
            building = result.getString("building");
            room = result.getString("room");            
            System.out.println(course + " " + title + " " + time + " " + days + " " + building + " " + room);
        }
        ps.close();
        //generate query and store in result variable
        conn.close();       
       }
       catch (Exception sqlEx){
       //sSystem.err.println(sqlEx);
       }
    }
    LinkedList instantiatedCourses; //linked list of course CRN ints
    LinkedList <Student>studentList; //linked list of student objects
}
