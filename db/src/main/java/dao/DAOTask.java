package dao;

import model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DAOTask {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Task> viewAllTask() {
        List<Task> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT TASK_SEQ.nextval, (SELECT NAME FROM SUBJECT WHERE SUBJECT.ID_SUBJECT = TASK.ID_SUBJECT), NAME, CONTENT, MAX_MARK FROM TASK");

            while (resultSet.next()) {
                list.add(new Task(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    public static boolean insertNewTask(Task task) {
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
}
