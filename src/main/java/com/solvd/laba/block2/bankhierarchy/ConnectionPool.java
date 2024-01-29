package com.solvd.laba.block2.bankhierarchy;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.solvd.laba.block2.bankhierarchy.Main.LOGGER;

public class ConnectionPool {
    private final static String DRIVER;
    private final static String URL;
    private final static String USER;
    private final static String PASSWORD;
    private volatile static ConnectionPool thisPool = null;
    private volatile BlockingQueue<Connection> activeConnections = new LinkedBlockingQueue<>();
    private volatile BlockingQueue<Connection> idleConnections = new LinkedBlockingQueue<>();
    private static int size;

    static {
        Properties databaseProps = new Properties();
        try {
            databaseProps.load((new FileInputStream("src/main/resources/database.properties")));

            DRIVER = databaseProps.getProperty("driver");
            URL = databaseProps.getProperty("url") + '/' + databaseProps.getProperty("database");
            USER = databaseProps.getProperty("user");
            PASSWORD = databaseProps.getProperty("password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool(int size) {
        ConnectionPool.size = size;
        LOGGER.info("ConnectionPool created, size = " + size + "\n");
    }

    public static synchronized ConnectionPool createInstance(int size) {
        if (thisPool == null)
            thisPool = new ConnectionPool(size);

        return thisPool;
    }

    public static ConnectionPool getInstance() {
        return thisPool;
    }

    public synchronized Connection getConnection() {
        if (activeConnections.size() < size) {
            if (!idleConnections.isEmpty()) {
                try {
                    Connection temp = idleConnections.take();
                    activeConnections.add(temp);
                    LOGGER.info("Idle connection " + temp + " reused\n");
                    return temp;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    Class.forName(DRIVER);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Connection temp = null;
                try {
                    temp = DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                activeConnections.add(temp);
                LOGGER.info("New connection " + temp + " created\n");
                return temp;
            }
        }
        LOGGER.info("No available connections!\n");
        return null;
    }

    public synchronized void releaseConnection(Connection con) {
        idleConnections.add(con);
        activeConnections.remove(con);
        LOGGER.info("Connection " + con + " released\n");
    }

    public static synchronized void reset() {
        thisPool = null;
    }
}
