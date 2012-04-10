
package cpsc330groupproject.CPSC330GroupProject;
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
        //create courses
        Course testCourse = new Course(11941);
        Course testCourse2 = new Course(10366);
        //create student variable
        Student chrisLutz = new Student("clutz@umw.edu", "healthy");
        //add courses to student variable
        chrisLutz.addClass(testCourse);
        chrisLutz.addClass(testCourse2);
        //print schedule
        chrisLutz.printSchedule();
        //drop class
        chrisLutz.dropClass(testCourse);
        System.out.println("");
        //print schedule again to verify change
        chrisLutz.printSchedule();
        /*
    Connection myCon;
    Statement myStmt;
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      // Connect to an instance of mysql with the follow details

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
      insertStatement.executeUpdate("INSERT INTO `carOwnership`(`id`, `vin`) VALUES (1, '15787844787786475')");
      
      
      myCon.close();
    }
    catch (Exception sqlEx){
      System.err.println(sqlEx);
    }
    }
    */
    }
}