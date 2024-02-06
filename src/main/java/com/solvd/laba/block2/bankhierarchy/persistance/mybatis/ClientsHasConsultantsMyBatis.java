package com.solvd.laba.block2.bankhierarchy.persistance.mybatis;

import com.solvd.laba.block2.bankhierarchy.MyBatisConfig;
import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsHasConsultantsDao;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public enum ClientsHasConsultantsMyBatis implements ClientsHasConsultantsDao {
    INSTANCE;

    private ClientsHasConsultantsMyBatis() {
    }

    @Override
    public Long create(Pair<Client, Consultant> data) {
        return create(data.getLeft().getId(), data.getRight().getId());
    }

    @Override
    public Long create(Long clientId, Long consultantId) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsHasConsultantsDao clientsHasConsultantsDao = sqlSession.getMapper(ClientsHasConsultantsDao.class);
            keys = clientsHasConsultantsDao.create(clientId, consultantId);
        }
        return keys;
    }

    @Override
    public List<Long> getConsultantsForId(Long id) {
        List<Long> result = new ArrayList<>();
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsHasConsultantsDao clientsHasConsultantsDao = sqlSession.getMapper(ClientsHasConsultantsDao.class);
            result = clientsHasConsultantsDao.getConsultantsForId(id);
        }
        return result;
    }

    @Override
    public List<Long> getClientsForId(Long id) {
        List<Long> result = new ArrayList<>();
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsHasConsultantsDao clientsHasConsultantsDao = sqlSession.getMapper(ClientsHasConsultantsDao.class);
            result = clientsHasConsultantsDao.getClientsForId(id);
        }
        return result;
    }

    @Override
    public List<Pair<Long, Long>> getAll() {
        List<Pair<Long, Long>> result = new ArrayList<>();
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsHasConsultantsDao clientsHasConsultantsDao = sqlSession.getMapper(ClientsHasConsultantsDao.class);
            result = clientsHasConsultantsDao.getAll();
        }
        return result;
    }

    @Override
    public Long delete(Pair<Client, Consultant> data) {
        return delete(data.getLeft().getId(), data.getRight().getId());
    }

    @Override
    public Long delete(Long clientId, Long consultantId) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientsHasConsultantsDao clientsHasConsultantsDao = sqlSession.getMapper(ClientsHasConsultantsDao.class);
            keys = clientsHasConsultantsDao.delete(clientId, consultantId);
        }
        return keys;
    }
}
