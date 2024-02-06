package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.LunariMarket;
import cz.lunari.lunarimarket.interfaces.ICommand;
import org.bukkit.command.CommandSender;

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
    public Boolean runInConsole() {
        return true;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        LunariMarket.getInstance().getCommandManager().showHelp(sender);
    }
}
