package com.solvd.laba.block2.bankhierarchy.persistance.impl;

import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum ClientsDaoImpl implements ClientsDao {
    INSTANCE;

    private static final String CREATE_QUERY = "INSERT INTO Clients(name, surname, phone, monthlyIncome, totalDebt) VALUES (?,?,?,?,?)";
    private static final String GET_QUERY = "SELECT * FROM Clients WHERE idClients = ?";
    private static final String GETALL_QUERY = "SELECT * FROM Clients";
    private static final String UPDATE_QUERY
            = "UPDATE Clients SET name = ?, surname = ?, phone = ?, monthlyIncome = ?, totalDebt = ? WHERE idClients = ?";
    private static final String DELETE_QUERY = "DELETE FROM Clients WHERE idClients = ?";

    private ClientsDaoImpl() {
    }

    @Override
    public Long create(Client data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getSurname());
            preparedStatement.setString(3, data.getPhone());
            preparedStatement.setInt(4, data.getMonthlyIncome());
            preparedStatement.setInt(5, data.getTotalDebt());

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
    public Client getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Client result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = new Client(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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
    public List<Client> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<Client> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GETALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Client temp = new Client(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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
    public Long updateById(Long id, Client data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setString(2, data.getSurname());
            preparedStatement.setString(3, data.getPhone());
            preparedStatement.setInt(4, data.getMonthlyIncome());
            preparedStatement.setInt(5, data.getTotalDebt());
            preparedStatement.setLong(6, id);

            rows = (long) preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return rows;
    }

    @Override
    public Long update(Client data) {
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
