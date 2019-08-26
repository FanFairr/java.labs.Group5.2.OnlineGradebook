package DAO;

import model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DAOPerson {

    List<Person> viewAllInformation();
    boolean validateLogin(Person person);
    boolean validateRegistration(Person person);
    List<Person> selectAllStudents(int taskId);
    boolean personInfo(int personId, int taskId);
    List<Person> viewAllStudents();
    void updateStudent(int studentId);
    List<Person> viewAllTeachers();

}
