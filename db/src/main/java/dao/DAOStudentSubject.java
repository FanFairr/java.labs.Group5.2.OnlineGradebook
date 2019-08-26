package DAO;

import model.Person;
import model.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface DAOStudentSubject {

    Map<Person, List<Subject>> viewStudentSubject();
    Map<Subject, List<Person>> viewSubjectStudent();
    Map<Subject, String> subjectList(Person person);
    Map<Subject, String> studentSubjectList(Person person);
    boolean insertNewInfo(int studentId, int subjectId);
    boolean deleteInfo(int studentId, int subjectId);
    boolean studentInfo(String studentLogin, int subjectId);

}
