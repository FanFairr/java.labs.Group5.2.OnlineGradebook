package services;

import model.Subject;

import java.util.List;

/**
 * Interface for working with DAOSubject
 * @author Andrey Sherstyuk
 */
public interface SubjectService {

    /**
     * Method of transmitting data to {@link DAOImpl.DAOSubjectImpl}
     * @param id - subject id.
     * @return - Subject.
     */
    Subject viewSubject(int id);

    /**
     * Method for selecting all subjects.
     * @return - List of subjects.
     */
    List<Subject> viewAllSubject();

    /**
     * Method of transmitting data to {@link DAOImpl.DAOSubjectImpl}
     * @param subject - subject that need inserting.
     * @return - The success of the operation.
     */
    boolean insertNewSubject(Subject subject);

}
