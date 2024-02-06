package com.solvd.laba.block2.bankhierarchy;

import com.solvd.laba.block2.bankhierarchy.persistance.CardTypeDao;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsDao;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsHasConsultantsDao;
import com.solvd.laba.block2.bankhierarchy.persistance.ConsultantsDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream stream = Resources.getResourceAsStream("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(stream);

            if (!sqlSessionFactory.getConfiguration().hasMapper(CardTypeDao.class))
                sqlSessionFactory.getConfiguration().addMapper(CardTypeDao.class);

            if (!sqlSessionFactory.getConfiguration().hasMapper(ClientsDao.class))
                sqlSessionFactory.getConfiguration().addMapper(ClientsDao.class);

            if (!sqlSessionFactory.getConfiguration().hasMapper(ConsultantsDao.class))
                sqlSessionFactory.getConfiguration().addMapper(ConsultantsDao.class);

            if (!sqlSessionFactory.getConfiguration().hasMapper(ClientsHasConsultantsDao.class))
                sqlSessionFactory.getConfiguration().addMapper(ClientsHasConsultantsDao.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}