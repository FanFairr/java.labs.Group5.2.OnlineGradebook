package services;

import model.Person;
import model.Subject;

import java.util.Map;

public interface StudentSubjectService {

    Map<Subject, String> subjectList(Person person);
    Map<Subject, String> studentSubjectList(Person person);
    boolean studentInfo(String studentLogin, int subjectId);
    void insertInfo(int subjectId, int studentId);
    void deleteInfo(int subjectId, int studentId);

}
