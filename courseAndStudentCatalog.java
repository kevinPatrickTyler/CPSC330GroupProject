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
    public boolean studentExists(Student student){
        ListIterator iterator = studentList.listIterator();
       boolean alreadyInstantiated = false;
       while(iterator.hasNext()){
           if(iterator.next().equals(student)){
               alreadyInstantiated = true;
           }
       }
        return alreadyInstantiated;
    }
    LinkedList instantiatedCourses; //linked list of course CRN ints
    LinkedList studentList; //linked list of student objects
}
