package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.config.Config;
import cz.lunari.lunarimarket.utils.ChatUtils;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    private static Connection connection;

    private static final String DATABASE_HOST = Config.getConfigString("database.dbHost");
    private static final Integer DATABASE_PORT = Config.getConfigNumber("database.dbPort");
    private static final String DATABASE_NAME = Config.getConfigString("database.dbName");
    private static final String USERNAME = Config.getConfigString("database.username");
    private static final String PASSWORD = Config.getConfigString("database.password");

    public void initializeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            DATABASE_HOST + ':' + DATABASE_PORT + '/' + DATABASE_NAME,
                    USERNAME, PASSWORD
            );

            if (!(tableExist("markets")) || !(tableExist("vaults"))) {
                ChatUtils.logConsole("&cSQL Tables not found generating them!");
                createTables();
            }

            ChatUtils.logConsole("&2MySQL Connection successfully initialized");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(0))
                this.initializeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getSQLConn() {
        this.checkConnection();
        return connection;
    }

    private boolean tableExist(String tableName) {
        boolean tableExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
                if (table != null && table.equals(tableName)) {
                    tableExists = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableExists;
    }

    private void createTables() {
        ArrayList<PreparedStatement> statement = new ArrayList<>();
        try {
            statement.add(connection.prepareStatement("CREATE TABLE IF NOT EXISTS `markets` (`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the market',`market_owner` VARCHAR(16) NOT NULL COMMENT 'Owner of the market',`location_x` FLOAT NOT NULL COMMENT 'X location of the market',`location_y` FLOAT NOT NULL COMMENT 'Y location of the market',`location_z` FLOAT NOT NULL COMMENT 'Z location of the market',`sell_item` JSON NOT NULL COMMENT 'Item to sell in market',`price_item` JSON NOT NULL COMMENT 'Price item of market',`stock_amount` INT NOT NULL COMMENT 'Amount of items in stock',`date_created` DATE NOT NULL COMMENT 'Date the market was created',PRIMARY KEY (`id`));"));
            statement.add(connection.prepareStatement("CREATE TABLE IF NOT EXISTS `vaults` (`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the vault',`vault_owner` VARCHAR(16) NOT NULL COMMENT 'Vault owner',`location_x` FLOAT NOT NULL COMMENT 'X location of the vault',`location_y` FLOAT NOT NULL COMMENT 'Y location of the vault',`location_z` FLOAT NOT NULL COMMENT 'Z location of the vault',`items` JSON NOT NULL COMMENT 'Items in vault',PRIMARY KEY (`id`));"));
            for (int i = 0; i < statement.size(); i++) {
                statement.get(i).execute();
                statement.get(i).close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
