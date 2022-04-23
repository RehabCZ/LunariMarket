package cz.lunari.lunarimarket.interfaces;

import org.bukkit.plugin.Plugin;

public interface IPluginHook {
    Plugin getInstance();
    String getName();
    String getVersion();
    Boolean isEnabled();
    void execute();
}
