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
 * Faculty.java
 * class to maintain a faculty object
 * inherits Student.java
 */
public class Faculty extends Student {
    //default constructor with no arguements
    public Faculty(){
    }
    //default constructor
    public Faculty(String lastname, String firstname){
        lastName = lastname;
        firstName = firstname;
    }
    String lastName, firstName, department;
}
