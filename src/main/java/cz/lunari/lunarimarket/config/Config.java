package cz.lunari.lunarimarket.config;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class Config {

    private static final Plugin instance = LunariMarket.getInstance();

    /* Initialize Config file */
    public void initConfig()
    {
        File configFile = new File(instance.getDataFolder(), "config.yml");
        if(!configFile.exists()){ instance.getConfig().options().copyDefaults(); }
        instance.saveDefaultConfig();
    }

    /* Basic Config getters */
    public static String getConfigString(String key){ return instance.getConfig().getString(key); }
    public static Integer getConfigNumber(String key){ return instance.getConfig().getInt(key); }
    public static Boolean getConfigBool(String key){ return instance.getConfig().getBoolean(key); }
    public static List<String> getConfigStringList(String key){ return instance.getConfig().getStringList(key); }

    /* Basic Config setters */
    public static void setConfigString(String key, String value){ instance.getConfig().set(key,value); }
}
