package cz.lunari.lunarimarket.managers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cz.lunari.lunarimarket.LunariMarket;

import java.io.*;

public abstract class JsonManager extends AbstractManager {

    private final Gson gson = new Gson();
    private final Gson gsonPretty = new Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create();

    private final File file;

    protected JsonObject data;

    protected JsonManager(LunariMarket plugin) {
        super(plugin);

        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists()) {
            pluginDirectory.mkdirs();
        }

        this.file = new File(pluginDirectory, fileName());

        initConfig();
    }

    protected abstract String fileName();
    protected abstract void loadDefaults();

    private void initConfig() {
        if (file.exists()){
            load();

            return;
        }

        loadDefaults();
        save();
    }

    private JsonObject getObject(String obj){
        return data.get(obj).getAsJsonObject();
    }

    public String getString(String obj, String key) {
        return getObject(obj).get(key).getAsString();
    }

    public int getInteger(String obj, String key) {
        return getObject(obj).get(key).getAsInt();
    }

    public boolean getBoolean(String obj, String key) {
        return getObject(obj).get(key).getAsBoolean();
    }

    private void load() {
        try (FileReader reader = new FileReader(file)) {
            data = gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try (FileWriter writer = new FileWriter(file)) {
            gsonPretty.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
