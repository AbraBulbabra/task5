package org.intensiv.repository.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class CRUD<T> {
    protected Connection connection;
    protected String deletedQwery;
    private String getAll;


    CRUD(Connection connection, String deletedQwery, String getAll) {
        this.connection = connection;
        this.deletedQwery = deletedQwery;
        this.getAll = getAll;
    }

    public  List<T> readEntities() {
        List<T> tList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAll);

            while (resultSet.next()) {
                tList.add(entityParsing(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tList;
    }

    public abstract T getEntityForId(int id);

    public abstract void createEntity(T t);

    public abstract void updateEntityForId(int id, T t);

    public  void deletedEntityForId(int id){
        T deleted = this.getEntityForId(id);

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletedQwery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Delete entity ->" + deleted);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T entityParsing(ResultSet entity);
}
