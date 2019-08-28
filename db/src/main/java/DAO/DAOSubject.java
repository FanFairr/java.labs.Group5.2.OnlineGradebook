package DAO;

import model.Subject;

import java.util.List;

/**
 * Interface for working with the table "Subject" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOSubject {

    /**
     * Method for selecting all subjects.
     * @return - List of subjects.
     */
    List<Subject> viewAllSubject();

    /**
     * Method for selecting subject of subject id.
     * @param id - subject id.
     * @return - Subject.
     */
    Subject viewSubject(int id);

    /**
     * Method for inserting new information to table "Subject"
     * @param subject - subject that need inserting.
     * @return - The success of the operation.
     */
    boolean insertNewSubject(Subject subject);

    /**
     * Method for deleting information on table "Subject"
     * @param id - subject id
     * @return - The success of the operation.
     */
    boolean deleteSubject(int id);

}
