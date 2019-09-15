package DAOImpl;

import DAO.DAOSubject;
import model.Subject;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for working with the table "Subject" on database.
 * @author Anrey Sherstyuk
 */
public class DAOSubjectImpl implements DAOSubject {
    private static final Logger DAOSLOGGER = Logger.getLogger(DAOSubjectImpl.class);

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Method for selecting all subjects.
     * @return - List of subjects.
     */
    public List<Subject> viewAllSubject() {
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
            DAOSLOGGER.error("Error when use method viewAllSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    /**
     * Method for selecting subject of subject id.
     * @param id - subject id.
     * @return - Subject.
     */
    public Subject viewSubject(int id) {
        Subject subject = null;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select name, content from subject where ID_SUBJECT = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            subject = new Subject(resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException e) {
            DAOSLOGGER.error("Error when use method viewSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return subject;
    }

    /**
     * Method for inserting new information to table "Subject"
     * @param subject - subject that need inserting.
     * @return - The success of the operation.
     */
    public boolean insertNewSubject(Subject subject) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO SUBJECT VALUES (SUBJECT_SEQ.nextval, ?, ?)");
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setString(2, subject.getContent());
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOSLOGGER.error("Error when use method insertNewSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    /**
     * Method for deleting information on table "Subject"
     * @param id - subject id
     * @return - The success of the operation.
     */
    public boolean deleteSubject(int id) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM SUBJECT WHERE ID_SUBJECT = ?");
            preparedStatement.setInt(1, id);
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOSLOGGER.error("Error when use method deleteSubject. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }
}
