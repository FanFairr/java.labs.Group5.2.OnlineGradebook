package DAO;

import model.Person;
import model.Subject;

import java.util.List;
import java.util.Map;

/**
 * Interface for working with the table "StudentSubject" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOStudentSubject {

    /**
     * Method for selecting information - student -> subject that he learning.
     * @return map key - student, value - list of subject that student learning.
     */
    Map<Person, List<Subject>> viewStudentSubject();

    /**
     * Method for selecting information - subject -> students that learning this subject.
     * @return map key - subject, value - list of students that learning subject.
     */
    Map<Subject, List<Person>> viewSubjectStudent();

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @return map key - subject, value - teacher name
     */
    Map<Subject, String> subjectList();

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @param person - person who learning that subject.
     * @return - map key - subject, value - teacher name, that learning this student.
     */
    Map<Subject, String> studentSubjectList(Person person);

    /**
     * Method for inserting new information to table.
     * @param studentId - student id
     * @param subjectId - subject id
     * @return - The success of the operation.
     */
    boolean insertNewInfo(int studentId, int subjectId);

    /**
     * Method for deleting information of table.
     * @param studentId - student id
     * @param subjectId - subject id
     * @return - The success of the operation.
     */
    boolean deleteInfo(int studentId, int subjectId);

    /**
     * Method for checking whether a given person
     * is a student in a subject.
     * @param studentLogin - student login
     * @param subjectId - subject id
     * @return - True if student learning subject,
     * false in other cases.
     */
    boolean studentInfo(String studentLogin, int subjectId);

}
