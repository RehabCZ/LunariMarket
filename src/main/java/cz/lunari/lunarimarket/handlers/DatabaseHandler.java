package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static Connection connection;

    private static final String DATABASE_HOST = Config.getConfigString("database.dbHost");
    private static final Integer DATABASE_PORT = Config.getConfigNumber("database.dbPort");
    private static final String DATABASE_NAME = Config.getConfigString("database.dbName");
    private static final String USERNAME = Config.getConfigString("database.username");
    private static final String PASSWORD = Config.getConfigString("database.password");

    public static void initializeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
               DATABASE_HOST + ':' + DATABASE_PORT + '/' + DATABASE_NAME,
               USERNAME, PASSWORD
            );
            LunariMarket.sendConsole("MySQL Connection successfully initialized");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection()
    {
        if(connection != null)
        {
            try{ connection.close(); }
            catch (SQLException e){ e.printStackTrace(); }
        }
    }

    private static void checkConnection()
    {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(0))
                initializeConnection();
        } catch (SQLException e) { e.printStackTrace(); }
    }


    public static Connection getSQLConn()
    {
        checkConnection();
        return connection;
    }

}
