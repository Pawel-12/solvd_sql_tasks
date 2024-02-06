package com.solvd.laba.block2.bankhierarchy.util;

import com.solvd.laba.block2.bankhierarchy.persistance.impl.CardTypeDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ClientsHasConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.persistance.impl.ConsultantsDaoImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.CardTypeServiceImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.ClientsHasConsultantsServiceImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.ClientsServiceImpl;
import com.solvd.laba.block2.bankhierarchy.service.impl.ConsultantsServiceImpl;

public class ServiceFactory {
    public static CardTypeServiceImpl createCardTypeService() {
        return CardTypeServiceImpl.getInstance(CardTypeDaoImpl.INSTANCE);
    }

    public static ClientsServiceImpl createClientsService() {
        return ClientsServiceImpl.getInstance(ClientsDaoImpl.INSTANCE);
    }

    public static ConsultantsServiceImpl createConsultantsService() {
        return ConsultantsServiceImpl.getInstance(ConsultantsDaoImpl.INSTANCE);
    }

    public static ClientsHasConsultantsServiceImpl createClientsHasConsultantsService() {
        return ClientsHasConsultantsServiceImpl.getInstance(ClientsHasConsultantsDaoImpl.INSTANCE);
    }
}
