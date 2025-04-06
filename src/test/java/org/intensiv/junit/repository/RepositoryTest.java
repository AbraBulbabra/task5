package org.intensiv.junit.repository;


import org.intensiv.entity.Student;
import org.intensiv.jdbc.starter.StarterDB;
import org.intensiv.repository.crud.StudentCRUD;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepositoryTest {

    private Connection connection;

    @BeforeEach
    void connectDB() {
        connection = StarterDB.getConnection();
        StarterDB.clearTable();
    }

    @Test
    void saveNewStudent() {
        Student student = new Student("Adam", "Smit", "fonk@gmail.com");
        StudentCRUD studentCRUD = new StudentCRUD(connection);

        studentCRUD.createEntity(student);

        Student studentDB = studentCRUD.findById(4L).get();

        assertNotNull(studentDB);
        assertEquals("Adam", studentDB.getName());
        assertEquals("fonk@gmail.com", studentDB.getEmailStudent());
    }

    @AfterEach
    void exitDB(){
        StarterDB.exitConnection();
    }
}