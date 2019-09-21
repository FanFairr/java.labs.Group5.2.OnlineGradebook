package servicesImpl;

import DAO.DAOPerson;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.PersonService;

import java.util.List;

/**
 * Class for working with DAOPerson
 * @author Andrey Sherstyuk
 */
public class PersonServiceImpl implements PersonService {

    @Autowired
    DAOPerson daoPerson;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    public boolean validateLogin(Person person) {
        return daoPerson.validateLogin(person);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * with encryption password and setting status.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    public boolean validateRegistration(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setStatus("student");
        return daoPerson.validateRegistration(person);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param taskId - task id
     * @return List of persons who have mark on this task.
     */
    public List<Person> selectAllStudents(int taskId) {
        return daoPerson.selectAllStudents(taskId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param personId - teacher id
     * @param taskId - task id
     * @return True if person is a teacher in this subject,
     *  false in other cases.
     */
    public boolean personInfo(int personId, int taskId) {
        return daoPerson.personInfo(personId, taskId);
    }

    /**
     * Method for selecting all students.
     * @return list of students.
     */
    public List<Person> viewAllStudent() {
        return daoPerson.viewAllStudents();
    }

    /**
     * Method for selecting all teachers.
     * @return list of teachers.
     */
    public List<Person> viewAllTeachers() {
        return daoPerson.viewAllTeachers();
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOPersonImpl}
     * @param studentId - student id
     */
    public void updateStudent(int studentId) {
        daoPerson.updateStudent(studentId);
    }

}
