package cz.lunari.lunarimarket.interfaces;

import org.bukkit.plugin.Plugin;

public interface IPluginHook {
    Plugin bukkitInstance();
    String plName();
    String plVersion();
    Boolean isEnabled();
    void loadHook();
}
