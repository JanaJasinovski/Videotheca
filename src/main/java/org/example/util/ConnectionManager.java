package org.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionManager {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(PropertiesUtil.get("db.url"));
        config.setUsername(PropertiesUtil.get("db.username"));
        config.setPassword(PropertiesUtil.get("db.password"));
        config.setDriverClassName(PropertiesUtil.get("db.driver"));
        config.setMaximumPoolSize(Integer.parseInt(PropertiesUtil.get("db.pool.size", "10")));

        dataSource = new HikariDataSource(config);
    }

    private ConnectionManager() {
    }

    public static Connection get() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}