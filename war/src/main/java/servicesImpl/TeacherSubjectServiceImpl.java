package servicesImpl;

import DAOImpl.DAOTeacherSubjectImpl;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.TeacherSubjectService;

import java.util.Set;

public class TeacherSubjectServiceImpl implements TeacherSubjectService {

    @Autowired
    DAOTeacherSubjectImpl daoTeacherSubject;

    public Set<Subject> teacherSubjectSet(int teacherId) {
        return daoTeacherSubject.teacherSubjectSet(teacherId);
    }

    public boolean insertNewInfo(int teacherId, int subjectId) {
        return daoTeacherSubject.insertNewInfo(teacherId, subjectId);
    }
}
