package com.labs.ziptodb.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class ConnectionAndCounter {
    private Object connection;
    private AtomicInteger countOfUsages;

    public int addUse(){
        return countOfUsages.incrementAndGet();
    }

    public int minusUse(){
        return countOfUsages.decrementAndGet();
    }
}
