package services;

import model.Mark;
import model.Person;

import java.util.List;
import java.util.Map;

public interface MarkService {

    Map<Person, List<Mark>> viewMarks(int id);
    boolean updateMark(int taskId, int studentId, int teacherId, double mark);

}
