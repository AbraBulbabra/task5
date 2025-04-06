package org.intensiv;

import org.intensiv.jdbc.starter.StarterDB;
import org.intensiv.repository.crud.StudentCRUD;
import org.intensiv.service.StudentService;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = new StudentService(new StudentCRUD(StarterDB.getConnection()));

        System.out.println(studentService.getStudents());

        studentService.chengEmailOfStudent(2,"alexYT@gmail.com");

        System.out.println("-------------");
        System.out.println(studentService.getStudents());

        studentService.chengNameOfStudent(2,"Alex");

        System.out.println("-------------");
        System.out.println(studentService.getStudents());
    }
}