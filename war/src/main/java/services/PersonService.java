package services;

import dao.DAOPerson;
import model.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}
