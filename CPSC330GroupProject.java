/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject;
import java.sql.*;
/**
 *
 * @author Kevin Tyler
 */
public class CPSC330GroupProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Connection myCon;
    Statement myStmt;
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      // Connect to an instance of mysql with the follow details:

      myCon = DriverManager.getConnection(
              "jdbc:mysql://localhost/carBook",
              "root","");
      myStmt = myCon.createStatement();
      ResultSet result = myStmt.executeQuery(
         "SELECT * FROM cars");
      while (result.next()){
        System.out.println(result.getString("make"));
      }
      
      //insert into database
      Statement insertStatement = myCon.createStatement();
      insertStatement.executeUpdate("INSERT INTO cars" + 
              "VALUES('123456789AII5699W','1994','Toyota', 'Camry', '19000', '1000', 'Blue', 'Automatic')");
      
      
      myCon.close();
    }
    catch (Exception sqlEx){
      System.err.println(sqlEx);
    }
    }
}
