package cz.lunari.lunarimarket.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

class DatabaseTables {

    PreparedStatement[] statements = new PreparedStatement[]{};

    protected void createTables(Connection connection)
    {
        try
        {
            statements = new PreparedStatement[] {
                connection.prepareStatement("CREATE TABLE IF NOT EXISTS Markets(id INTEGER PRIMARY KEY autoincrement, location varchar(64), owner varchar(64));"),
            };
        } catch(Exception e) { e.printStackTrace(); }
    }

}
