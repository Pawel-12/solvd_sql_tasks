package com.solvd.laba.block2.bankhierarchy.service.impl;

import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import com.solvd.laba.block2.bankhierarchy.persistance.ConsultantsDao;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsHasConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.service.ConsultantsService;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultantsServiceImpl implements ConsultantsService {
    private final ConsultantsDao consultantsDao;
    private static volatile ConsultantsServiceImpl thisService = null;

    private static ClientsHasConsultantsServiceImpl clientsHasConsultantsService
            = ClientsHasConsultantsServiceImpl.getInstance(ClientsHasConsultantsDaoImpl.INSTANCE);
    private static ClientsServiceImpl clientsService
            = ClientsServiceImpl.getInstance(ClientsDaoImpl.INSTANCE);

    private ConsultantsServiceImpl(ConsultantsDao consultantsDao) {
        this.consultantsDao = consultantsDao;
    }

    public static synchronized ConsultantsServiceImpl getInstance(ConsultantsDao consultantsDao) {
        if (thisService == null)
            thisService = new ConsultantsServiceImpl(consultantsDao);

        return thisService;
    }

    @Override
    public Long create(Consultant data) {
        return consultantsDao.create(data);
    }

    @Override
    public Consultant getById(Long id) {
        return consultantsDao.getById(id);
    }

    @Override
    public List<Consultant> getAll() {
        return consultantsDao.getAll();
    }

    @Override
    public Long updateById(Long id, Consultant data) {
        return consultantsDao.updateById(id, data);
    }

    @Override
    public Long update(Consultant data) {
        return consultantsDao.update(data);
    }

    @Override
    public Long deleteById(Long id) {
        return consultantsDao.deleteById(id);
    }

    public List<Client> getClients(Long id) {
        var clientsIds = clientsHasConsultantsService.getClientsForId(id);

        if (clientsIds.isEmpty())
            return null;

        return clientsIds.stream().map(cliId -> clientsService.getById(cliId)).collect(Collectors.toList());
    }

    public List<Client> getClients(Consultant consultant) {
        return getClients(consultant.getId());
    }
}
