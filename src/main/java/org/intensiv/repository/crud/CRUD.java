package org.intensiv.repository.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class CRUD<T> {
    protected Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }

   public abstract List<T> readAll();

    protected abstract T entityParsing(ResultSet entity);
}
