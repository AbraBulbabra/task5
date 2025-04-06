package org.intensiv.service;

import org.intensiv.entity.Student;
import org.intensiv.repository.crud.StudentCRUD;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentService {
    private final StudentCRUD studentCRUD;

    public StudentService(StudentCRUD studentCRUD) {
        this.studentCRUD = studentCRUD;
    }

    public List<Student> getStudents() {
        return studentCRUD.readEntities();
    }

    public void chengEmailOfStudent(long id, String email) {
        Student student = null;

        if (checkEmail(email)) {
            student = getStudent(id);
            student.setEmailStudent(email);
        }

        studentCRUD.updateEntityForId(id, student);
    }

    private boolean checkEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        if (email == null || email.isEmpty() && email.length() < 255) {
            return false;
        }

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public void chengNameOfStudent(long id, String name) {
        Student student = null;

        if (name.length() >= 50) {
            name = name.substring(0, 49);
        }

        if (checkParamName(name)) {
            student = getStudent(id);
            student.setName(name);
        }

        studentCRUD.updateEntityForId(id, student);
    }

    private boolean checkParamName(String name) {
        return (name != null && !name.isEmpty());
    }

    public Student getStudent(long id) {
        if (checkIndexOutOfBounds(id)) {
            return studentCRUD.findById(id).get();
        }

        throw new IllegalArgumentException("Student with this ID not found");
    }


    private boolean checkEntityByIndex(long id) {
        return checkIndexOutOfBounds(id) && studentCRUD.findById(id).isPresent();
    }

    private boolean checkIndexOutOfBounds(long id) {
        return id > 0;
    }
}