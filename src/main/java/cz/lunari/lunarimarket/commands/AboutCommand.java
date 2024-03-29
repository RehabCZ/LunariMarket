package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.command.CommandSender;

public class AboutCommand implements ICommand {

    @Override
    public String getName() {
        return "about";
    }

    @Override
    public String getDescription() {
        return "About this plugin";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public String getSyntax() {
        return "/lunarimarket about";
    }

    @Override
    public Boolean runInConsole() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatMessageUtils.translateColors("&7Plugin made with &4❤&7 by Rehab_CZ"));
    }
}
