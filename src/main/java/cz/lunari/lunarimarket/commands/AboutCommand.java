package cz.lunari.lunarimarket.commands;

import cz.lunari.lunarimarket.interfaces.ICommand;
import cz.lunari.lunarimarket.utils.ChatMessageUtils;
import org.bukkit.entity.Player;

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
    public void execute(Player player, String[] args) {
        player.sendMessage(ChatMessageUtils.translateColors("&7Plugin made with &4\u2764&7 by Rehab_CZ"));
    }
}
