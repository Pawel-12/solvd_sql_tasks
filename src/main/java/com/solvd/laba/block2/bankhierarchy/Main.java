package com.solvd.laba.block2.bankhierarchy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static {
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml");
    }

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        ConnectionPool dbPool = ConnectionPool.createInstance(5);

    }
}
