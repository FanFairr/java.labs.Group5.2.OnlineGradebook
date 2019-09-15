package DAOImpl;

import DAO.DAOStudentSubject;
import model.Person;
import model.Subject;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for working with the table "StudentSubject" on database.
 * @author Anrey Sherstyuk
 */
public class DAOStudentSubjectImpl implements DAOStudentSubject {
    private static final Logger DAOSSLOGGER = Logger.getLogger(DAOStudentSubjectImpl.class);

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Method for selecting information - student -> subject that he learning.
     * @return map key - student, value - list of subject that student learning.
     */
    public Map<Person, List<Subject>> viewStudentSubject() {
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
            DAOSSLOGGER.error("Error when use method ViewStudentSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for selecting information - subject -> students that learning this subject.
     * @return map key - subject, value - list of students that learning subject.
     */
    public Map<Subject, List<Person>> viewSubjectStudent() {
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
            DAOSSLOGGER.error("Error when use method viewSubjectStudent. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @return map key - subject, value - teacher name
     */
    public Map<Subject, String> subjectList() {
        Map<Subject, String> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT s.ID_SUBJECT, s.SUBJECT_NAME, s.CONTENT, p.NAME" +
                    " from subject s" +
                    " join teacher_subject ts on s.ID_SUBJECT = ts.ID_SUBJECT" +
                    " join teacher t on ts.ID_PERSON = t.ID_PERSON" +
                    " join person p on t.ID_PERSON = p.ID_PERSON");

            while (resultSet.next()) {
                map.put(new Subject(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)),
                        resultSet.getString(4));
            }
        } catch (SQLException e) {
            DAOSSLOGGER.error("Error when use method subjectList. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for selecting information - subject -> teacher name that teaching this subject.
     * @param person - person who learning that subject.
     * @return - map key - subject, value - teacher name, that learning this student.
     */
    public Map<Subject, String> studentSubjectList(Person person) {
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
            DAOSSLOGGER.error("Error when use method studentSubjectList. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for inserting new information to table.
     * @param studentId - student id
     * @param subjectId - subject id
     * @return - The success of the operation.
     */
    public boolean insertNewInfo(int studentId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO STUDENT_SUBJECT VALUES (?, ?)");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOSSLOGGER.error("Error when use method insertNewInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    /**
     * Method for deleting information of table.
     * @param studentId - student id
     * @param subjectId - subject id
     * @return - The success of the operation.
     */
    public boolean deleteInfo(int studentId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM STUDENT_SUBJECT WHERE ID_PERSON = ? AND ID_SUBJECT = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOSSLOGGER.error("Error when use method deleteInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    /**
     * Method for checking whether a given person
     * is a student in a subject.
     * @param studentLogin - student login
     * @param subjectId - subject id
     * @return - True if student learning subject,
     * false in other cases.
     */
    public boolean studentInfo(String studentLogin, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select 1 from PERSON\n" +
                    "join student on PERSON.ID_PERSON = STUDENT.ID_PERSON\n" +
                    "join STUDENT_SUBJECT on STUDENT.ID_PERSON = STUDENT_SUBJECT.ID_PERSON\n" +
                    "where login = ? and ID_SUBJECT = ?");
            preparedStatement.setString(1, studentLogin);
            preparedStatement.setInt(2, subjectId);
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
        } catch (SQLException e) {
            DAOSSLOGGER.error("Error when use method studentInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }
}
