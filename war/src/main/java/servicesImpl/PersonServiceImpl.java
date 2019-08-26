package servicesImpl;

import DAO.DAOPerson;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import services.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Autowired
    DAOPerson daoPerson;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean validateLogin(Person person) {
        return daoPerson.validateLogin(person);
    }

    public boolean validateRegistration(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setStatus("student");
        return daoPerson.validateRegistration(person);
    }

    public List<Person> selectAllStudents(int taskId) {
        return daoPerson.selectAllStudents(taskId);
    }

    public boolean personInfo(int personId, int taskId) {
        return daoPerson.personInfo(personId, taskId);
    }

    public List<Person> viewAllStudent() {
        return daoPerson.viewAllStudents();
    }

    public List<Person> viewAllTeachers() {
        return daoPerson.viewAllTeachers();
    }

    public void updateStudent(int studentId) {
        daoPerson.updateStudent(studentId);
    }
}
