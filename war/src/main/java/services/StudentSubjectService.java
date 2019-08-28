package services;

import model.Person;
import model.Subject;

import java.util.Map;

/**
 * Interface for working with DAOStudentSubject
 * @author Andrey Sherstyuk
 */
public interface StudentSubjectService {

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @return map key - subject, value - teacher name
     */
    Map<Subject, String> subjectList();

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param person - person who learning that subject.
     * @return - map key - subject, value - teacher name, that learning this student.
     */
    Map<Subject, String> studentSubjectList(Person person);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentLogin - student login
     * @param subjectId - subject id
     * @return - True if student learning subject,
     * false in other cases.
     */
    boolean studentInfo(String studentLogin, int subjectId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentId - student id
     * @param subjectId - subject id
     */
    void insertInfo(int subjectId, int studentId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOStudentSubjectImpl}
     * @param studentId - student id
     * @param subjectId - subject id
     */
    void deleteInfo(int subjectId, int studentId);

}
