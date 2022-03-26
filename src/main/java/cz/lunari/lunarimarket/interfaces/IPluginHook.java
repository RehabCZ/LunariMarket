package cz.lunari.lunarimarket.interfaces;

import org.bukkit.plugin.Plugin;

public interface IPluginHook {
    Plugin plInstance();
    String plName();
    String plVersion();
    Boolean isEnabled();
    void loadHook();
}
