package cz.lunari.lunarimarket.managers;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class YamlManager extends AbstractManager {

    private final File file;
    protected FileConfiguration config;

    public YamlManager(LunariMarket plugin) {
        super(plugin);

        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists()) {
            try {
                pluginDirectory.mkdirs();
            } catch (SecurityException e) {
                LunariMarket.getInstance().getLogger().severe(e.getMessage());
            }
        }

        this.file = new File(pluginDirectory, filename());

        initConfig();
    }

    private void initConfig() {
        if (file.exists()){
            load();

            return;
        }

        createFile();
        loadDefaults();
        save();
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


    protected abstract String filename();

    protected abstract void loadDefaults();

    private void createFile(){
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
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
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }
    }

}
