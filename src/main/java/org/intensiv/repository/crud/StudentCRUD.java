package org.intensiv.repository.crud;

import org.intensiv.entity.Student;
import org.intensiv.jdbc.starter.StarterDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUD extends CRUD<Student> {

    private static String ALL_STUDENTS = "SELECT * FROM students";
    private static String FIND_BY_ID_ENTITY = "SELECT * FROM students WHERE id = ?";
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, course_name) VALUES (?,?,?)";
    private static String UPDATE_STUDENT = "UPDATE students SET name = ?, surname = ?, course_name = ? WHERE id = ?";
    private static String DELETED_STUDENT = "DELETE FROM students WHERE id = ?";


    public StudentCRUD() {
        super(StarterDB.setConnection(), DELETED_STUDENT, ALL_STUDENTS, FIND_BY_ID_ENTITY);
    }

    @Override
    public void createEntity(Student student) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourseName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEntityForId(int id, Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getCourseName());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Student entityParsing(ResultSet resultSet) {
        try {
            return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("course_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
