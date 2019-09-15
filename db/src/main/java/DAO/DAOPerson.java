package DAO;

import model.Person;

import java.util.List;

/**
 * Interface for working with the table "Person" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOPerson {

    /**
     * Method for returning all person from the database.
     * @return List of person
     */
    List<Person> viewAllInformation();

    /**
     * Method for checking the entered data in the "Authorization" window.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    boolean validateLogin(Person person);

    /**
     * Method for checking the entered data in the "Registration" window.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    boolean validateRegistration(Person person);

    /**
     * The method of selecting a list of persons by task id.
     * @param taskId - task id
     * @return List of persons who have mark on this task.
     */
    List<Person> selectAllStudents(int taskId);

    /**
     * A method for checking whether a given person
     * is a teacher in a subject.
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
    List<Person> viewAllStudents();

    /**
     * Update information of person(student -> teacher
     * with deleting all information about student)
     * @param studentId - student id
     */
    void updateStudent(int studentId);

    /**
     * Method for selecting all teachers.
     * @return list of teachers.
     */
    List<Person> viewAllTeachers();

    /**
     * Method for testing tables.
     */
    void testTables();
}
