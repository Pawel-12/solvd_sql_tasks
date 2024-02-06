package com.solvd.laba.block2.bankhierarchy.service.mybatis;

import com.solvd.laba.block2.bankhierarchy.domain.CardType;
import com.solvd.laba.block2.bankhierarchy.persistance.CardTypeDao;
import com.solvd.laba.block2.bankhierarchy.service.CardTypeService;

import java.util.List;

public class CardTypeServiceMyBatis implements CardTypeService {
    private final CardTypeDao cardTypeDao;
    private static volatile com.solvd.laba.block2.bankhierarchy.service.mybatis.CardTypeServiceMyBatis thisService = null;

    private CardTypeServiceMyBatis(CardTypeDao cardTypeDao) {
        this.cardTypeDao = cardTypeDao;
    }

    public static synchronized com.solvd.laba.block2.bankhierarchy.service.mybatis.CardTypeServiceMyBatis getInstance(CardTypeDao cardTypeDao) {
        if (thisService == null)
            thisService = new com.solvd.laba.block2.bankhierarchy.service.mybatis.CardTypeServiceMyBatis(cardTypeDao);

        return thisService;
    }

    @Override
    public Long create(CardType data) {
        return cardTypeDao.create(data);
    }

    @Override
    public CardType getById(Long id) {
        return cardTypeDao.getById(id);
    }

    @Override
    public List<CardType> getAll() {
        return cardTypeDao.getAll();
    }

    @Override
    public Long updateById(Long id, CardType data) {
        return cardTypeDao.updateById(id, data);
    }

    @Override
    public Long update(CardType data) {
        return cardTypeDao.update(data);
    }

    @Override
    public Long deleteById(Long id) {
        return cardTypeDao.deleteById(id);
    }
}

