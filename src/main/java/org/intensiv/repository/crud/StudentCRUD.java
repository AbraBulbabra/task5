package org.intensiv.repository.crud;

import org.intensiv.entity.Student;
import org.intensiv.jdbc.starter.StarterDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentCRUD extends CRUD<Student>{

    public StudentCRUD() {
        super(StarterDB.setConnection());
    }

    @Override
    public List<Student> readAll(){
        List<Student> students = new ArrayList<>();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()){
                students.add(entityParsing(resultSet));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    protected Student entityParsing(ResultSet resultSet){
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
