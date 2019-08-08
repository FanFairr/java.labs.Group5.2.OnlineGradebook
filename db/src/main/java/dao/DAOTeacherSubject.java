package dao;

import model.Person;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOTeacherSubject {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Map<Person, List<Subject>> viewTeacherSubject() {
        Map<Person, List<Subject>> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.NAME, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN TEACHER T on TS.ID_PERSON = T.ID_PERSON " +
                    "JOIN PERSON P on T.ID_PERSON = P.ID_PERSON " +
                    "JOIN SUBJECT S on TS.ID_SUBJECT = S.ID_SUBJECT");

            DAOHelper.personSubject(map, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    public static Map<Subject, List<Person>> viewSubjectTeacher() {
        Map<Subject, List<Person>> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.ID_PERSON, P.NAME, S.ID_SUBJECT, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN TEACHER T on TS.ID_PERSON = T.ID_PERSON " +
                    "JOIN PERSON P on T.ID_PERSON = P.ID_PERSON " +
                    "JOIN SUBJECT S on TS.ID_SUBJECT = S.ID_SUBJECT");

            DAOHelper.subjectPerson(map, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    public static boolean insertNewInfo(int teacherId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO TEACHER_SUBJECT VALUES (?, ?)");
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    public static boolean deleteInfo(int teacherId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM TEACHER_SUBJECT WHERE ID_PERSON = ? AND ID_SUBJECT = ?");
            preparedStatement.setInt(1, teacherId);
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
