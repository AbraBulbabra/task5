package org.intensiv.junit.service;

import org.intensiv.entity.Student;
import org.intensiv.repository.crud.StudentCRUD;
import org.intensiv.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServiceTest {

    @Mock
    StudentCRUD studentCRUD;

    @InjectMocks
    StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Положительные сценарии поиска студента по его ID")
    void getStudentPositive() {
        long idStudent = 1L;

        Student student = new Student(idStudent, "Adam", "Smit", "fonk@gmail.com");

        when(studentCRUD.findById(idStudent)).thenReturn(Optional.of(student));

        Student result = studentService.getStudent(idStudent);

        assertEquals(student, result);
        verify(studentCRUD, times(1)).findById(idStudent);
    }
}
