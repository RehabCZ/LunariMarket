package cz.lunari.lunarimarket.config;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.objects.JsonObject;

public class Localization extends JsonObject {

    public Localization(LunariMarket plugin) {
        super(plugin);
    }

    @Override
    protected String fileName() {
        return "messages.json";
    }

    @Override
    protected void loadDefaults() {
        data = new com.google.gson.JsonObject();

        com.google.gson.JsonObject messages = new com.google.gson.JsonObject();
        messages.addProperty("enabled","&2Plugin was successfully loaded.");
        messages.addProperty("disabled","&2Plugin was successfully unloaded.");

        com.google.gson.JsonObject inventory = new com.google.gson.JsonObject();
        inventory.addProperty("menu_title","&6&lLunariMarket &7GUI");
        inventory.addProperty("menu_create_shop", "&a&lCreate shop");
        inventory.addProperty("menu_create_vault", "&a&lCreate vault");
        inventory.addProperty("menu_list_shops", "&a&lList your shops");
        inventory.addProperty("menu_exit", "&c&lExit menu");

        data.add("messages", messages);
        data.add("inventory",inventory);
    }
}
