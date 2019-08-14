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

public class DAOStudentSubject {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static Map<Person, List<Subject>> viewStudentSubject() {
        Map<Person, List<Subject>> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.NAME, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN STUDENT T on TS.ID_PERSON = T.ID_PERSON " +
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

    public static Map<Subject, List<Person>> viewSubjectStudent() {
        Map<Subject, List<Person>> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.NAME, S.SUBJECT_NAME, S.CONTENT FROM STUDENT_SUBJECT TS " +
                    "JOIN STUDENT T on TS.ID_PERSON = T.ID_PERSON " +
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
    public static Map<Subject, String> subjectList(Person person) {
        Map<Subject, String> map = new HashMap<>();

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("SELECT s.ID_SUBJECT, s.SUBJECT_NAME, s.CONTENT, p.NAME" +
                    " from subject s" +
                    " join teacher_subject ts on s.ID_SUBJECT = ts.ID_SUBJECT" +
                    " join teacher t on ts.ID_PERSON = t.ID_PERSON" +
                    " join person p on t.ID_PERSON = p.ID_PERSON");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                map.put(new Subject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)),
                        resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    public static Map<Subject, String> studentSubjectList(Person person) {
        Map<Subject, String> map = new HashMap<>();

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("SELECT ID_SUBJECT, SUBJECT_NAME, CONTENT, p.NAME" +
                    " from person stud" +
                    " join STUDENT_SUBJECT ss on ss.ID_PERSON = stud.ID_PERSON" +
                    " join subject s on ss.ID_SUBJECT = s.ID_SUBJECT" +
                    " join teacher_subject ts on s.ID_SUBJECT = ts.ID_SUBJECT" +
                    " join teacher t on ts.ID_PERSON = t.ID_PERSON" +
                    " join person p on t.ID_PERSON = p.ID_PERSON" +
                    " where stud.LOGIN = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                map.put(new Subject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)),
                        resultSet.getString(4));
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
