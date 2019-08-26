package services;

import model.Person;

import java.util.List;

public interface PersonService {

    boolean validateLogin(Person person);
    boolean validateRegistration(Person person);
    List<Person> selectAllStudents(int taskId);
    boolean personInfo(int personId, int taskId);
    List<Person> viewAllStudent();
    List<Person> viewAllTeachers();
    void updateStudent(int studentId);

}
