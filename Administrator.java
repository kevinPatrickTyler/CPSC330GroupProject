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
        if(studentExists(student.email) == false){
            return false;
        }
        else{
            studentNameList.remove(student);
        }
        return true;
    }
}
