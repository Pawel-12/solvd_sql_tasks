package com.solvd.laba.block2.bankhierarchy;

import com.solvd.laba.block2.bankhierarchy.persistance.impl.CardTypeDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.CardTypeServiceImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.ClientsServiceImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.ConsultantsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ConnectionPool dbPool = ConnectionPool.createInstance(5);
        CardTypeServiceImpl cardTypeService = CardTypeServiceImpl.getInstance(CardTypeDaoImpl.INSTANCE);
        ClientsServiceImpl clientsService = ClientsServiceImpl.getInstance(ClientsDaoImpl.INSTANCE);
        ConsultantsServiceImpl consultantsService = ConsultantsServiceImpl.getInstance(ConsultantsDaoImpl.INSTANCE);


        LOGGER.info("CardTypes : \n");
        for (var ct : cardTypeService.getAll())
            LOGGER.info(ct);

        LOGGER.info("Clients : \n");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        LOGGER.info("Consultants : \n");
        for (var con : consultantsService.getAll()) {
            LOGGER.info(con);
        }

        LOGGER.info("Consultants for clientId = 1 : \n");
        for (var cl : consultantsService.getClients(1L))
            LOGGER.info(cl);

        LOGGER.info("Clients for consultantId = 3 : \n");
        for (var con : clientsService.getConsultants(3L))
            LOGGER.info(con);

        var client1 = clientsService.getById(9L);
        clientsService.deleteById(9L);

        LOGGER.info("Clients : \n");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        //clientsService.create(client1);

        LOGGER.info("Clients : \n");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        client1 = clientsService.getById(19L);
        client1.setName("Mary");

        clientsService.update(client1);

        LOGGER.info("Clients : \n");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }
    }
}
