package model;

public class Mark {
    private int id;
    private String taskName;
    private String studentName;
    private String teacherName;
    private double mark;

    public Mark() {
    }

    public Mark(String taskName, String studentName, String teacherName, double mark) {
        this.taskName = taskName;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
