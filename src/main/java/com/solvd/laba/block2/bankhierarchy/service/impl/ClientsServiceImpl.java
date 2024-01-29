package com.solvd.laba.block2.bankhierarchy.service.impl;

import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ClientsDao;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsHasConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.service.ClientsService;

import java.util.List;
import java.util.stream.Collectors;

public class ClientsServiceImpl implements ClientsService {
    private final ClientsDao clientsDao;
    private static volatile ClientsServiceImpl thisService = null;
    private static ClientsHasConsultantsServiceImpl clientsHasConsultantsService
            = ClientsHasConsultantsServiceImpl.getInstance(ClientsHasConsultantsDaoImpl.INSTANCE);
    private static ConsultantsServiceImpl consultantsService
            = ConsultantsServiceImpl.getInstance(ConsultantsDaoImpl.INSTANCE);

    private ClientsServiceImpl(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }

    public static synchronized ClientsServiceImpl getInstance(ClientsDao clientsDao) {
        if (thisService == null)
            thisService = new ClientsServiceImpl(clientsDao);

        return thisService;
    }

    @Override
    public Long create(Client data) {
        return clientsDao.create(data);
    }

    @Override
    public Client getById(Long id) {
        return clientsDao.getById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientsDao.getAll();
    }

    @Override
    public Long updateById(Long id, Client data) {
        return clientsDao.updateById(id, data);
    }

    @Override
    public Long update(Client data) {
        return clientsDao.update(data);
    }

    @Override
    public Long deleteById(Long id) {
        return clientsDao.deleteById(id);
    }

    public List<Consultant> getConsultants(Long id) {
        var consultantsIds = clientsHasConsultantsService.getConsultantsForId(id);

        if (consultantsIds.isEmpty())
            return null;

        return consultantsIds.stream().map(conId -> consultantsService.getById(conId)).collect(Collectors.toList());
    }

    public List<Consultant> getConsultants(Client client) {
        return getConsultants(client.getId());
    }
}
