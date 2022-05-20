package cz.lunari.lunarimarket.managers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cz.lunari.lunarimarket.LunariMarket;

import java.io.*;

public class ConfigManager extends AbstractManager {

    private final Gson gson = new Gson();
    private final Gson gsonPretty = new Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create();

    private final File file;

    private JsonObject data;

    public ConfigManager(LunariMarket plugin) {
        super(plugin);

        File pluginDirectory = plugin.getDataFolder();

        if (!pluginDirectory.exists()) {
            pluginDirectory.mkdirs();
        }

        this.file = new File(pluginDirectory, "config.json");

        initConfig();
    }

    private void initConfig() {
        if (file.exists()){
            load();

            return;
        }

        loadDefaults();
        save();
    }

    public static String getString(String key) {
        return "";
    }

    public static int getInteger(String key) {
        return 1;
    }

    public static boolean getBoolean(String key) {
        return false;
    }

    private void loadDefaults() {
        data = new JsonObject();

        JsonObject database = new JsonObject();
        database.addProperty("host", "");
        database.addProperty("port", 3306);
        database.addProperty("name", "");
        database.addProperty("username", "");
        database.addProperty("password", "");

        data.add("database", database);
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
