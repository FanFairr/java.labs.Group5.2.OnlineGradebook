package DAO;

import model.Task;

import java.util.List;

/**
 * Interface for working with the table "Task" on database.
 * @author Anrey Sherstyuk
 */
public interface DAOTask {

    /**
     * Method for selecting all tasks of subject id.
     * @param subjectId - subject id
     * @return - List of tasks.
     */
    List<Task> viewAllTask(int subjectId);

    /**
     * Method for selecting task of task id.
     * @param taskId - task id.
     * @return - Task.
     */
    Task viewTask(int taskId);

    /**
     * Method for inserting information to table "Task".
     * @param task - task for inserting.
     * @return - The success of the operation.
     */
    boolean insertNewTask(Task task);

    /**
     * Method for selecting subject id of task id.
     * @param taskId - task id
     * @return - subject id.
     */
    int subjectId(int taskId);

}
