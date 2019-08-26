package DAOImpl;

import DAO.DAOSubject;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DAOSubjectImpl implements DAOSubject {

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return subject;
    }

    public boolean insertNewSubject(Subject subject) {
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

    public boolean deleteSubject(int id) {
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
