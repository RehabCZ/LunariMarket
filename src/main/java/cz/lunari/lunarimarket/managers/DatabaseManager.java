package cz.lunari.lunarimarket.managers;

import com.google.common.collect.Lists;
import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.config.Configuration;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;

import java.sql.*;
import java.util.List;

public class DatabaseManager {

    private Connection connection;
    private final Configuration configuration = LunariMarket.getInstance().getConfiguration();

    public DatabaseManager() {
        initializeConnection();
    }

    public boolean initializeConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            configuration.getString("database.dbHost") + ":" + configuration.getInteger("database.dbPort").toString() + "/" + configuration.getString("database.dbName"),
                    configuration.getString("database.dbUser"), configuration.getString("database.dbPass")
            );

            if (!(tableExist("markets")) || !(tableExist("vaults"))) {
                ChatMessageUtils.logConsole("&cSQL Tables not found generating them!");

                createTables();
            }

            ChatMessageUtils.logConsole("&7Connection with &2MySQL database&7 was successfully initialized.");

            return true;
        } catch (SQLException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }

        return false;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LunariMarket.getInstance().getLogger().severe(e.getMessage());
            }
        }
    }

    private boolean checkConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return false;
            }

            return initializeConnection();
        } catch (SQLException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }
        return true;
    }

    private boolean tableExist(String tableName) {
        try (ResultSet rs = getConnection().getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");

                if (table != null && table.equals(tableName)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }

        return false;
    }

    private void createTables() {
        List<PreparedStatement> statement = Lists.newArrayList();

        try {
            statement.add(getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `markets` (`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the market',`market_owner` VARCHAR(16) NOT NULL COMMENT 'Owner of the market',`location_x` FLOAT NOT NULL COMMENT 'X location of the market',`location_y` FLOAT NOT NULL COMMENT 'Y location of the market',`location_z` FLOAT NOT NULL COMMENT 'Z location of the market',`sell_item` JSON NOT NULL COMMENT 'Item to sell in market',`price_item` JSON NOT NULL COMMENT 'Price item of market',`stock_amount` INT NOT NULL COMMENT 'Amount of items in stock',`date_created` DATE NOT NULL COMMENT 'Date the market was created',PRIMARY KEY (`id`));"));
            statement.add(getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `vaults` (`id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID of the vault',`vault_owner` VARCHAR(16) NOT NULL COMMENT 'Vault owner',`location_x` FLOAT NOT NULL COMMENT 'X location of the vault',`location_y` FLOAT NOT NULL COMMENT 'Y location of the vault',`location_z` FLOAT NOT NULL COMMENT 'Z location of the vault',`items` JSON NOT NULL COMMENT 'Items in vault',PRIMARY KEY (`id`));"));

            for (PreparedStatement preparedStatement : statement) {
                preparedStatement.execute();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }
    }

    public Connection getConnection() {
        if (!checkConnection()) {
            return connection;
        }
        return null;
    }
}
