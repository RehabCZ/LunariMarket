package cz.lunari.lunarimarket.interfaces;

import org.bukkit.entity.Player;

public interface ICommand {
    String getName();
    String getDescription();
    String getPermission();
    String getSyntax();
    void execute(Player player, String args[]);
}
