package services;

import model.Subject;

import java.util.Set;

public interface TeacherSubjectService {

    Set<Subject> teacherSubjectSet(int teacherId);
    boolean insertNewInfo(int teacherId, int subjectId);

}
