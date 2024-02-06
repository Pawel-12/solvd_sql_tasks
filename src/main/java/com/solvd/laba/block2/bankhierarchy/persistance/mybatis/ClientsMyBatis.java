package com.solvd.laba.block2.bankhierarchy.persistance.mybatis;

import com.solvd.laba.block2.bankhierarchy.MyBatisConfig;
import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public enum ClientsMyBatis implements ClientsDao {
    INSTANCE;

    private ClientsMyBatis() {
    }

    @Override
    public Long create(Client data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsDao clientsDao = sqlSession.getMapper(ClientsDao.class);
            keys = clientsDao.create(data);
        }
        return keys;
    }

    @Override
    public Client getById(Long id) {
        Client result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsDao clientsDao = sqlSession.getMapper(ClientsDao.class);
            result = clientsDao.getById(id);
        }
        return result;
    }

    @Override
    public List<Client> getAll() {
        List<Client> result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsDao clientsDao = sqlSession.getMapper(ClientsDao.class);
            result = clientsDao.getAll();
        }
        return result;
    }

    @Override
    public Long updateById(Long id, Client data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsDao clientsDao = sqlSession.getMapper(ClientsDao.class);
            keys = clientsDao.updateById(id, data);
        }
        return keys;
    }

    @Override
    public Long update(Client data) {
        return updateById(data.getId(), data);
    }

    @Override
    public Long deleteById(Long id) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsDao clientsDao = sqlSession.getMapper(ClientsDao.class);
            keys = clientsDao.deleteById(id);
        }
        return keys;
    }
}

