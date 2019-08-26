package services;

import model.Task;

import java.util.List;

public interface TaskService {

    List<Task> viewAllTask(int subjectId);
    Task viewTask(int taskId);
    int subjectId(int taskId);
    boolean insertNewTask(Task task);

}
