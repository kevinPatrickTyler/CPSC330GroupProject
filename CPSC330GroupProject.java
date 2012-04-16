
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
       
    }
}