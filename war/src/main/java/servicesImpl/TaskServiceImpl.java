package servicesImpl;

import DAOImpl.DAOTaskImpl;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import services.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    @Autowired
    DAOTaskImpl daoTask;

    public List<Task> viewAllTask(int subjectId) {
        return daoTask.viewAllTask(subjectId);
    }

    public Task viewTask(int taskId) {
        return daoTask.viewTask(taskId);
    }

    public int subjectId(int taskId) {
        return daoTask.subjectId(taskId);
    }

    public boolean insertNewTask(Task task) {
        return daoTask.insertNewTask(task);
    }
}
