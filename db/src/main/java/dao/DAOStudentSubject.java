package dao;

import model.Person;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DAOStudentSubject {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Map<Person, List<Subject>> viewStudentSubject() {
        Map<Person, List<Subject>> map = new HashMap<>();
        List<Subject> list = new LinkedList<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.ID_PERSON, P.NAME, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN STUDENT T on TS.ID_PERSON = T.ID_PERSON " +
                    "JOIN PERSON P on T.ID_PERSON = P.ID_PERSON " +
                    "JOIN SUBJECT S on TS.ID_SUBJECT = S.ID_SUBJECT");

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                list.add(new Subject(resultSet.getString(3), resultSet.getString(4)));

                while (resultSet.next()) {
                    int pattern = resultSet.getInt(1);

                    if (id == pattern) {
                        list.add(new Subject(resultSet.getString(3), resultSet.getString(4)));
                    } else {
                        map.put(new Person(id, resultSet.getString(2)), list);
                        id = pattern;
                        list = new LinkedList<>();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    public static Map<Subject, List<Person>> viewSubjectStudent() {
        Map<Subject, List<Person>> map = new HashMap<>();
        List<Person> list = new LinkedList<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.ID_PERSON, P.NAME, S.ID_SUBJECT, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN STUDENT T on TS.ID_PERSON = T.ID_PERSON " +
                    "JOIN PERSON P on T.ID_PERSON = P.ID_PERSON " +
                    "JOIN SUBJECT S on TS.ID_SUBJECT = S.ID_SUBJECT");

            if (resultSet.next()) {
                int id = resultSet.getInt(3);
                list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));

                while (resultSet.next()) {
                    int pattern = resultSet.getInt(3);

                    if (id == pattern) {
                        list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
                    } else {
                        map.put(new Subject(id, resultSet.getString(4), resultSet.getString(5)), list);
                        id = pattern;
                        list = new LinkedList<>();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    public static boolean insertNewInfo(int studentId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO STUDENT_SUBJECT VALUES (?, ?)");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    public static boolean deleteInfo(int studentId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM STUDENT_SUBJECT WHERE ID_PERSON = ? AND ID_SUBJECT = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }
}
