package model;

import java.util.Objects;

/**
 * Class Subject for working with database
 * @author Andrey Sherstyuk
 */
public class Subject {
    private int id;
    private String name;
    private String content;

    /**
     * Empty constructor
     */
    public Subject() {
    }

    /**
     * Constructor with parameters
     * @param name subject name
     * @param content subject content
     */
    public Subject(String name, String content) {
        this.name = name;
        this.content = content;
    }

    /**
     * Constructor with parameters
     * @param id subject id
     * @param name subject name
     * @param content subject content
     */
    public Subject(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    /**
     * Overridden method equals
     * @param o - The object to compare against this.
     * @return - result of compare
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(content, subject.content);
    }

    /**
     * Overridden method hashCode
     * @return subject hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }

    /**
     * Method for reading the subject id
     * @return subject id
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting the subject id
     * @param id subject id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for reading the subject name
     * @return subject name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting the subject name
     * @param name subject name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for reading the subject content
     * @return subject content
     */
    public String getContent() {
        return content;
    }

    /**
     * Method for setting the subject content
     * @param content subject content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
