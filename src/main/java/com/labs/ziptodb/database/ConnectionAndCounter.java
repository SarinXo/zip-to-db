package com.labs.ziptodb.database;


import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionAndCounter {
    private Object connection;
    private AtomicInteger countOfUsages;

    public ConnectionAndCounter(Object connection, AtomicInteger countOfUsages) {
        this.connection = connection;
        this.countOfUsages = countOfUsages;
    }

    public int addUse(){
        return countOfUsages.incrementAndGet();
    }

    public int minusUse(){
        return countOfUsages.decrementAndGet();
    }

    public Object getConnection() {
        return connection;
    }

    public AtomicInteger getCountOfUsages() {
        return countOfUsages;
    }

    public void setConnection(Object connection) {
        this.connection = connection;
    }

    public void setCountOfUsages(AtomicInteger countOfUsages) {
        this.countOfUsages = countOfUsages;
    }
}
