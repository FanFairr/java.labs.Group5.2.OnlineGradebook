package model;

import java.util.Objects;

/**
 * Class Person for working with database
 * @author Andrey Sherstyuk
 */
public class Person {
    private int personId;
    private String name;
    private String login;
    private String password;
    private String email;
    private String status;

    /**
     * Empty constructor
     */
    public Person() {
    }

    /**
     * Constructor with parameters
     * @param personId - person id
     * @param name - person name
     */
    public Person(int personId, String name) {
        this.personId = personId;
        this.name = name;
    }

    /**
     * Constructor with parameters
     * @param name - person name
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Constructor with parameters
     * @param name - person name
     * @param login - person login
     * @param password - person password
     * @param email - person email
     */
    public Person(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructor with parameters
     * @param personId - person id
     * @param name - person name
     * @param login - person login
     * @param password - person password
     * @param email - person email
     */
    public Person(int personId, String name, String login, String password, String email) {
        this.personId = personId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
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
        Person person = (Person) o;
        return personId == person.personId &&
                Objects.equals(name, person.name);
    }

    /**
     * Overridden method hashCode
     * @return person hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(personId, name, login, password, email);
    }

    /**
     * Overridden method toString
     * @return person string
     */
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

    /**
     * Method for reading the person id
     * @return person id
     */
    public int getId() {
        return personId;
    }

    /**
     * Method for setting the person id
     * @param personId - person id
     */
    public void setId(int personId) {
        this.personId = personId;
    }

    /**
     * Method for reading the person name
     * @return person name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for setting the person name
     * @param name person name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for reading the person login
     * @return person login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Method for setting the person login
     * @param login person login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Method for reading the person password
     * @return person password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting the person password
     * @param password person password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for reading the person email
     * @return person email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method for setting the person email
     * @param email person email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method for reading the person status
     * @return person status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method for setting the person status
     * @param status person status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
