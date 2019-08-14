package services;

import dao.DAOMark;
import model.Mark;

import java.util.List;

public class MarkService {
    public List<Mark> markList(int id) {
        return DAOMark.viewMarks(id);
    }
}
