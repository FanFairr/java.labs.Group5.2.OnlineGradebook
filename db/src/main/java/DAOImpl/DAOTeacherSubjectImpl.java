package DAOImpl;

import DAO.DAOTeacherSubject;
import model.Person;
import model.Subject;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Class for working with the table "TeacherSubject" on database.
 * @author Anrey Sherstyuk
 */
public class DAOTeacherSubjectImpl implements DAOTeacherSubject {
    private static final Logger DAOTSLOGGER = Logger.getLogger(DAOTeacherSubjectImpl.class);

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Method for selecting information - person -> subject list that this person teaches.
     * @return - Map key - person, value - subjects that this person teaches.
     */
    public Map<Person, List<Subject>> viewTeacherSubject() {
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
            DAOTSLOGGER.error("Error when use method viewTeacherSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for selecting information - subject -> person list that teaches this subject.
     * @return - Map key - person, value - subjects that this person teaches.
     */
    public Map<Subject, List<Person>> viewSubjectTeacher() {
        Map<Subject, List<Person>> map = new HashMap<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT P.ID_PERSON, P.NAME, S.ID_SUBJECT, S.SUBJECT_NAME, S.CONTENT FROM TEACHER_SUBJECT TS " +
                    "JOIN TEACHER T on TS.ID_PERSON = T.ID_PERSON " +
                    "JOIN PERSON P on T.ID_PERSON = P.ID_PERSON " +
                    "JOIN SUBJECT S on TS.ID_SUBJECT = S.ID_SUBJECT");

            DAOHelper.subjectPerson(map, resultSet);
        } catch (SQLException e) {
            DAOTSLOGGER.error("Error when use method viewSubjectTeacher. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return map;
    }

    /**
     * Method for selecting subject set for teacher id.
     * @param teacherId - teacher id.
     * @return - subject set that this person teaches.
     */
    public Set<Subject> teacherSubjectSet(int teacherId) {
        Set<Subject> set = new HashSet<>();

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select s.id_subject, s.SUBJECT_NAME, s.CONTENT " +
                    "from teacher_subject ts " +
                    "join teacher t on t.ID_PERSON = ts.ID_PERSON " +
                    "join subject s on ts.ID_SUBJECT = s.ID_SUBJECT " +
                    "where t.ID_PERSON = ?");
            preparedStatement.setInt(1, teacherId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                set.add(new Subject(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException e) {
            DAOTSLOGGER.error("Error when use method teacherSubjectSet. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return set;
    }

    /**
     * Method for inserting information for table "TeacherSubject".
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    public boolean insertNewInfo(int teacherId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO TEACHER_SUBJECT VALUES (?, ?)");
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOTSLOGGER.error("Error when use method insertNewInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    /**
     * Method for deleting information on table "TeacherSubject".
     * @param teacherId - teacher id.
     * @param subjectId - subject id.
     * @return - The success of the operation.
     */
    public boolean deleteInfo(int teacherId, int subjectId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM TEACHER_SUBJECT WHERE ID_PERSON = ? AND ID_SUBJECT = ?");
            preparedStatement.setInt(1, teacherId);
            preparedStatement.setInt(2, subjectId);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOTSLOGGER.error("Error when use method deleteInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }
}
