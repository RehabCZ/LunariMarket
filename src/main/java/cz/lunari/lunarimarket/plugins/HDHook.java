package cz.lunari.lunarimarket.plugins;

import cz.lunari.lunarimarket.config.Config;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HDHook implements IPluginHook {

    @Override
    public Plugin bukkitInstance() {
        return Bukkit.getPluginManager().getPlugin(plName());
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
        return Config.getConfigBool("HolographicDisplaysHook");
    }

    @Override
    public void loadHook() {
        //TODO initialize palceholders in later stages
    }
}
