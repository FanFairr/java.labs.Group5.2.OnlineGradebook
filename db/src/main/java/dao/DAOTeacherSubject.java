package DAO;

import model.Person;
import model.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DAOTeacherSubject {

    Map<Person, List<Subject>> viewTeacherSubject();
    Map<Subject, List<Person>> viewSubjectTeacher();
    Set<Subject> teacherSubjectSet(int teacherId);
    boolean insertNewInfo(int teacherId, int subjectId);
    boolean deleteInfo(int teacherId, int subjectId);

}
