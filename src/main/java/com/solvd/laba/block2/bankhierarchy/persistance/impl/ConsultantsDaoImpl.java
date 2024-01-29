package com.solvd.laba.block2.bankhierarchy.persistance.impl;

import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ConsultantsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum ConsultantsDaoImpl implements ConsultantsDao {
    INSTANCE;

    private static final String CREATE_QUERY = "INSERT INTO Consultants(name, surname, phone) VALUES (?,?,?)";
    private static final String GET_QUERY = "SELECT * FROM Consultants WHERE idConsultant = ?";
    private static final String GETALL_QUERY = "SELECT * FROM Consultants";
    private static final String UPDATE_QUERY
            = "UPDATE Consultants SET name = ?, surname = ?, phone = ? WHERE idConsultant = ?";
    private static final String DELETE_QUERY = "DELETE FROM Consultants WHERE idConsultant = ?";

    private ConsultantsDaoImpl() {
    }

    @Override
    public Long create(Consultant data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getSurname());
            preparedStatement.setString(3, data.getPhone());

            rows = (long) preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                data.setId(resultSet.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return rows;
    }

    @Override
    public Consultant getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Consultant result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = new Consultant(rs.getString(2), rs.getString(3), rs.getString(4));
                result.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return result;
    }

    @Override
    public List<Consultant> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<Consultant> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GETALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Consultant temp = new Consultant(rs.getString(2), rs.getString(3), rs.getString(4));
                temp.setId(rs.getLong(1));
                result.add(temp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return result;
    }

    @Override
    public Long updateById(Long id, Consultant data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getSurname());
            preparedStatement.setString(3, data.getPhone());
            preparedStatement.setLong(4, id);

            rows = (long) preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return rows;
    }

    @Override
    public Long update(Consultant data) {
        return updateById(data.getId(), data);
    }

    @Override
    public Long deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            rows = (long) preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return rows;
    }
}
