package servicesImpl;

import DAOImpl.DAOSubjectImpl;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService{

    @Autowired
    DAOSubjectImpl daoSubject;

    public Subject viewSubject(int id) {
        return daoSubject.viewSubject(id);
    }

    public List<Subject> viewAllSubject() {
        return daoSubject.viewAllSubject();
    }

    public boolean insertNewSubject(Subject subject) {
        return daoSubject.insertNewSubject(subject);
    }

}
