package DAOImpl;

import model.Person;
import model.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class DAOHelper {
    static void personSubject(Map<Person, List<Subject>> map, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Person person = new Person(resultSet.getString(1));
                Subject subject = new Subject(resultSet.getString(2), resultSet.getString(3));
                if (map.containsKey(person)) {
                    map.get(person).add(subject);
                } else {
                    map.put(person, new LinkedList<>(Collections.singletonList(subject)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void subjectPerson(Map<Subject, List<Person>> map, ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Subject subject = new Subject(resultSet.getString(2), resultSet.getString(3));
                Person person = new Person(resultSet.getString(1));
                if (map.containsKey(subject)) {
                    map.get(subject).add(person);
                } else {
                    map.put(subject, new LinkedList<>(Collections.singletonList(person)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
