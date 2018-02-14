package dao;

import java.sql.Connection;

public abstract class AbstractDAO<T> {

    private final Connection connection;
    private final String table;

    public AbstractDAO(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    public Connection getConnection() {
        return connection;
    }

    public void init() {

    }
}
