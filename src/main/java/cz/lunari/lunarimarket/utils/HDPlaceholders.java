package cz.lunari.lunarimarket.utils;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

public class HDPlaceholders implements PlaceholderReplacer {


    private final int plugins;

    public HDPlaceholders(int plugins){
        this.plugins = plugins;
    }

    @Override
    public String update() {
        return String.valueOf(plugins);
    }
}
