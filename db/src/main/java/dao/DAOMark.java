package DAO;

import model.Mark;
import model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface DAOMark {

    List<Mark> viewAllMark();
    Map<Person, List<Mark>> viewMarks(int id);
    boolean updateMark(int taskId, int studentId, int teacherId, double mark);
    boolean deleteMark(int markId);

}
