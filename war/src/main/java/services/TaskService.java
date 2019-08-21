package services;

import dao.DAOTask;
import model.Task;

import java.util.List;

public class TaskService {
    public List<Task> viewAllTask(int subjectId) {
        return DAOTask.viewAllTask(subjectId);
    }

    public Task viewTask(int taskId) {
        return DAOTask.viewTask(taskId);
    }

    public int subjectId(int taskId) {
        return DAOTask.subjectId(taskId);
    }
}
