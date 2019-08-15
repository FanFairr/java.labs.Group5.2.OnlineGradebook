package services;

import dao.DAOMark;
import model.Mark;
import model.Person;

import java.util.List;
import java.util.Map;

public class MarkService {
    public Map<Person, List<Mark>> viewMarks(int id) {
        return DAOMark.viewMarks(id);
    }
}
