package DAOImpl;

import DAO.DAOMark;
import model.Mark;
import model.Person;

import java.sql.*;
import java.util.*;

public class DAOMarkImpl implements DAOMark {

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public List<Mark> viewAllMark() {
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

    public Map<Person, List<Mark>> viewMarks(int id) {
        Map<Person, List<Mark>> map = new HashMap<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select stud.ID_PERSON, stud.name, task.name, MARK_SCORE\n" +
                    "from STUDENT\n" +
                    "  left join STUDENT_SUBJECT ss on STUDENT.ID_PERSON = ss.ID_PERSON\n" +
                    "  join PERSON stud on STUDENT.ID_PERSON = stud.ID_PERSON\n" +
                    "  left join MARK on MARK.ID_STUDENT = STUDENT.ID_PERSON\n" +
                    "  left join task on MARK.TASK_ID = TASK.TASK_ID\n" +
                    "where ss.ID_SUBJECT = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                map.put(new Person(resultSet.getInt(1), resultSet.getString(2)),
                        new LinkedList<>(Collections.singletonList(
                                new Mark(resultSet.getString(3), resultSet.getString(2),
                                        resultSet.getDouble(4)))));

                while (resultSet.next()) {
                    Person person = new Person(resultSet.getInt(1), resultSet.getString(2));
                    Mark mark = new Mark(resultSet.getString(3), resultSet.getString(2),
                            resultSet.getDouble(4));

                    if (map.containsKey(person)) {
                        map.get(person).add(mark);
                    } else {
                        map.put(person, new LinkedList<>(Collections.singletonList(mark)));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return map;
    }

    private boolean insertNewMark(int taskId, int studentId, int teacherId, double mark) {
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

    public boolean updateMark(int taskId, int studentId, int teacherId, double mark) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select 1 from mark where TASK_ID = ? and ID_STUDENT = ? and ID_TEACHER = ?");
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, teacherId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement = DAOConnection.connection.prepareStatement("update mark set mark_score = ? " +
                        "where TASK_ID = ? and ID_STUDENT = ? and ID_TEACHER = ?");
                preparedStatement.setDouble(1, mark);
                preparedStatement.setInt(2, taskId);
                preparedStatement.setInt(3, studentId);
                preparedStatement.setInt(4, teacherId);
                b = preparedStatement.execute();
            } else
                b = insertNewMark(taskId, studentId, teacherId, mark);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    public boolean deleteMark(int markId) {
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
