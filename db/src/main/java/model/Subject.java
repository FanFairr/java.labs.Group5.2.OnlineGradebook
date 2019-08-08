package model;

import java.util.Objects;

public class Subject {
    private int id;
    private String name;
    private String content;

    public Subject() {
    }

    public Subject(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Subject(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(content, subject.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
