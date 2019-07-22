package dao;

import model.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOPerson {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Person> viewAllInformation() {
        List<Person> personList = new LinkedList<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select * from login order by id");
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
            preparedStatement = DAOConnection.connection.prepareStatement("select * from login where login = ? and PASSWORD = ?");
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getPassword());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
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
            preparedStatement = DAOConnection.connection.prepareStatement("select * from login where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
            if (!b) {
                preparedStatement = DAOConnection.connection.prepareStatement("insert into login values(login_seq.nextval, ?, ?, ?)");
                preparedStatement.setString(1, person.getLogin());
                preparedStatement.setString(2, person.getPassword());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return false;
    }


}
