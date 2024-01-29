package com.solvd.laba.block2.bankhierarchy.persistance.impl;

import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsHasConsultantsDao;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum ClientsHasConsultantsDaoImpl implements ClientsHasConsultantsDao {
    INSTANCE;

    private static final String CREATE_QUERY = "INSERT INTO Clients_has_Consultants VALUES (?,?)";
    private static final String GET_BY_CLIENT
            = "SELECT * FROM Clients_has_Consultants WHERE Clients_idClients = ?";
    private static final String GET_BY_CONSULTANT
            = "SELECT * FROM Clients_has_Consultants WHERE Consultants_idConsultant = ?";
    private static final String GETALL_QUERY = "SELECT * FROM Clients_has_Consultants";

    private static final String DELETE_QUERY
            = "DELETE FROM Clients_has_Consultants WHERE Clients_idClients = ? AND Consultants_idConsultant = ?";

    private ClientsHasConsultantsDaoImpl() {
    }

    @Override
    public Long create(Pair<Client, Consultant> data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, data.getLeft().getId());
            preparedStatement.setLong(2, data.getRight().getId());

            rows = (long) preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                data.getLeft().setId(resultSet.getLong(1));
                data.getRight().setId(resultSet.getLong(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return rows;
    }

    @Override
    public Long create(Long clientId, Long consultantId) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.setLong(2, consultantId);

            rows = (long) preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                clientId = (resultSet.getLong(1));
                consultantId = (resultSet.getLong(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return rows;
    }

    @Override
    public List<Long> getConsultantsForId(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<Long> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                result.add(rs.getLong(2));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return result;
    }

    @Override
    public List<Long> getClientsForId(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<Long> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_CONSULTANT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next())
                result.add(rs.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return result;
    }

    @Override
    public List<Pair<Long, Long>> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<Pair<Long, Long>> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GETALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pair<Long, Long> temp;
                temp = new MutablePair<>(rs.getLong(1), rs.getLong(2));
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
    public Long delete(Pair<Client, Consultant> data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, data.getLeft().getId());
            preparedStatement.setLong(2, data.getRight().getId());

            rows = (long) preparedStatement.executeUpdate();

            if (rows > 0) {
                data.getLeft().setId(null);
                data.getRight().setId(null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return rows;
    }

    @Override
    public Long delete(Long clientId, Long consultantId) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.setLong(2, consultantId);

            rows = (long) preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return rows;
    }
}
