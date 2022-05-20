package cz.lunari.lunarimarket.interfaces;

import org.bukkit.plugin.Plugin;

public interface IIntegration {

    Plugin getInstance();

    String getName();
    String getVersion();

    boolean isEnabled();

    void execute();
}
