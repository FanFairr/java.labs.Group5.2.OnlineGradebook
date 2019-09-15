package DAOImpl;

import DAO.DAOPerson;
import model.Person;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for working with the table "Person" on database.
 * @author Anrey Sherstyuk
 */
public class DAOPersonImpl implements DAOPerson {
    private static final Logger DAOPLOGGER = Logger.getLogger(DAOPersonImpl.class);
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Method for returning all person from the database.
     * @return List of person
     */
    public List<Person> viewAllInformation() {
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
            DAOPLOGGER.error("Error when use method viewAllInformation. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return personList;
    }

    /**
     * Method for checking the entered data in the "Authorization" window.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    public boolean validateLogin(Person person) {
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
            DAOPLOGGER.error("Error when use method validateLogin. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    /**
     * Method for checking the entered data in the "Registration" window.
     * @param person - data that is checked.
     * @return True, if the data meets the requirements,
     * it is not true in other cases.
     */
    public boolean validateRegistration(Person person) {
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
            DAOPLOGGER.error("Error when use method validateRegistration. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return b;
    }

    /**
     * The method of selecting a list of persons by task id.
     * @param taskId - task id
     * @return List of persons who have mark on this task.
     */
    public List<Person> selectAllStudents(int taskId) {
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
            DAOPLOGGER.error("Error when use method selectAllStudents. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return personList;
    }

    /**
     * A method for checking whether a given person
     * is a teacher in a subject.
     * @param personId - teacher id
     * @param taskId - task id
     * @return True if person is a teacher in this subject,
     *  false in other cases.
     */
    public boolean personInfo(int personId, int taskId) {
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
            DAOPLOGGER.error("Error when use method personInfo. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }

        return b;
    }

    /**
     * Method for selecting all students.
     * @return list of students.
     */
    public List<Person> viewAllStudents() {
        List<Person> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("select id_person, name from person where STATUS = 'student'");
            while (resultSet.next()) {
                list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            DAOPLOGGER.error("Error when use method viewAllStudents. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    /**
     * Update information of person(student -> teacher
     * with deleting all information about student)
     * @param studentId - student id
     */
    public void updateStudent(int studentId) {
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
            DAOPLOGGER.error("Error when use method updateStudent. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
    }

    /**
     * Method for selecting all teachers.
     * @return list of teachers.
     */
    public List<Person> viewAllTeachers() {
        List<Person> list = new LinkedList<>();
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            resultSet = statement.executeQuery("select id_person, name from person where STATUS = 'teacher'");
            while (resultSet.next()) {
                list.add(new Person(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            DAOPLOGGER.error("Error when use method viewAllTeachers. Message: " + e.getMessage());
        } finally {
            DAOConnection.disconnect();
        }
        return list;
    }

    /**
     * Method for filling test data to database
     */
    public void testTables() {
        try {
            DAOConnection.connect();
            statement = DAOConnection.connection.createStatement();
            statement.executeQuery("select * from person");
            statement.executeQuery("select * from student");
            statement.executeQuery("select * from teacher");
            statement.executeQuery("select * from subject");
            statement.executeQuery("select * from task");
            statement.executeQuery("select * from TEACHER_SUBJECT");
            statement.executeQuery("select * from STUDENT_SUBJECT");
            statement.executeQuery("select * from mark");
        } catch (SQLException e) {
            DAOPLOGGER.error("Error when use method testTables. Message: " + e.getMessage());
            try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/script.txt"))) {
                StringBuilder buffer = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null)
                    buffer.append(str);
                statement.execute(buffer.toString());
            } catch (IOException | SQLException e1) {
                DAOPLOGGER.error("Error when use method testTables in second try. Message: " + e.getMessage());
            }
        } finally {
            DAOConnection.disconnect();
        }
    }
}
