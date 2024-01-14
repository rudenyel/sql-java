package cz.murkaliza.jdbc;

import cz.murkaliza.jdbc.utils.DataTransferObject;

public class Book implements DataTransferObject {
    private long id;
    private String title;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + firstName + " | " + lastName;
    }

}
