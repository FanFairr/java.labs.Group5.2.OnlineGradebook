package servicesImpl;

import DAOImpl.DAOMarkImpl;
import model.Mark;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import services.MarkService;

import java.util.List;
import java.util.Map;

public class MarkServiceImpl implements MarkService {

    @Autowired
    DAOMarkImpl daoMark;

    public Map<Person, List<Mark>> viewMarks(int id) {
        return daoMark.viewMarks(id);
    }

    public boolean updateMark(int taskId, int studentId, int teacherId, double mark) {
        return daoMark.updateMark(taskId, studentId, teacherId, mark);
    }
}
