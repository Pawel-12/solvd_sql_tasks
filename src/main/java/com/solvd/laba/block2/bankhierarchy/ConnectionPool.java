package com.solvd.laba.block2.bankhierarchy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.solvd.laba.block2.bankhierarchy.Main.LOGGER;

public class ConnectionPool {
    private volatile static ConnectionPool thisPool = null;
    private volatile BlockingQueue<Connection> activeConnections = new LinkedBlockingQueue<>();
    private volatile BlockingQueue<Connection> idleConnections = new LinkedBlockingQueue<>();
    private static int size;

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

    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
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
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection temp = DriverManager.getConnection("127.0.0.1:3306","root","root");
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
