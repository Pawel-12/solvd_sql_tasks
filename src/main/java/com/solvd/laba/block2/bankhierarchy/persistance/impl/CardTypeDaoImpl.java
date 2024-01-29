package com.solvd.laba.block2.bankhierarchy.persistance.impl;

import com.solvd.laba.block2.bankhierarchy.domain.CardType;
import com.solvd.laba.block2.bankhierarchy.persistance.CardTypeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public enum CardTypeDaoImpl implements CardTypeDao {
    INSTANCE;

    private static final String CREATE_QUERY = "INSERT INTO CardType(name,limit,multicurrency,exchangeFeeRate,creditFeeRate) VALUES (?,?,?,?,?)";
    private static final String GET_QUERY = "SELECT * FROM CardType WHERE idCardType = ?";
    private static final String GETALL_QUERY = "SELECT * FROM CardType";
    private static final String UPDATE_QUERY
            = "UPDATE CardType SET name = ?, limit = ?, multicurrency = ?, exchangeFeeRate = ?, creditFeeRate = ? WHERE idCardType = ?";
    private static final String DELETE_QUERY = "DELETE FROM CardType WHERE idCardType = ?";

    private CardTypeDaoImpl() {
    }

    @Override
    public Long create(CardType data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setLong(2, data.getLimit());
            preparedStatement.setBoolean(3, data.isMulticurrency());
            preparedStatement.setFloat(4, data.getExchangeFeeRate());
            preparedStatement.setFloat(5, data.getCreditFeeRate());

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
    public CardType getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        CardType result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = new CardType(rs.getString(2), rs.getLong(3), rs.getBoolean(4), rs.getFloat(5), rs.getFloat(6));
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
    public List<CardType> getAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        List<CardType> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GETALL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CardType temp = new CardType(rs.getString(2), rs.getLong(3), rs.getBoolean(4), rs.getFloat(5), rs.getFloat(6));
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
    public Long updateById(Long id, CardType data) {
        Connection connection = CONNECTION_POOL.getConnection();
        if (connection == null)
            return null;

        Long rows;

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, data.getName());
            preparedStatement.setLong(2, data.getLimit());
            preparedStatement.setBoolean(3, data.isMulticurrency());
            preparedStatement.setFloat(4, data.getExchangeFeeRate());
            preparedStatement.setFloat(5, data.getCreditFeeRate());
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
    public Long update(CardType data) {
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
