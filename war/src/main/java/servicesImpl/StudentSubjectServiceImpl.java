package servicesImpl;

import DAOImpl.DAOStudentSubjectImpl;
import model.Person;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.StudentSubjectService;

import java.util.Map;

public class StudentSubjectServiceImpl implements StudentSubjectService {

    @Autowired
    DAOStudentSubjectImpl daoStudentSubject;

    public Map<Subject, String> subjectList(Person person) {
        return daoStudentSubject.subjectList(person);
    }

    public Map<Subject, String> studentSubjectList(Person person) {
        return daoStudentSubject.studentSubjectList(person);
    }

    public boolean studentInfo(String studentLogin, int subjectId) {
        return daoStudentSubject.studentInfo(studentLogin, subjectId);
    }

    public void insertInfo(int subjectId, int studentId) {
        daoStudentSubject.insertNewInfo(studentId, subjectId);
    }

    public void deleteInfo(int subjectId, int studentId) {
        daoStudentSubject.deleteInfo(studentId, subjectId);
    }
}
