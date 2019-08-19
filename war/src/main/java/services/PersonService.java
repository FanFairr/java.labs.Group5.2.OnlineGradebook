package services;

import dao.DAOPerson;
import model.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class PersonService {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static boolean validateLogin(Person person) {
        return DAOPerson.validateLogin(person);
    }

    public static boolean validateRegistration(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setStatus("student");
        return DAOPerson.validateRegistration(person);
    }

    public List<Person> selectAllStudents(int taskId) {
        return DAOPerson.selectAllStudents(taskId);
    }

    public boolean personInfo(int personId, int taskId) {
        return DAOPerson.personInfo(personId, taskId);
    }
}
