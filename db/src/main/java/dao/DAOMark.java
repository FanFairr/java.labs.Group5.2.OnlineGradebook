package dao;

import model.Mark;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOMark {

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Mark> viewAllMark() {
        List<Mark> marks = new LinkedList<>();

        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("SELECT T.NAME, P.NAME, P2.NAME, MARK_SCORE FROM MARK M " +
                    "JOIN TASK T on T.TASK_ID = M.TASK_ID " +
                    "JOIN STUDENT S on M.ID_STUDENT = S.ID_PERSON " +
                    "JOIN TEACHER T2 on M.ID_TEACHER = T2.ID_PERSON " +
                    "JOIN PERSON P on S.ID_PERSON = P.ID_PERSON " +
                    "JOIN PERSON P2 on T2.ID_PERSON = P2.ID_PERSON");
            while (resultSet.next()) {
                marks.add(new Mark(
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

        return marks;
    }

    public static List<Mark> viewMarks(int id) {
        List<Mark> markList = new LinkedList<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select task.name, stud.name, teach.name, MARK_SCORE" +
                    " from mark" +
                    " join STUDENT on MARK.ID_STUDENT = STUDENT.ID_PERSON" +
                    " join teacher on MARK.ID_TEACHER = TEACHER.ID_PERSON" +
                    " join PERSON stud on STUDENT.ID_PERSON = stud.ID_PERSON" +
                    " join PERSON teach on TEACHER.ID_PERSON = teach.ID_PERSON" +
                    " join task on MARK.TASK_ID = TASK.TASK_ID" +
                    " where task.ID_SUBJECT = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                markList.add(new Mark(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return markList;
    }

    public static boolean insertNewMark(int taskId, int studentId, int teacherId, double mark) {
        boolean bool = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("INSERT INTO MARK VALUES (MARK_SEQ.nextval, ?, ?, ?, ?)");
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, teacherId);
            preparedStatement.setDouble(4, mark);
            bool = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return bool;
    }

    public static boolean deleteMark(int markId) {
        boolean bool = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("DELETE FROM MARK WHERE ID_MARK = ?");
            preparedStatement.setInt(1, markId);
            bool = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return bool;
    }
}
