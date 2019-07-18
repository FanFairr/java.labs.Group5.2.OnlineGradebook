package dao;

import model.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOLogin {

    private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:Druce";
    private final static String LOGIN = "system";
    private final static String PASSWORD = "191195";

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static Connection connection;
    private static boolean isConnected = false;
    private static ResultSet resultSet;

    private static void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            isConnected = connection == null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> viewAllInformation() {
        List<Person> personList = new LinkedList<>();
        try {
            connect();
            preparedStatement = connection.prepareStatement("select * from login order by id");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public static void viewInformation(String pattern) {

    }

    public static boolean validateLogin(Person person) {
        try {
            connect();
            preparedStatement = connection.prepareStatement("select * from login where login = ? and PASSWORD = ?");
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getPassword());
            resultSet = preparedStatement.executeQuery();
            boolean b = resultSet.next();
            disconnect();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateRegistration(Person person) {
        try {
            connect();
            preparedStatement = connection.prepareStatement("select * from login where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                disconnect();
                return false;
            } else {
                preparedStatement = connection.prepareStatement("insert into login values(login_seq.nextval, ?, ?, ?)");
                preparedStatement.setString(1, person.getLogin());
                preparedStatement.setString(2, person.getPassword());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.execute();
                disconnect();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    private static void disconnect() {
        try {
            connection.close();
            isConnected = connection == null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
