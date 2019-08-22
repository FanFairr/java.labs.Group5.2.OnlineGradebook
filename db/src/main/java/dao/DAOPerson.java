package dao;

import model.Person;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DAOPerson {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static ResultSet resultSet;

    public static List<Person> viewAllInformation() {
        List<Person> personList = new LinkedList<>();
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select * from PERSON order by id");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return personList;
    }

    public static void viewInformation(String pattern) {

    }

    public static boolean validateLogin(Person person) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select NAME, STATUS, PASSWORD, ID_PERSON from PERSON where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
            if (b) {
                b = bCryptPasswordEncoder.matches(person.getPassword(), resultSet.getString(3));
                if (b) {
                    person.setName(resultSet.getString(1));
                    person.setStatus(resultSet.getString(2));
                    person.setId(resultSet.getInt(4));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    public static boolean validateRegistration(Person person) {
        boolean b = false;
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select * from PERSON where login = ?");
            preparedStatement.setString(1, person.getLogin());
            resultSet = preparedStatement.executeQuery();
            b = resultSet.next();
            if (!b) {
                preparedStatement = DAOConnection.connection.prepareStatement("insert into PERSON values(login_seq.nextval, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(2, person.getLogin());
                preparedStatement.setString(3, person.getPassword());
                preparedStatement.setString(4, person.getEmail());
                preparedStatement.setString(5, person.getStatus());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    public static List<Person> selectAllStudents(int taskId) {
        List<Person> personList = new LinkedList<>();

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select PERSON.*\n" +
                    "from task\n" +
                    "      join SUBJECT on TASK.ID_SUBJECT = SUBJECT.ID_SUBJECT\n" +
                    "      join STUDENT_SUBJECT on SUBJECT.ID_SUBJECT = STUDENT_SUBJECT.ID_SUBJECT\n" +
                    "      join student on STUDENT_SUBJECT.ID_PERSON = STUDENT.ID_PERSON\n" +
                    "      join person on STUDENT.ID_PERSON = PERSON.ID_PERSON\n" +
                    "where task.TASK_ID = ?");
            preparedStatement.setInt(1, taskId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return personList;
    }

    public static boolean personInfo(int personId, int taskId) {
        boolean b = false;

        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("select 1 from PERSON\n" +
                    " join teacher on PERSON.ID_PERSON = TEACHER.ID_PERSON\n" +
                    " join TEACHER_SUBJECT on TEACHER.ID_PERSON = TEACHER_SUBJECT.ID_PERSON\n" +
                    " join subject on TEACHER_SUBJECT.ID_SUBJECT = SUBJECT.ID_SUBJECT\n" +
                    " join task on SUBJECT.ID_SUBJECT = TASK.ID_SUBJECT\n" +
                    "where TASK_ID = ? and PERSON.ID_PERSON = ?");
            preparedStatement.setInt(1, taskId);
            preparedStatement.setInt(2, personId);
            resultSet = preparedStatement.executeQuery();

            b = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    public static List<Person> viewAllStudents() {
        List<Person> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("select id_person, name from person where STATUS = 'student'");
            while (resultSet.next()) {
                list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    public static void updateStudent(int studentId) {
        try {
            DAOConnection.connect();
            preparedStatement = DAOConnection.connection.prepareStatement("update person set STATUS = 'teacher' where ID_PERSON = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();

            preparedStatement = DAOConnection.connection.prepareStatement("update person1 set STATUS = 'teacher' where ID_PERSON = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();

            preparedStatement = DAOConnection.connection.prepareStatement("delete from student where ID_PERSON = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();

            preparedStatement = DAOConnection.connection.prepareStatement("insert into teacher values (?)");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();

            preparedStatement = DAOConnection.connection.prepareStatement("delete from STUDENT_SUBJECT where ID_PERSON = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();

            preparedStatement = DAOConnection.connection.prepareStatement("delete from MARK where ID_STUDENT = ?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
    }

    public static List<Person> viewAllTeachers() {
        List<Person> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("select id_person, name from person where STATUS = 'teacher'");
            while (resultSet.next()) {
                list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }
}
