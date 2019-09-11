package servicesImpl;

import DAO.DAOMark;
import model.Mark;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import services.MarkService;

import java.util.List;
import java.util.Map;

/**
 * Class for working with DAOMark
 * @author Andrey Sherstyuk
 */
public class MarkServiceImpl implements MarkService {

    @Autowired
    DAOMark daoMark;

    /**
     * Method of transmitting data to {@link DAOImpl.DAOMarkImpl}
     * @param id - subject id
     * @return A map with a key is the person who teaches the subject
     * and by value is a list of marks for this subject.
     */
    public Map<Person, List<Mark>> viewMarks(int id) {
        return daoMark.viewMarks(id);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOMarkImpl}
     * @param taskId - task id
     * @param studentId - The id of the person who received the mark.
     * @param teacherId - The id of the person who rated it.
     * @param mark - mark
     * @return The success of the operation.
     */
    public boolean updateMark(int taskId, int studentId, int teacherId, double mark) {
        return daoMark.updateMark(taskId, studentId, teacherId, mark);
    }
}
