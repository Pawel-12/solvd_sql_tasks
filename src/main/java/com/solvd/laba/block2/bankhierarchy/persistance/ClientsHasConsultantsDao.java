package com.solvd.laba.block2.bankhierarchy.persistance;

import com.solvd.laba.block2.bankhierarchy.ConnectionPool;
import com.solvd.laba.block2.bankhierarchy.domain.Client;
import com.solvd.laba.block2.bankhierarchy.domain.Consultant;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ClientsHasConsultantsDao {
    public final static ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public Long create(Pair<Client, Consultant> data);

    public Long create(Long clientId, Long consultantId);

    public List<Long> getConsultantsForId(Long id);

    public List<Long> getClientsForId(Long id);

    public List<Pair<Long, Long>> getAll();

    public Long delete(Pair<Client, Consultant> data);

    public Long delete(Long clientId, Long consultantId);
}
