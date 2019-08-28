package model;

/**
 * Class Task for working with database
 * @author Andrey Sherstyuk
 */
public class Task {
    private int id;
    private String subjectName;
    private String name;
    private String content;
    private double max_mark;

    /**
     * Empty constructor
     */
    public Task() {
    }

    /**
     * Constructor with parameters
     * @param subjectName subject name
     * @param name task name
     * @param content task content
     * @param max_mark maximal mark for task
     */
    public Task(String subjectName, String name, String content, double max_mark) {
        this.subjectName = subjectName;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    /**
     * Constructor with parameters
     * @param id task id
     * @param name task name
     * @param content task content
     * @param max_mark maximal mark for task
     */
    public Task(int id, String name, String content, double max_mark) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    /**
     * Constructor with parameters
     * @param id task id
     * @param subjectName subject name
     * @param name task name
     * @param content task content
     * @param max_mark maximal mark for task
     */
    public Task(int id, String subjectName, String name, String content, double max_mark) {
        this.id = id;
        this.subjectName = subjectName;
        this.name = name;
        this.content = content;
        this.max_mark = max_mark;
    }

    /**
     * Method for setting the task id
     * @return task id
     */
    public int getId() {
        return id;
    }

    /**
     * Method for reading the task id
     * @param id task id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for setting the subject name
     * @return subject name
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Method for reading the subject name
     * @param subjectName subject name
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Method for setting the task name
     * @return task name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for reading the task name
     * @param name task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the task content
     * @return task content
     */
    public String getContent() {
        return content;
    }

    /**
     * Method for reading the task content
     * @param content task content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Method for setting the task maximal score
     * @return maximal score
     */
    public double getMax_mark() {
        return max_mark;
    }

    /**
     * Method for reading the task maximal score
     * @param max_mark maximal score
     */
    public void setMax_mark(double max_mark) {
        this.max_mark = max_mark;
    }
}
