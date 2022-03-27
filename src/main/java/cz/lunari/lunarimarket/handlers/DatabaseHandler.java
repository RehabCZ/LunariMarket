package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.LunariMarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static Connection connection;

    private static final String DATABASE_HOST = ConfigHandler.getConfigString("database.dbHost");
    private static final Integer DATABASE_PORT = ConfigHandler.getConfigNumber("database.dbPort");
    private static final String DATABASE_NAME = ConfigHandler.getConfigString("database.dbName");
    private static final String USERNAME = ConfigHandler.getConfigString("database.username");
    private static final String PASSWORD = ConfigHandler.getConfigString("database.password");

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

    public static void checkConnection()
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
