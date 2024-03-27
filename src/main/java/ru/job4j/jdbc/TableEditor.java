package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

import static java.sql.DriverManager.getConnection;


public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws IOException, SQLException {
        this.properties = properties;
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.properties.load(in);
        }
        initConnection();
    }

    private void initConnection() throws SQLException {
        connection = getConnection(this.properties.getProperty("jdbc.url"),
                                    this.properties.getProperty("jdbc.username"),
                                    this.properties.getProperty("jdbc.password"));
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table %s();", tableName);
        statementExecute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s", tableName);
        statementExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("alter table %s add %s %s", tableName, columnName, type);
        statementExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        statementExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
            String sql = String.format("alter table %s rename column %s to %s",
                                        tableName,
                                        columnName,
                                        newColumnName);
            statementExecute(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void statementExecute(String sql) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor table = new TableEditor(new Properties());
        table.createTable("test");
        System.out.println(table.getTableScheme("test"));
        table.addColumn("test", "coltest", "varchar(60)");
        System.out.println(table.getTableScheme("test"));
        table.renameColumn("test", "coltest", "col");
        System.out.println(table.getTableScheme("test"));
        table.dropColumn("test", "col");
        System.out.println(table.getTableScheme("test"));
        table.dropTable("test");
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
