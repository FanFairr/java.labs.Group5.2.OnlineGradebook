package services;

import model.Mark;
import model.Person;

import java.util.List;
import java.util.Map;

/**
 * Interface for working with DAOMark
 * @author Andrey Sherstyuk
 */
public interface MarkService {

    /**
     * Method of transmitting data to {@link DAOImpl.DAOMarkImpl}
     * @param id - subject id
     * @return A map with a key is the person who teaches the subject
     * and by value is a list of marks for this subject.
     */
    Map<Person, List<Mark>> viewMarks(int id);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOMarkImpl}
     * @param taskId - task id
     * @param studentId - The id of the person who received the mark.
     * @param teacherId - The id of the person who rated it.
     * @param mark - mark
     * @return The success of the operation.
     */
    boolean updateMark(int taskId, int studentId, int teacherId, double mark);

}
