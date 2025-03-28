package org.intensiv;

import org.intensiv.entity.Student;
import org.intensiv.repository.crud.CRUD;
import org.intensiv.repository.crud.StudentCRUD;

public class Main {
    public static void main(String[] args) {

        CRUD<Student> studentCRUD = new StudentCRUD();

        System.out.println(studentCRUD.readAll());

        studentCRUD.createEntity(new Student("Pablo", "Abelardo", "Prolog"));

        System.out.println(studentCRUD.readAll());

    }
}