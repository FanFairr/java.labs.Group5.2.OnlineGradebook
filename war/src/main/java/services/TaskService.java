package services;

import model.Task;

import java.util.List;

/**
 * Interface for working with DAOTask
 * @author Andrey Sherstyuk
 */
public interface TaskService {

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param subjectId - subject id
     * @return - List of tasks.
     */
    List<Task> viewAllTask(int subjectId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param taskId - task id.
     * @return - Task.
     */
    Task viewTask(int taskId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param taskId - task id
     * @return - subject id.
     */
    int subjectId(int taskId);

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param task - task for inserting.
     * @return - The success of the operation.
     */
    boolean insertNewTask(Task task);

}
