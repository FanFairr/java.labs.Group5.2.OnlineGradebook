package model;

public class Task {
    private int id;
    private String subjectName;
    private String name;
    private String content;
    private double max_mark;

    public Task() {
    }

    public Task(String subjectName, String name, String content, double max_mark) {
        this.subjectName = subjectName;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    public Task(int id, String name, String content, double max_mark) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    public Task(int id, String subjectName, String name, String content, double max_mark) {
        this.id = id;
        this.subjectName = subjectName;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getMax_mark() {
        return max_mark;
    }

    public void setMax_mark(double max_mark) {
        this.max_mark = max_mark;
    }
}
