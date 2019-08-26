package DAO;

import model.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DAOSubject {

    List<Subject> viewAllSubject();
    Subject viewSubject(int id);
    boolean insertNewSubject(Subject subject);
    boolean deleteSubject(int id);

}
