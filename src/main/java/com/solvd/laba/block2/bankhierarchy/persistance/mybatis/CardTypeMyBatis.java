package com.solvd.laba.block2.bankhierarchy.persistance.mybatis;

import com.solvd.laba.block2.bankhierarchy.MyBatisConfig;
import com.solvd.laba.block2.bankhierarchy.domain.CardType;
import com.solvd.laba.block2.bankhierarchy.persistance.CardTypeDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public enum CardTypeMyBatis implements CardTypeDao {
    INSTANCE;

    private CardTypeMyBatis() {
    }

    @Override
    public Long create(CardType data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardTypeDao cardTypeDao = sqlSession.getMapper(CardTypeDao.class);
            keys = cardTypeDao.create(data);
        }
        return keys;
    }

    @Override
    public CardType getById(Long id) {
        CardType result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardTypeDao cardTypeDao = sqlSession.getMapper(CardTypeDao.class);
            result = cardTypeDao.getById(id);
        }
        return result;
    }

    @Override
    public List<CardType> getAll() {
        List<CardType> result = null;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardTypeDao cardTypeDao = sqlSession.getMapper(CardTypeDao.class);
            result = cardTypeDao.getAll();
        }
        return result;
    }

    @Override
    public Long updateById(Long id, CardType data) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardTypeDao cardTypeDao = sqlSession.getMapper(CardTypeDao.class);
            keys = cardTypeDao.updateById(id, data);
        }
        return keys;
    }

    @Override
    public Long update(CardType data) {
        return updateById(data.getId(), data);
    }

    @Override
    public Long deleteById(Long id) {
        long keys = 0;
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CardTypeDao cardTypeDao = sqlSession.getMapper(CardTypeDao.class);
            keys = cardTypeDao.deleteById(id);
        }
        return keys;
    }
}
