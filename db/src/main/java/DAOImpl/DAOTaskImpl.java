package DAOImpl;

import DAO.DAOTask;
import model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DAOTaskImpl implements DAOTask {

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return task;
    }

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

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
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return subjectId;
    }
}
