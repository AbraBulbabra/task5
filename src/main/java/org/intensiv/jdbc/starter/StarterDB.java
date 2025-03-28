package org.intensiv.jdbc.starter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StarterDB {

    private static final String PATH_DB = "jdbc:h2:mem:test;";
    private static final String NAME_INIT_SCRIPT = "init.sql";
    private static final String PATH_INIT_SCRIPT =
            StarterDB.class.getClassLoader().getResource(NAME_INIT_SCRIPT).toString();
    private static final String INIT_SCRIPT = "INIT=RUNSCRIPT FROM '" + PATH_INIT_SCRIPT + "'";


    private static final String URL_DB = PATH_DB + INIT_SCRIPT;
    private static final String USER_NAME_DB = "sa";
    private static final String PASSWORD_DB = "";


    private StarterDB() {
    }

    public static Connection startDB() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(
                    URL_DB,
                    USER_NAME_DB,
                    PASSWORD_DB
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

}
