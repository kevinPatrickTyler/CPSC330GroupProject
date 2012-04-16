/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cpsc330groupproject.CPSC330GroupProject;

/**
 *
 * @author kevintyler
 */
public class Administrator extends courseAndStudentCatalog{
    public Administrator(){
        
    }
    public boolean deleteStudent(Student student){
        if(studentExists(student) == false){
            return false;
        }
        else{
            studentList.remove(student);
        }
        return true;
    }
}
