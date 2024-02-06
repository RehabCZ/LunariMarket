package cz.lunari.lunarimarket.objects;

import cz.lunari.lunarimarket.LunariMarket;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class YamlObject extends AbstractObject {

    private final File file;
    protected FileConfiguration config;

    public YamlObject(LunariMarket plugin) {
        super(plugin);

        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists()) {
            try {
                pluginDirectory.mkdirs();
            } catch (SecurityException e) {
                LunariMarket.getInstance().getLogger().severe(e.getMessage());
            }
        }

        this.file = new File(pluginDirectory, fileName() + ".yml");

        initConfig();
    }

    protected abstract String fileName();

    protected abstract void loadDefaults();

    private void initConfig() {
        if (file.exists()){
            loadFile();
            return;
        }

        createFile();
        loadFile();
        loadDefaults();
        saveFile();
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

    private void createFile(){
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }
    }

    private void loadFile() {
        this.config = plugin.getConfig();
    }

    private void saveFile() {
        try {
            config.save(file);
        }
        catch (IOException e){
            LunariMarket.getInstance().getLogger().severe(e.getMessage());
        }
    }

    public void reload() {
        loadFile();
    }

}
