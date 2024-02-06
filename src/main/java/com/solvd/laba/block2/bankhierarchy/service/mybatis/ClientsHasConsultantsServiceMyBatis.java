package com.solvd.laba.block2.bankhierarchy.service.mybatis;

import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsHasConsultantsDao;
import com.solvd.laba.block2.bankhierarchy.service.ClientsHasConsultantsService;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ClientsHasConsultantsServiceMyBatis implements ClientsHasConsultantsService {
    private final ClientsHasConsultantsDao clientsHasConsultantsDao;
    private static volatile com.solvd.laba.block2.bankhierarchy.service.mybatis.ClientsHasConsultantsServiceMyBatis thisService = null;

    private ClientsHasConsultantsServiceMyBatis(ClientsHasConsultantsDao clientsHasConsultantsDao) {
        this.clientsHasConsultantsDao = clientsHasConsultantsDao;
    }

    public static synchronized com.solvd.laba.block2.bankhierarchy.service.mybatis.ClientsHasConsultantsServiceMyBatis getInstance(ClientsHasConsultantsDao clientsHasConsultantsDao) {
        if (thisService == null)
            thisService = new com.solvd.laba.block2.bankhierarchy.service.mybatis.ClientsHasConsultantsServiceMyBatis(clientsHasConsultantsDao);

        return thisService;
    }

    @Override
    public Long create(Pair<Client, Consultant> data) {
        return clientsHasConsultantsDao.create(data);
    }

    @Override
    public Long create(Long clientId, Long consultantId) {
        return clientsHasConsultantsDao.create(clientId, consultantId);
    }

    @Override
    public List<Long> getConsultantsForId(Long id) {
        return clientsHasConsultantsDao.getConsultantsForId(id);
    }

    @Override
    public List<Long> getClientsForId(Long id) {
        return clientsHasConsultantsDao.getClientsForId(id);
    }

    @Override
    public List<Pair<Long, Long>> getAll() {
        return clientsHasConsultantsDao.getAll();
    }

    @Override
    public Long delete(Pair<Client, Consultant> data) {
        return clientsHasConsultantsDao.delete(data);
    }

    @Override
    public Long delete(Long clientId, Long consultantId) {
        return clientsHasConsultantsDao.delete(clientId, consultantId);
    }
}
