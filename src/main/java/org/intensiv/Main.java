package org.intensiv;

import org.intensiv.entity.Student;
import org.intensiv.repository.crud.CRUD;
import org.intensiv.repository.crud.StudentCRUD;

public class Main {
    public static void main(String[] args) {

        CRUD<Student> studentCRUD = new StudentCRUD();

        System.out.println(studentCRUD.readEntities());

        studentCRUD.createEntity(new Student("Pablo", "Abelardo", "Prolog"));

        System.out.println(studentCRUD.readEntities());

        studentCRUD.updateEntityForId(2, new Student("Arlicino", "Ocon", "Swift"));

        System.out.println(studentCRUD.readEntities());

        System.out.println(studentCRUD.getEntityForId(4));

        studentCRUD.deletedEntityForId(4);
    }
}