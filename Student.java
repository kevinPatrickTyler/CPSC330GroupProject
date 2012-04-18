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
 * Student.java.  File to hold student objects 
 * @author kevintyler
 */
public class Student extends courseAndStudentCatalog{
   public Student(){
   }
   public Student(String emailAddy, String pw){
       //inserts Student object into SQL DB and instantiates object
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
        String sql = "INSERT INTO student VALUES ('" + emailAddy+ "','" + pw + "')"; 
        myStmt.executeUpdate(sql);
        //iterate through result sets
        //since we're searching by CRN, there should only be one
        
        //close connection
        myCon.close();
        }
        catch (Exception sqlEx){
        System.err.println(sqlEx);
        }
       email = emailAddy;
       password = pw;
   }
   
   public void setLastName(String lastname){
       lastName = lastname;
   }
   public void setFirstName(String firstname){
       firstName = firstname;
   }
  
   int bannerId;
   public String lastName, firstName, academicYear, email, password;
}
