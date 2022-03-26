package cz.lunari.lunarimarket.handlers;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigHandler {

    private static Plugin instance = LunariMarket.getInstance();

    /* Initialize Config file */
    public static void initConfig()
    {
        File configFile = new File(instance.getDataFolder(), "config.yml");

        if(!configFile.exists()){ instance.getConfig().options().copyDefaults(); }
        instance.saveDefaultConfig();

        if(getConfigString("shopBlock") == null){ setConfigString("shopBlock", "minecraft:barrel");}
    }

    /* Save Config file */
    public static void saveConfig(){ instance.saveConfig(); }

    /* Basic Config getters */
    public static String getConfigString(String key){ return instance.getConfig().getString(key); }
    public static Double getConfigNumber(String key){ return instance.getConfig().getDouble(key); }
    public static Boolean getConfigBool(String key){ return instance.getConfig().getBoolean(key); }

    /* Basic Config setters */
    public static void setConfigString(String key, String value){ instance.getConfig().set(key,value); }
}
