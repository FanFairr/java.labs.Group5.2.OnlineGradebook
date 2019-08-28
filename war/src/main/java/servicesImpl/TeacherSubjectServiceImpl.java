package servicesImpl;

import DAOImpl.DAOTeacherSubjectImpl;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.TeacherSubjectService;

import java.util.Set;

/**
 * Class for working with DAOTeacherSubject
 * @author Andrey Sherstyuk
 */
public class TeacherSubjectServiceImpl implements TeacherSubjectService {

    @Autowired
    DAOTeacherSubjectImpl daoTeacherSubject;

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTeacherSubjectImpl}
     * @param teacherId - teacher id.
     * @return - subject set that this person teaches.
     */
    public Set<Subject> teacherSubjectSet(int teacherId) {
        return daoTeacherSubject.teacherSubjectSet(teacherId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTeacherSubjectImpl}
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    public boolean insertNewInfo(int teacherId, int subjectId) {
        return daoTeacherSubject.insertNewInfo(teacherId, subjectId);
    }
}
