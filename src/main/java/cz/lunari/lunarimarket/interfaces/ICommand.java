package cz.lunari.lunarimarket.interfaces;

import org.bukkit.command.CommandSender;

public interface ICommand {

    String getName();
    String getDescription();
    String getPermission();
    String getSyntax();

    Boolean runInConsole();

    void execute(CommandSender sender, String[] args);
}
