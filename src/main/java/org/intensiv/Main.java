package org.intensiv;

import org.intensiv.jdbc.starter.StarterDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Main nn = new Main();

        Connection connection = StarterDB.startDB();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM students");
            System.out.println(st.executeQuery().toString());
//            st.executeQuery().toString()
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String string(){
        return  getClass().getClassLoader().getResource("init.sql").toString();
    }
}