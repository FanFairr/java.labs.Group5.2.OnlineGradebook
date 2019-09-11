package servicesImpl;

import DAO.DAOSubject;
import model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import services.SubjectService;

import java.util.List;

/**
 * Class for working with DAOSubject
 * @author Andrey Sherstyuk
 */
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    DAOSubject daoSubject;

    /**
     * Method of transmitting data to {@link DAOImpl.DAOSubjectImpl}
     * @param id - subject id.
     * @return - Subject.
     */
    public Subject viewSubject(int id) {
        return daoSubject.viewSubject(id);
    }

    /**
     * Method for selecting all subjects.
     * @return - List of subjects.
     */
    public List<Subject> viewAllSubject() {
        return daoSubject.viewAllSubject();
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOSubjectImpl}
     * @param subject - subject that need inserting.
     * @return - The success of the operation.
     */
    public boolean insertNewSubject(Subject subject) {
        return daoSubject.insertNewSubject(subject);
    }

}
