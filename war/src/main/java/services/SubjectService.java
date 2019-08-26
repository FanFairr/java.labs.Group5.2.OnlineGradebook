package services;

import model.Subject;

import java.util.List;

public interface SubjectService {

    Subject viewSubject(int id);
    List<Subject> viewAllSubject();
    boolean insertNewSubject(Subject subject);

}
