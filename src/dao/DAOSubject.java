package dao;

import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DAOSubject {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Subject> viewAllSubject() {
        List<Subject> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SUBJECT");

            while (resultSet.next()) {
                list.add(new Subject(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    public static boolean insertNewSubject(Subject subject) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO SUBJECT VALUES (SUBJECT_SEQ.nextval, ?, ?)");
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setString(2, subject.getContent());
            b = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    public static boolean deleteSubject(int id) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM SUBJECT WHERE ID_SUBJECT = ?");
            preparedStatement.setInt(1, id);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }
}
