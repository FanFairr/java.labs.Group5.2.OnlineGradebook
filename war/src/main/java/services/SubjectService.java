package services;

import dao.DAOSubject;
import model.Subject;

public class SubjectService {
    public Subject viewSubject(int id) {
        return DAOSubject.viewSubject(id);
    }
}
