package org.intensiv.entity;

public class Student {

    private int id;
    private String name;
    private String surname;
    private String emailStudent;

    public Student(int id, String name, String surname, String emailStudent) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emailStudent = emailStudent;
    }

    public Student(String name, String surname, String emailStudent) {
        this.name = name;
        this.surname = surname;
        this.emailStudent = emailStudent;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "\nStudent{\n" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email student='" + emailStudent + '\'' +
                "\n" +
                '}';
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailStudent() {
        return emailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }
}
