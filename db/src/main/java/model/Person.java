package model;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Person {
    private int personId;
    private String name;
    private String login;
    private String password;
    private String email;
    private String status;

    public Person() {
    }

    public Person(int personId, String name) {
        this.personId = personId;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int personId, String name, String login, String password, String email) {
        this.personId = personId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name, login, password, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return personId;
    }

    public void setId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
