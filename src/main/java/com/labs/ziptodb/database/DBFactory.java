package com.labs.ziptodb.database;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ConfigurationProperties(prefix = "datasources")
public class DBFactory {
    private static List<Datasource> databaseData;

    private static Map<String, ConnectionAndCounter> connections = new HashMap<>();

    public static Object getConnection(String name) throws SQLException {
        if(connections.containsKey(name)) {
            var con = connections.get(name);
            con.addUse();
            return con.getConnection();
        }
        Connection connection = createConnection(name);
        connections.put(name, new ConnectionAndCounter(connection, new AtomicInteger(1)));
        return connection;
    }

    private static Connection createConnection(String name) throws SQLException {
        for(var dataource : databaseData){
            if(name.equals(dataource.name())){
                Properties properties = new Properties();
                properties.put("user", dataource.user());
                properties.put("password", dataource.password());
                return DriverManager.getConnection(dataource.url(), properties);
            }
        }
        throw new SQLException(
                String.format(
                        "database with name - %s not found! Try to check properties", name
                ));
    }

    public static void closeConnection(String name){
        try{
            var con = connections.get(name);
            if(con.minusUse() <= 0){
                connections.remove(name);
            }
        } catch (NullPointerException e){
            throw new NullPointerException("this connection already close!");
        }
    }

}