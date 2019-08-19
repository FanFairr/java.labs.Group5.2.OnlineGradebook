package services;

import dao.DAOSubject;
import model.Subject;

import java.util.List;

public class SubjectService {
    public Subject viewSubject(int id) {
        return DAOSubject.viewSubject(id);
    }

    public List<Subject> viewAllSubject() {
        return DAOSubject.viewAllSubject();
    }
}
