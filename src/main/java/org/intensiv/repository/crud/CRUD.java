package org.intensiv.repository.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CRUD<T> {
    protected Connection connection;
    private String deletedQuery;
    private String getAll;
    private String findByIdEntity;


    CRUD(Connection connection, String deletedQuery, String getAll, String findByIdEntity) {
        this.connection = connection;
        this.deletedQuery = deletedQuery;
        this.getAll = getAll;
        this.findByIdEntity = findByIdEntity;
    }

    public List<T> readEntities() {
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

    public void deletedEntityForId(int id) {
        Optional<T> entity = this.findById(id);

        if (entity.isPresent()) {
            T deleted = entity.get();

            try (PreparedStatement preparedStatement = connection.prepareStatement(deletedQuery)) {

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                System.out.println("Delete entity ->" + deleted);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<T> findById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(findByIdEntity)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(entityParsing(resultSet));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка базы данных", e);
        }
    }

    public abstract void createEntity(T t);

    public abstract void updateEntityForId(int id, T t);

    protected abstract T entityParsing(ResultSet entity);
}
