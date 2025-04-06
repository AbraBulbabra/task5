package org.intensiv.repository.crud;

import org.intensiv.entity.Student;
import org.intensiv.jdbc.starter.StarterDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCRUD extends CRUD<Student> {

    private static String ALL_STUDENTS = "SELECT * FROM students";
    private static String FIND_BY_ID_ENTITY = "SELECT * FROM students WHERE id = ?";
    private static String INSERT_STUDENT = "INSERT INTO students(name, surname, email_student) VALUES (?,?,?)";
    private static String UPDATE_STUDENT = "UPDATE students SET name = ?, surname = ?, email_student = ? WHERE id = ?";
    private static String DELETED_STUDENT = "DELETE FROM students WHERE id = ?";


    public StudentCRUD() {
        super(StarterDB.getConnection(), DELETED_STUDENT, ALL_STUDENTS, FIND_BY_ID_ENTITY);
    }

    public StudentCRUD(Connection connection) {
        super(connection, DELETED_STUDENT, ALL_STUDENTS, FIND_BY_ID_ENTITY);
    }

    @Override
    public void createEntity(Student student) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmailStudent());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Student не создан");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEntityForId(long id, Student student) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmailStudent());
            preparedStatement.setLong(4, id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Student entityParsing(ResultSet resultSet) {
        try {
            return new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("email_student"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
