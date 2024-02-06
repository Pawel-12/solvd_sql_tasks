package com.solvd.laba.block2.bankhierarchy.persistance.mybatis;

import com.solvd.laba.block2.bankhierarchy.MyBatisConfig;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ConsultantsDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public enum ConsultantsMyBatis implements ConsultantsDao {
    INSTANCE;

    private ConsultantsMyBatis() {
    }

    @Override
    public Long create(Consultant data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ConsultantsDao consultantsDao = sqlSession.getMapper(ConsultantsDao.class);
            keys = consultantsDao.create(data);
        }
        return keys;
    }

    @Override
    public Consultant getById(Long id) {
        Consultant result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ConsultantsDao consultantsDao = sqlSession.getMapper(ConsultantsDao.class);
            result = consultantsDao.getById(id);
        }
        return result;
    }

    @Override
    public List<Consultant> getAll() {
        List<Consultant> result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ConsultantsDao consultantsDao = sqlSession.getMapper(ConsultantsDao.class);
            result = consultantsDao.getAll();
        }
        return result;
    }

    @Override
    public Long updateById(Long id, Consultant data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ConsultantsDao consultantsDao = sqlSession.getMapper(ConsultantsDao.class);
            keys = consultantsDao.updateById(id, data);
        }
        return keys;
    }

    @Override
    public Long update(Consultant data) {
        return updateById(data.getId(), data);
    }

    @Override
    public Long deleteById(Long id) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ConsultantsDao consultantsDao = sqlSession.getMapper(ConsultantsDao.class);
            keys = consultantsDao.deleteById(id);
        }
        return keys;
    }
}
