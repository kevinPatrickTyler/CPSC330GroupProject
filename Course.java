package cpsc330groupproject.CPSC330GroupProject;
import java.sql.*;
import java.util.ListIterator;

/**
 *
 * @author kevintyler
 * Course.java
 * Course object to hold course data
 */
public class Course extends courseAndStudentCatalog{
    //default constructor
    public Course(int courseNum){
        //instantiates course object from extracted SQL record
        //  corresponds with CRN
        Connection myCon;
        Statement myStmt;
        //open connection and instantiate statement
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
            "SELECT * FROM umw2010 WHERE crn = " + courseNum);
        //iterate through result sets
        //since we're searching by CRN, there should only be one
        while (result.next()){
            //set CRN, course, section etc variables from database
            CRN = Integer.parseInt(result.getString("crn"));            
            course = result.getString("course");            
            section = Integer.parseInt(result.getString("section"));
            title = result.getString("title");   
            
            String poiString = result.getString("poi"); 
            if(poiString.length() > 0){
                poi = poiString.charAt(0);
            }
            else{
                poi = 'N';
            }
            
            String coString = result.getString("co");
            if(coString.length() > 0){
                co = coString.charAt(0);
            }
            else{
                co = 'N';
            }
            
            String prString = result.getString("prereqs"); 
            if(prString.length() > 0){
                prereqs = prString.charAt(0); 
            }
            else{
                prereqs = 'N';
            }
            atc = result.getString("atc");
            credits = Integer.parseInt(result.getString("credits"));
            time = result.getString("time");
            days = result.getString("days");
            building = result.getString("building");
            room = result.getString("room");
            instructor = result.getString("instructor");
            requirements = result.getString("requirements");
        }
        //close connection
        myCon.close();
        }
        catch (Exception sqlEx){
        System.err.println(sqlEx);
        }
        //instantiate variables for capacity and number of students enrolled
        numCurrentlyEnrolled = 0;
        capacity = 18;
        //add to list of instantiated courses
        instantiatedCourses.add(CRN);
        }
    
    public String toString(){
        //return String of course information
        return this.CRN + " " + this.course + " " + this.section + " " 
                + this.title + " " + this.poi + " " + this.co + " "
                + this.prereqs + " " + this.atc + " " + this.credits + " "
                + this.time + " " + this.days + " " + this.building + " "
                + this.room + " " + this.instructor + this.requirements;
    }
    
    private int CRN;
    private String course;
    private int section;
    private String title;
    private char poi;
    private char co;
    private char prereqs;
    private String atc;
    private int credits;
    private String time;
    private String days;
    private String building;
    private String room;
    private String instructor;
    private String requirements;
    public int capacity;
    public int numCurrentlyEnrolled;
}
