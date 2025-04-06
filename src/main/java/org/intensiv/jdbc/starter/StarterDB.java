package org.intensiv.jdbc.starter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StarterDB {

    private static final String PATH_DB = "jdbc:h2:mem:test;";
    private static final String NAME_INIT_SCRIPT = "init.sql";
    private static final String PATH_INIT_SCRIPT =
            StarterDB.class.getClassLoader().getResource(NAME_INIT_SCRIPT).toString();
    private static final String INIT_SCRIPT = "INIT=RUNSCRIPT FROM";

    public static final String URL_DB = String.format("%s%s '%s'", PATH_DB, INIT_SCRIPT, PATH_INIT_SCRIPT);
    private static final String USER_NAME_DB = "sa";
    private static final String PASSWORD_DB = "";

    private static String CLEAR_TABLE_STUDENT = "TRUNCATE TABLE students";

    private static Connection connection;


    private StarterDB() {
        try {
            connection = DriverManager.getConnection(
                    URL_DB,
                    USER_NAME_DB,
                    PASSWORD_DB
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new StarterDB();
        }
        return connection;
    }



    public static void clearTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CLEAR_TABLE_STUDENT);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы Students", e);
        }
    }

    public static void exitConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        connection = null;
    }
}