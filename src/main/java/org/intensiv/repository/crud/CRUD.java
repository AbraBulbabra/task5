package org.intensiv.repository.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public abstract class CRUD<T> {
    protected Connection connection;

    CRUD(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> readAll();

    public abstract void createEntity(T t);

    protected abstract T entityParsing(ResultSet entity);
}
