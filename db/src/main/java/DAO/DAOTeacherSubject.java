package DAO;

import model.Person;
import model.Subject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for working with the table "TeacherSubject" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOTeacherSubject {

    /**
     * Method for selecting information - person -> subject list that this person teaches.
     * @return - Map key - person, value - subjects that this person teaches.
     */
    Map<Person, List<Subject>> viewTeacherSubject();

    /**
     * Method for selecting information - subject -> person list that teaches this subject.
     * @return - Map key - person, value - subjects that this person teaches.
     */
    Map<Subject, List<Person>> viewSubjectTeacher();

    /**
     * Method for selecting subject set for teacher id.
     * @param teacherId - teacher id.
     * @return - subject set that this person teaches.
     */
    Set<Subject> teacherSubjectSet(int teacherId);

    /**
     * Method for inserting information for table "TeacherSubject".
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    boolean insertNewInfo(int teacherId, int subjectId);

    /**
     * Method for deleting information on table "TeacherSubject".
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    boolean deleteInfo(int teacherId, int subjectId);

}
