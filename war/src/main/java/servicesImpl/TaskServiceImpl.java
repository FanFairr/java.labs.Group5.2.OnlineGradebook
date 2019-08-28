package servicesImpl;

import DAOImpl.DAOTaskImpl;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import services.TaskService;

import java.util.List;

/**
 * Class for working with DAOTask
 * @author Andrey Sherstyuk
 */
public class TaskServiceImpl implements TaskService {

    @Autowired
    DAOTaskImpl daoTask;

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param subjectId - subject id
     * @return - List of tasks.
     */
    public List<Task> viewAllTask(int subjectId) {
        return daoTask.viewAllTask(subjectId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param taskId - task id.
     * @return - Task.
     */
    public Task viewTask(int taskId) {
        return daoTask.viewTask(taskId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param taskId - task id
     * @return - subject id.
     */
    public int subjectId(int taskId) {
        return daoTask.subjectId(taskId);
    }

    /**
     * Method of transmitting data to {@link DAOImpl.DAOTaskImpl}
     * @param task - task for inserting.
     * @return - The success of the operation.
     */
    public boolean insertNewTask(Task task) {
        return daoTask.insertNewTask(task);
    }
}
