package services;

import dao.DAOStudentSubject;
import model.Person;
import model.Subject;

import java.util.Map;

public class StudentSubjectService {
    public Map<Subject, String> subjectList(Person person) {
        return DAOStudentSubject.subjectList(person);
    }

    public Map<Subject, String> studentSubjectList(Person person) {
        return DAOStudentSubject.studentSubjectList(person);
    }

    public boolean studentInfo(String studentLogin, int subjectId) {
        return DAOStudentSubject.studentInfo(studentLogin, subjectId);
    }

    public void insertInfo(int subjectId, int studentId) {
        DAOStudentSubject.insertNewInfo(studentId, subjectId);
    }

    public void deleteInfo(int subjectId, int studentId) {
        DAOStudentSubject.deleteInfo(studentId, subjectId);
    }
}
