package com.labs.ziptodb.database;

public record Datasource(
        String name,
        String url,
        String user,
        String password
) {}