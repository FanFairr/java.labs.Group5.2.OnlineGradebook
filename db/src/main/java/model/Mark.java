package model;

/**
 * Class Mark for working with database
 * @author Andrey Sherstyuk
 */
public class Mark {
    private int id;
    private String taskName;
    private String studentName;
    private String teacherName;
    private double mark;

    /**
     * Empty constructor
     */
    public Mark() {
    }

    /**
     * Constructor with parameters
     * @param taskName - name of task
     * @param studentName - student name
     * @param mark - mark
     */
    public Mark(String taskName, String studentName, double mark) {
        this.taskName = taskName;
        this.studentName = studentName;
        this.mark = mark;
    }

    /**
     * Constructor with parameters
     * @param taskName - name of task
     * @param studentName - student name
     * @param teacherName - teacher name
     * @param mark - mark for task
     */
    public Mark(String taskName, String studentName, String teacherName, double mark) {
        this.taskName = taskName;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.mark = mark;
    }

    /**
     * Method for reading the mark id
     * @return mark id
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting the mark id
     * @param id mark id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for reading the task name
     * @return task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Method for setting the task name
     * @param taskName task name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Method for reading student name
     * @return student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Method for setting the student name
     * @param studentName student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Method for reading teacher name
     * @return teacher name
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * Method for setting the teacher name
     * @param teacherName teacher name
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * Method for reading mark score
     * @return mark score
     */
    public double getMark() {
        return mark;
    }

    /**
     * Method for setting the mark score
     * @param mark mark score
     */
    public void setMark(double mark) {
        this.mark = mark;
    }
}
