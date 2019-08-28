package DAO;

import model.Mark;
import model.Person;

import java.util.List;
import java.util.Map;

/**
 * Interface for working with the table "Mark" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOMark {

    /**
     * Method for returning all marks from the database.
     * @return List of marks
     */
    List<Mark> viewAllMark();

    /**
     * Method for returning mark by subject id.
     * @param id - subject id
     * @return A map with a key is the person who teaches the subject
     * and by value is a list of marks for this subject.
     */
    Map<Person, List<Mark>> viewMarks(int id);

    /**
     * Method for updating student marks.
     * @param taskId - task id
     * @param studentId - The id of the person who received the mark.
     * @param teacherId - The id of the person who rated it.
     * @param mark - mark
     * @return The success of the operation.
     */
    boolean updateMark(int taskId, int studentId, int teacherId, double mark);

    /**
     * Method for delete student marks.
     * @param markId - The id of the mark to be deleted.
     * @return The success of the operation.
     */
    boolean deleteMark(int markId);

}
