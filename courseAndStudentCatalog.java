/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author kevintyler
 */
public class courseAndStudentCatalog {
    public courseAndStudentCatalog(){
        studentNameList = new LinkedList();
        instantiatedCourses = new LinkedList();
        studentList = new LinkedList <Student>();
    }
    public boolean courseAlreadyInstantiated(int CRN){
       ListIterator iterator = instantiatedCourses.listIterator();
       boolean alreadyInstantiated = false;
       while(iterator.hasNext()){
           if(iterator.next().equals(CRN)){
               alreadyInstantiated = true;
           }
       }
        return alreadyInstantiated;
    }
    public boolean courseExistsInCatalog(int CRN){
       //THIS NEEDS TO BE TESTED
       //SYNTAX FOR EMPTY SET MAY NOT BE RIGHT
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
       String resultString =  result.getString("crn");
       if(resultString == "Empty set."){
           courseExists = true;
       }      
       myCon.close();
       }
       catch (Exception sqlEx){
       System.err.println(sqlEx);
       }
       return courseExists;
    }
    public boolean studentLogin(String email, String password){
        if(studentList.size() == 0){
            return false;
        }
        
        Student student = new Student();
        ListIterator<Student> iterator = studentList.listIterator();
        boolean accountExists = false;
        while(iterator.hasNext()){
            System.out.println(iterator.next().email);
            student = iterator.next();
            if(student.email.equals(email) && student.password.equals(password)){
                String m = iterator.next().email;
                return true;
            }
//            student = iterator.next();
//            System.out.print("Student info" + student.email + " " + student.password);
//            if(student.email.equals(email) && student.password.equals(password)){
//                String m = iterator.next().email;
//                return true;
//            }
        }
        return accountExists;
    }
    public boolean studentExists(String studentEmail){
       if(studentNameList.size() == 0){
           return false;
       }
       ListIterator iterator = studentNameList.listIterator();
       boolean alreadyInstantiated = false;
       while(iterator.hasNext()){
           if(iterator.next().equals(studentEmail)){
               alreadyInstantiated = true;
           }
       }
        return alreadyInstantiated;
    }
    public Student getStudent(String email){
        Student student = new Student();
        ListIterator<Student> iterator = studentList.listIterator();
        while(iterator.hasNext()){
            if(student.email == email){
                student = iterator.next();
            }
        }
        return student;
    }
    LinkedList instantiatedCourses; //linked list of course CRN ints
    LinkedList studentNameList; //linked list of email Strings
    LinkedList <Student>studentList; //linked list of student objects
}
