package dev.nico85.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager {
    private Connection connection;
    private final String dbName;

    public SQLiteManager(String dbName) {
        this.dbName = dbName;
        try {
            File dataFolder = new File("plugins/FolignoDocuments");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File databaseFile = new File(dataFolder, dbName);
            String url = "jdbc:sqlite:" + databaseFile.getAbsolutePath();

            connection = DriverManager.getConnection(url);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS player_data (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "player_name TEXT NOT NULL," +
                            "age INT NOT NULL," +
                            "gender TEXT NOT NULL)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Resto del codice per ottenere la connessione, chiudere la connessione, ecc.

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

