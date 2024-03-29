package com.solvd.laba.block2.bankhierarchy;

import com.solvd.laba.block2.bankhierarchy.persistance.mybatis.CardTypeMyBatis;
import com.solvd.laba.block2.bankhierarchy.persistance.mybatis.ClientsHasConsultantsMyBatis;
import com.solvd.laba.block2.bankhierarchy.persistance.mybatis.ClientsMyBatis;
import com.solvd.laba.block2.bankhierarchy.persistance.mybatis.ConsultantsMyBatis;
import com.solvd.laba.block2.bankhierarchy.service.mybatis.CardTypeServiceMyBatis;
import com.solvd.laba.block2.bankhierarchy.service.mybatis.ClientsHasConsultantsServiceMyBatis;
import com.solvd.laba.block2.bankhierarchy.service.mybatis.ClientsServiceMyBatis;
import com.solvd.laba.block2.bankhierarchy.service.mybatis.ConsultantsServiceMyBatis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
//        ConnectionPool dbPool = ConnectionPool.createInstance(5);
//        CardTypeServiceImpl cardTypeService = ServiceFactory.createCardTypeService();
//        ClientsServiceImpl clientsService = ServiceFactory.createClientsService();
//        ConsultantsServiceImpl consultantsService = ServiceFactory.createConsultantsService();

        CardTypeServiceMyBatis cardTypeService = CardTypeServiceMyBatis.getInstance(CardTypeMyBatis.INSTANCE);
        ClientsServiceMyBatis clientsService = ClientsServiceMyBatis.getInstance(ClientsMyBatis.INSTANCE);
        ConsultantsServiceMyBatis consultantsService = ConsultantsServiceMyBatis.getInstance(ConsultantsMyBatis.INSTANCE);
        ClientsHasConsultantsServiceMyBatis clientsHasConsultantsService
                = ClientsHasConsultantsServiceMyBatis.getInstance(ClientsHasConsultantsMyBatis.INSTANCE);


        LOGGER.info("\n\nclientsToConsultants : ");
        for (var ct : clientsHasConsultantsService.getAll())
            LOGGER.info(ct);


        LOGGER.info("\n\nCardTypes : ");
        for (var ct : cardTypeService.getAll())
            LOGGER.info(ct);

        LOGGER.info("\n\nClients : ");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        LOGGER.info("\n\nConsultants : ");
        for (var con : consultantsService.getAll()) {
            LOGGER.info(con);
        }

        LOGGER.info("\n\nConsultants for clientId = 1 : ");
        for (var cl : clientsService.getConsultants(1L))
            LOGGER.info(cl);

        LOGGER.info("\n\nClients for consultantId = 2 : ");
        for (var con : consultantsService.getClients(1L))
            LOGGER.info(con);

        var client1 = clientsService.getById(10L);
        clientsService.deleteById(43L);

        LOGGER.info("\n\nClients : ");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        clientsService.create(client1);

        LOGGER.info("\n\nClients : ");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }

        client1 = clientsService.getById(21L);
        client1.setName("Mary");

        clientsService.update(client1);

        LOGGER.info("\n\nClients : ");
        for (var cl : clientsService.getAll()) {
            LOGGER.info(cl);
        }
    }
}
