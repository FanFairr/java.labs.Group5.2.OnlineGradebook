package DAOImpl;

import DAO.DAOTask;
import model.Task;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for working with the table "Task" on database.
 * @author Anrey Sherstyuk
 */
public class DAOTaskImpl implements DAOTask {
    private static final Logger DAOTLOGGER = Logger.getLogger(DAOTaskImpl.class);

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Method for selecting all tasks of subject id.
     * @param subjectId - subject id
     * @return - List of tasks.
     */
    public List<Task> viewAllTask(int subjectId) {
        List<Task> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement(
                    "SELECT (SELECT SUBJECT_NAME FROM SUBJECT WHERE SUBJECT.ID_SUBJECT = TASK.ID_SUBJECT), NAME, CONTENT, MAX_MARK, TASK_ID " +
                            "FROM TASK WHERE ID_SUBJECT = ?");
            preparedStatement.setInt(1, subjectId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(new Task(Integer.parseInt(resultSet.getString(5)),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)));
            }
        } catch (SQLException e) {
            DAOTLOGGER.error("Error when use method viewAllTask. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    /**
     * Method for selecting task of task id.
     * @param taskId - task id.
     * @return - Task.
     */
    public Task viewTask(int taskId) {
        Task task = new Task();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select name, content, MAX_MARK from task where TASK_ID = ?");
            preparedStatement.setString(1, String.valueOf(taskId));

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                task.setId(taskId);
                task.setName(resultSet.getString(1));
                task.setContent(resultSet.getString(2));
                task.setMax_mark(resultSet.getDouble(3));
            }
        } catch (SQLException e) {
            DAOTLOGGER.error("Error when use method viewTask. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return task;
    }

    /**
     * Method for inserting information to table "Task".
     * @param task - task for inserting.
     * @return - The success of the operation.
     */
    public boolean insertNewTask(Task task) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO TASK VALUES (" +
                    "TASK_SEQ.nextval, (SELECT ID_SUBJECT FROM SUBJECT WHERE SUBJECT_NAME = ?), ?, ?, ?)");
            preparedStatement.setString(1, task.getSubjectName());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getContent());
            preparedStatement.setDouble(4, task.getMax_mark());
            b = preparedStatement.execute();
        } catch (SQLException e) {
            DAOTLOGGER.error("Error when use method insertNewTask. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    /**
     * Method for selecting subject id of task id.
     * @param taskId - task id
     * @return - subject id.
     */
    public int subjectId(int taskId) {
        int subjectId = 0;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select ID_SUBJECT from task where TASK_ID = ?");
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            subjectId = resultSet.getInt(1);
        } catch (SQLException e) {
            DAOTLOGGER.error("Error when use method subjectId. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return subjectId;
    }
}
