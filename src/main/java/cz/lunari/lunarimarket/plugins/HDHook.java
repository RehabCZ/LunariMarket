package cz.lunari.lunarimarket.plugins;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.handlers.ConfigHandler;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import cz.lunari.lunarimarket.utils.HDPlaceholders;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HDHook implements IPluginHook {

    @Override
    public Plugin plInstance() {
        return Bukkit.getPluginManager().getPlugin("HolographicDisplays");
    }

    @Override
    public String plName() {
        return "HolographicDisplays";
    }

    @Override
    public String plVersion() {
        return "2.4.9";
    }

    @Override
    public Boolean isEnabled() {
        return ConfigHandler.getConfigBool("HolographicDisplaysHook");
    }

    @Override
    public void loadHook() {
        try {
            if(Bukkit.getPluginManager().isPluginEnabled(plName())){
                HologramsAPI.registerPlaceholder(LunariMarket.getInstance(), "{plugins}", 1.0,
                        new HDPlaceholders(Bukkit.getServer().getPluginManager().getPlugins().length));
            }
        } catch (Exception e){ e.printStackTrace(); }
    }
}
