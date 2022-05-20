package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.interfaces.ICommand;
import org.bukkit.entity.Player;

public class HelpCommand implements ICommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Help command";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/lunarimarket help";
    }

    @Override
    public void execute(Player player, String[] args) {
        // Just alias for plain command without args to display help command
        // TODO: Call directly target command's execute method
        player.performCommand("lunarimarket");
    }
}
