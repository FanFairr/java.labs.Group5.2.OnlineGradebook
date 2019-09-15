package DAOImpl;

import model.Person;
import model.Subject;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class for carrying out methods that are used in many places.
 * @author Andrey Sherstyuk
 */
class DAOHelper {
    private static final Logger logger = Logger.getLogger(DAOHelper.class);

    /**
     * Method for filling the map(key - person, value - subject list) from resultSet.
     * @param map - map that need filling.
     * @param resultSet - resultSet where to get information from.
     */
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
            logger.error("Error when use method personSubject. Message: " + e.getMessage());
        }
    }

    /**
     * Method for filling the map(key - subject, value - person list) from resultSet.
     * @param map - map that need filling.
     * @param resultSet - resultSet where to get information from.
     */
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
            logger.error("Error when use method subjectPerson. Message: " + e.getMessage());
        }
    }
}
