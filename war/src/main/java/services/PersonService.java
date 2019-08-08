package services;

import dao.DAOPerson;
import model.Person;

public class PersonService {

    public boolean validateLogin(Person person) {
        return DAOPerson.validateLogin(person);
    }
}
