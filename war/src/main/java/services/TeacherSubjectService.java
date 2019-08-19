package services;

import dao.DAOTeacherSubject;
import model.Subject;

import java.util.Set;

public class TeacherSubjectService {
    public Set<Subject> teacherSubjectSet(int teacherId) {
        return DAOTeacherSubject.teacherSubjectSet(teacherId);
    }
}
