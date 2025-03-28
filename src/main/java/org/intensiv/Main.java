package org.intensiv;

import org.intensiv.repository.crud.CRUD;
import org.intensiv.repository.crud.StudentCRUD;

public class Main {
    public static void main(String[] args) {

        CRUD studentCRUD = new StudentCRUD();
        System.out.println(studentCRUD.readAll());

    }
}