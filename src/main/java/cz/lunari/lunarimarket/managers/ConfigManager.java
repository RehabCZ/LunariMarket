package cz.lunari.lunarimarket.managers;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager extends AbstractManager{

    private final File file;
    private FileConfiguration config;

    public ConfigManager(LunariMarket plugin) {
        super(plugin);

        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists()) {
            pluginDirectory.mkdirs();
        }

        this.file = new File(pluginDirectory, "config.yml");

        initConfig();
    }

    private void initConfig() {
        if (file.exists()){
            load();

            return;
        }

        createFile();
        loadDefaults();
    }

    public String getString(String key){
        return config.getString(key);
    }

    public Integer getInteger(String key){
        return config.getInt(key);
    }

    public Boolean getBoolean(String key){
        return config.getBoolean(key);
    }

    private void loadDefaults(){
        load();

        ConfigurationSection integration = config.createSection("integration");
        integration.set("WorldGuard", true);

        ConfigurationSection database = config.createSection("database");
        database.set("dbHost", "localhost");
        database.set("dbPort", 3306);
        database.set("dbName", "lunarimarket");
        database.set("dbUser", "");
        database.set("dbPass", "");

        save();
    }

    private void createFile(){
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(){
        this.config = plugin.getConfig();
    }

    private void save(){
        try {
            config.save(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
