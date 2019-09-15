package services;

import model.Person;

import java.util.List;

/**
 * Interface for working with DAOPerson
 * @author Andrey Sherstyuk
 */
public interface PersonService {

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    boolean validateLogin(Person person);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * with encryption password and setting status.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    boolean validateRegistration(Person person);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param taskId - task id
     * @return List of persons who have mark on this task.
     */
    List<Person> selectAllStudents(int taskId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param personId - teacher id
     * @param taskId - task id
     * @return True if person is a teacher in this subject,
     *  false in other cases.
     */
    boolean personInfo(int personId, int taskId);

    /**
     * Method for selecting all students.
     * @return list of students.
     */
    List<Person> viewAllStudent();

    /**
     * Method for selecting all teachers.
     * @return list of teachers.
     */
    List<Person> viewAllTeachers();

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param studentId - student id
     */
    void updateStudent(int studentId);

    /**
     * Method for testing tables.
     */
    void testTables();
}
