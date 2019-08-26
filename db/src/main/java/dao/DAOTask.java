package DAO;

import model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DAOTask {

    List<Task> viewAllTask(int subjectId);
    Task viewTask(int taskId);
    boolean insertNewTask(Task task);
    int subjectId(int taskId);

}
