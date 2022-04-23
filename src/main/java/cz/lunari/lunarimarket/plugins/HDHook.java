package cz.lunari.lunarimarket.plugins;

import cz.lunari.lunarimarket.config.Config;
import cz.lunari.lunarimarket.interfaces.IPluginHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class HDHook implements IPluginHook {

    @Override
    public Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin(getName());
    }

    @Override
    public String getName() {
        return "HolographicDisplays";
    }

    @Override
    public String getVersion() {
        return "2.4.9";
    }

    @Override
    public Boolean isEnabled() {
        return Config.getConfigBool("HolographicDisplaysHook");
    }

    @Override
    public void execute() {
        //TODO initialize palceholders in later stages
    }
}
