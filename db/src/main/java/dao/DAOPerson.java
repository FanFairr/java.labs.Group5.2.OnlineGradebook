package dao;

import model.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOPerson {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Person> viewAllInformation() {
        List<Person> personList = new LinkedList<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select * from PERSON order by id");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return personList;
    }

    public static void viewInformation(String pattern) {

    }

    public static boolean validateLogin(Person person) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select NAME, STATUS, PASSWORD from PERSON where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
            if (b) {
                b = bCryptPasswordEncoder.matches(person.getPassword(), resultSet.getString(3));
                if (b) {
                    person.setName(resultSet.getString(1));
                    person.setStatus(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    public static boolean validateRegistration(Person person) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select * from PERSON where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
            if (!b) {
                preparedStatement = DAOConnection.connection.prepareStatement("insert into PERSON values(login_seq.nextval, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(2, person.getLogin());
                preparedStatement.setString(3, person.getPassword());
                preparedStatement.setString(4, person.getEmail());
                preparedStatement.setString(5, person.getStatus());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }


}
