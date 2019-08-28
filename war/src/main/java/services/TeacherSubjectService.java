package services;

import model.Subject;

import java.util.Set;

/**
 * Interface for working with DAOTeacherSubject
 * @author Andrey Sherstyuk
 */
public interface TeacherSubjectService {

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTeacherSubjectImpl}
     * @param teacherId - teacher id.
     * @return - subject set that this person teaches.
     */
    Set<Subject> teacherSubjectSet(int teacherId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTeacherSubjectImpl}
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    boolean insertNewInfo(int teacherId, int subjectId);

}
