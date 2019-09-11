package servicesImpl;

import DAO.DAOStudentSubject;
import model.Person;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.StudentSubjectService;

import java.util.Map;

/**
 * Class for working with DAOStudentSubject
 * @author Andrey Sherstyuk
 */
public class StudentSubjectServiceImpl implements StudentSubjectService {

    @Autowired
    DAOStudentSubject daoStudentSubject;

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @return map key - subject, value - teacher name
     */
    public Map<Subject, String> subjectList() {
        return daoStudentSubject.subjectList();
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param person - person who learning that subject.
     * @return - map key - subject, value - teacher name, that learning this student.
     */
    public Map<Subject, String> studentSubjectList(Person person) {
        return daoStudentSubject.studentSubjectList(person);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentLogin - student login
     * @param subjectId - subject id
     * @return - True if student learning subject,
     * false in other cases.
     */
    public boolean studentInfo(String studentLogin, int subjectId) {
        return daoStudentSubject.studentInfo(studentLogin, subjectId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentId - student id
     * @param subjectId - subject id
     */
    public void insertInfo(int subjectId, int studentId) {
        daoStudentSubject.insertNewInfo(studentId, subjectId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentId - student id
     * @param subjectId - subject id
     */
    public void deleteInfo(int subjectId, int studentId) {
        daoStudentSubject.deleteInfo(studentId, subjectId);
    }
}
